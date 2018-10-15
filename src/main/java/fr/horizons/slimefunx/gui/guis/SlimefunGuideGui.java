package fr.horizons.slimefunx.gui.guis;

import com.github.stefvanschie.inventoryframework.Gui;
import com.github.stefvanschie.inventoryframework.GuiItem;
import com.github.stefvanschie.inventoryframework.GuiLocation;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import fr.horizons.slimefunx.SlimefunX;
import fr.horizons.slimefunx.base.Category;
import fr.horizons.slimefunx.base.SlimefunObject;
import fr.horizons.slimefunx.gui.SlimefunGui;
import fr.horizons.slimefunx.list.Categories;
import fr.horizons.slimefunx.list.GuiList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SlimefunGuideGui extends SlimefunGui {

    private GuideMode guideMode;
    private Category selectedCategory;
    private SlimefunObject openedObject;

    public static String SLIMEFUN_GUIDE_TITLE = "§8Slimefun Guide";

    public SlimefunGuideGui(String id) {
        super(id);
    }

    @Override
    public void openGui(Player p) {
        Gui gui = new Gui(SlimefunX.getInstance(), 9, "           " + SLIMEFUN_GUIDE_TITLE);

        gui.addPane(getUpperBar());
        gui.addPane(getBottomBar());
        gui.addPane(getCategoryList());

        gui.setOnLocalClick(e -> e.setCancelled(true));
        gui.setOnGlobalClick(e -> e.setCancelled(true));

        gui.show(p);
    }

    public StaticPane getUpperBar() {
        StaticPane staticPane = new StaticPane(new GuiLocation(0, 0), 9, 1);

        GuiItem[] breadCrumb = getBreadCrumb();
        int i = 0;
        if (breadCrumb != null) for (GuiItem guiItem : breadCrumb) {
            staticPane.addItem(guiItem, new GuiLocation(i, 0));
            i++;
        }

        staticPane.addItem(new GuiItem(new ItemStack(Material.PLAYER_HEAD)), new GuiLocation(8, 0));

        return staticPane;
    }

    private GuiItem[] getBreadCrumb() {
        if (openedObject == null && selectedCategory == null) return null;
        GuiItem[] list = new GuiItem[3];
        if (guideMode.equals(GuideMode.RESEARCH)) list[0] = new GuiItem(new ItemStack(Material.FILLED_MAP));
        else if (guideMode.equals(GuideMode.ITEM)) list[0] = new GuiItem(new ItemStack(Material.HOPPER));

        list[1] = new GuiItem(selectedCategory.getIcon());
        if (openedObject != null) {
            list[2] = new GuiItem(openedObject.getItem());
        }

        return list;
    }

    private StaticPane getBottomBar() {
        StaticPane bottomBar = new StaticPane(new GuiLocation(0, 6), 9, 3);
        bottomBar.addItem(new GuiItem(new ItemStack(Material.FILLED_MAP)), new GuiLocation(3, 1));
        bottomBar.addItem(new GuiItem(new ItemStack(Material.HOPPER)), new GuiLocation(5, 1));

        bottomBar.addItem(new GuiItem(GuiList.BIG_BUTTON_UNSELECT.getCustomTexture()), new GuiLocation(3, 0));
        bottomBar.addItem(new GuiItem(GuiList.BIG_BUTTON_UNSELECT.getCustomTexture()), new GuiLocation(5, 0));

        bottomBar.addItem(new GuiItem(GuiList.GUIDE_MAIN.getCustomTexture()), new GuiLocation(0, 2));
        // TODO: FAIRE LES BOUTONS AVEC CUSTOMS TEXTURES
        return bottomBar;
    }

    private StaticPane getCategoryList() {
        StaticPane categoryList = new StaticPane(new GuiLocation(0, 1), 9, 5);
        final int[] x = {0};
        final int[] y = {0};
        Arrays.stream(Categories.class.getDeclaredFields())
                .filter(field -> Category.class.isAssignableFrom(field.getType()))
                .forEach(field -> {
                    try {
                        categoryList.addItem(new GuiItem(((Category) field.get(null)).getIcon()), new GuiLocation(x[0], y[0])); // TODO: ADD INTERACTION
                        x[0]++;
                        if (x[0] > 8) {
                            x[0] = 0;
                            y[0]++;
                        }
                    } catch (Exception e) {
                        SlimefunX.getInstance().getLogger().info("Skipping '" + field.getName() + "' due to IllegalAccessException.");
                    }
                });
        return categoryList;
    }

    private enum GuideMode {
        RESEARCH,
        ITEM
    }
}
