package fr.horizons.slimefunx.gui.guis;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.GuiLocation;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pane;
import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.gui.SlimefunGui;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.list.GuiList;
import fr.horizons.slimefunx.list.ItemList;
import fr.horizons.slimefunx.util.InventoryUtils;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SlimefunGuideGui extends SlimefunGui {

    private static int GUIDE_MODE_DEFAULT = 0;
    public static String SLIMEFUN_GUIDE_TITLE = "§8Slimefun Guide";
    private static ItemStack researchLogo = InventoryUtils.getCustomItem("Recherches", Material.MAP);
    private static ItemStack unlockedLogo = InventoryUtils.getCustomItem("Débloqués", Material.HOPPER);
    private SlimefunX plugin = SlimefunX.getInstance();

    public SlimefunGuideGui(String id) {
        super(id);
    }

    @Override
    public void openGui(Player p, ItemStack is) {
        Gui gui = new Gui(plugin, 9, "           " + SLIMEFUN_GUIDE_TITLE);

        NBTTagCompound compound = (NBTTagCompound) InventoryUtils.getItemData(is, "GuideData");
        if (compound == null) compound = new NBTTagCompound();

        gui.addPane(getUpperBar(is, compound, p));
        gui.addPane(getBottomBar(p, is, compound));
        gui.addPane(getMiddlePane(is, compound)); // TODO: A CHANGER

        gui.setOnLocalClick(e -> e.setCancelled(true));
        gui.setOnGlobalClick(e -> e.setCancelled(true));

        gui.show(p);
    }

    public StaticPane getUpperBar(ItemStack slimefunGuide, NBTTagCompound compound, Player p) {
        StaticPane staticPane = new StaticPane(new GuiLocation(0, 0), 9, 1);

        GuiItem[] breadCrumb = getBreadCrumb(slimefunGuide, compound);
        int i = 0;
        if (breadCrumb != null) for (GuiItem guiItem : breadCrumb) {
            staticPane.addItem(guiItem, new GuiLocation(i, 0));
            i++;
        }

        staticPane.addItem(new GuiItem(InventoryUtils.getSkullHead("Statistiques", p)), new GuiLocation(8, 0));

        return staticPane;
    }

    private GuiItem[] getBreadCrumb(ItemStack slimefunGuide, NBTTagCompound compound) {
        if (!compound.hasKey("OpenedObject") && !compound.hasKey("SelectedCategory")) return null;
        int guideMode = compound.getInt("GuideMode");
        if (guideMode == -1) guideMode = GUIDE_MODE_DEFAULT;

        GuiItem[] list = new GuiItem[3];
        if (guideMode == 0) list[0] = new GuiItem(researchLogo);
        else if (guideMode == 1) list[0] = new GuiItem(unlockedLogo);

        Category category = Categories.getCategoryById(compound.getString("SelectedCategory"));

        list[1] = new GuiItem(category.getIcon());
        if (compound.hasKey("OpenedObject")) {
            SlimefunObject slimefunObject = InventoryUtils.getSlimefunObject(compound.getString("OpenedObject"));
            if (slimefunObject != null) {
                list[2] = new GuiItem(slimefunObject.getItem());
            }
        }

        return list;
    }

    private StaticPane getBottomBar(Player p, ItemStack slimefunGuide, NBTTagCompound compound) {
        StaticPane bottomBar = new StaticPane(new GuiLocation(0, 6), 9, 3);

        bottomBar.addItem(new GuiItem(researchLogo, inventoryClickEvent -> changeGuideMode(p, slimefunGuide, compound, 0)), new GuiLocation(3, 1));
        bottomBar.addItem(new GuiItem(unlockedLogo, inventoryClickEvent -> changeGuideMode(p, slimefunGuide, compound, 1)), new GuiLocation(5, 1));
        int guideMode = compound.getInt("GuideMode");
        System.out.println("mode : " + guideMode);
        if (guideMode == -1) guideMode = GUIDE_MODE_DEFAULT;
        bottomBar.addItem(new GuiItem(guideMode == 0 ? GuiList.BIG_BUTTON_SELECT.getCustomTexture() : GuiList.BIG_BUTTON_UNSELECT.getCustomTexture()), new GuiLocation(3, 0));
        bottomBar.addItem(new GuiItem(guideMode == 0 ? GuiList.BIG_BUTTON_UNSELECT.getCustomTexture() : GuiList.BIG_BUTTON_SELECT.getCustomTexture()), new GuiLocation(5, 0));

        bottomBar.addItem(new GuiItem(GuiList.GUIDE_MAIN.getCustomTexture()), new GuiLocation(0, 2));
        return bottomBar;
    }

    private StaticPane getCategoryList(ItemStack slimefunGuide, NBTTagCompound compound) {
        StaticPane categoryList = new StaticPane(new GuiLocation(0, 1), 9, 5);
        final int[] x = {0};
        final int[] y = {0};
        Arrays.stream(Categories.class.getDeclaredFields())
                .filter(field -> Category.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    try {
                        categoryList.addItem(new GuiItem(((Category) field.get(null)).getIcon(), inventoryClickEvent -> {
                            /*try {
                                //compound.setString("SelectedCategory", ((Category) field.get(null)).getId());
                                //InventoryUtils.setItemData(slimefunGuide, "GuideData", compound);
                                //updateGui(compound);
                            } catch (IllegalAccessException e) {
                                System.out.println("Cassé !");
                                e.printStackTrace();
                            }*/
                        }), new GuiLocation(x[0], y[0])); // TODO: ADD INTERACTION
                        x[0]++;
                        if (x[0] > 8) {
                            x[0] = 0;
                            y[0]++;
                        }
                    } catch (Exception e) {
                        plugin.getLogger().info("Skipping '" + field.getName() + "' due to IllegalAccessException.");
                    }
                });
        return categoryList;
    }

    private Pane getMiddlePane(ItemStack slimefunGuide, NBTTagCompound compound) {
        return getCategoryList(slimefunGuide, compound); // TODO: FAIRE LA LISTE DES ITEMS PAR CATEGORIES
    }

    private void updateGui(Player p, ItemStack slimefunGuide, NBTTagCompound compound) {
        ItemStack newGuide = InventoryUtils.setItemData(slimefunGuide, "GuideData", compound);
        if (InventoryUtils.isEqual(ItemList.SLIMEFUN_GUIDE, p.getInventory().getItemInMainHand())) p.getInventory().setItemInMainHand(newGuide);
        else if (InventoryUtils.isEqual(ItemList.SLIMEFUN_GUIDE, p.getInventory().getItemInOffHand())) p.getInventory().setItemInOffHand(newGuide);
        openGui(p, newGuide);
    }

    private void changeGuideMode(Player p, ItemStack slimefunGuide, NBTTagCompound compound, int mode) {
        if (!compound.hasKey("GuideMode")) {
            compound.setInt("GuideMode", mode);
        } else {
            if (compound.getInt("GuideMode") == mode) return;
            compound.setInt("GuideMode", mode);
        }
        updateGui(p, slimefunGuide, compound);

    }
}
