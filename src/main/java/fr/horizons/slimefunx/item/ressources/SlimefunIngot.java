package fr.horizons.slimefunx.item.ressources;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import fr.horizons.slimefunx.item.SlimefunResource;
import fr.horizons.slimefunx.list.ItemList;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class SlimefunIngot extends SlimefunResource {

    public SlimefunIngot(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_ingot", textureId, name + " Ingot", lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        if (getId().equals(ItemList.ALUMINIUM_INGOT.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.ALUMINIUM_NUGGET.getItem(), ItemList.ALUMINIUM_NUGGET.getItem(), ItemList.ALUMINIUM_NUGGET.getItem(),
                                    ItemList.ALUMINIUM_NUGGET.getItem(), ItemList.ALUMINIUM_NUGGET.getItem(), ItemList.ALUMINIUM_NUGGET.getItem(),
                                    ItemList.ALUMINIUM_NUGGET.getItem(), ItemList.ALUMINIUM_NUGGET.getItem(), ItemList.ALUMINIUM_NUGGET.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.COPPER_INGOT.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.COPPER_NUGGET.getItem(), ItemList.COPPER_NUGGET.getItem(), ItemList.COPPER_NUGGET.getItem(),
                                    ItemList.COPPER_NUGGET.getItem(), ItemList.COPPER_NUGGET.getItem(), ItemList.COPPER_NUGGET.getItem(),
                                    ItemList.COPPER_NUGGET.getItem(), ItemList.COPPER_NUGGET.getItem(), ItemList.COPPER_NUGGET.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.LEAD_INGOT.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.LEAD_NUGGET.getItem(), ItemList.LEAD_NUGGET.getItem(), ItemList.LEAD_NUGGET.getItem(),
                                    ItemList.LEAD_NUGGET.getItem(), ItemList.LEAD_NUGGET.getItem(), ItemList.LEAD_NUGGET.getItem(),
                                    ItemList.LEAD_NUGGET.getItem(), ItemList.LEAD_NUGGET.getItem(), ItemList.LEAD_NUGGET.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.MAGNESIUM_INGOT.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.MAGNESIUM_NUGGET.getItem(), ItemList.MAGNESIUM_NUGGET.getItem(), ItemList.MAGNESIUM_NUGGET.getItem(),
                                    ItemList.MAGNESIUM_NUGGET.getItem(), ItemList.MAGNESIUM_NUGGET.getItem(), ItemList.MAGNESIUM_NUGGET.getItem(),
                                    ItemList.MAGNESIUM_NUGGET.getItem(), ItemList.MAGNESIUM_NUGGET.getItem(), ItemList.MAGNESIUM_NUGGET.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.SILVER_INGOT.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.SILVER_NUGGET.getItem(), ItemList.SILVER_NUGGET.getItem(), ItemList.SILVER_NUGGET.getItem(),
                                    ItemList.SILVER_NUGGET.getItem(), ItemList.SILVER_NUGGET.getItem(), ItemList.SILVER_NUGGET.getItem(),
                                    ItemList.SILVER_NUGGET.getItem(), ItemList.SILVER_NUGGET.getItem(), ItemList.SILVER_NUGGET.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.TIN_INGOT.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.TIN_NUGGET.getItem(), ItemList.TIN_NUGGET.getItem(), ItemList.TIN_NUGGET.getItem(),
                                    ItemList.TIN_NUGGET.getItem(), ItemList.TIN_NUGGET.getItem(), ItemList.TIN_NUGGET.getItem(),
                                    ItemList.TIN_NUGGET.getItem(), ItemList.TIN_NUGGET.getItem(), ItemList.TIN_NUGGET.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.ZINC_INGOT.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.ZINC_NUGGET.getItem(), ItemList.ZINC_NUGGET.getItem(), ItemList.ZINC_NUGGET.getItem(),
                                    ItemList.ZINC_NUGGET.getItem(), ItemList.ZINC_NUGGET.getItem(), ItemList.ZINC_NUGGET.getItem(),
                                    ItemList.ZINC_NUGGET.getItem(), ItemList.ZINC_NUGGET.getItem(), ItemList.ZINC_NUGGET.getItem()
                            })
                    .build();
        }
        return null;
    }

    @Override
    public int getResearchCost() {
        return 0;
    }
}
