package fr.horizons.slimefunx.item.ressources;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import fr.horizons.slimefunx.item.SlimefunResource;
import fr.horizons.slimefunx.list.ItemList;
import fr.horizons.slimefunx.util.CraftingType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class SlimefunNugget extends SlimefunResource {

    public SlimefunNugget(String id, int textureId, @Nullable String name, @Nullable List<String> lore) {
        super(id + "_nugget", textureId, name + " Nugget", lore);
    }

    @Override
    public Table<CraftingType, Integer, ItemStack[]> getRecipesPatterns() {
        if (getId().equals(ItemList.ALUMINIUM_NUGGET.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.ALUMINIUM_DUST.getItem(), ItemList.ALUMINIUM_DUST.getItem(), ItemList.ALUMINIUM_DUST.getItem(),
                                    ItemList.ALUMINIUM_DUST.getItem(), ItemList.ALUMINIUM_DUST.getItem(), ItemList.ALUMINIUM_DUST.getItem(),
                                    ItemList.ALUMINIUM_DUST.getItem(), ItemList.ALUMINIUM_DUST.getItem(), ItemList.ALUMINIUM_DUST.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.COPPER_NUGGET.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.COPPER_DUST.getItem(), ItemList.COPPER_DUST.getItem(), ItemList.COPPER_DUST.getItem(),
                                    ItemList.COPPER_DUST.getItem(), ItemList.COPPER_DUST.getItem(), ItemList.COPPER_DUST.getItem(),
                                    ItemList.COPPER_DUST.getItem(), ItemList.COPPER_DUST.getItem(), ItemList.COPPER_DUST.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.LEAD_NUGGET.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.LEAD_DUST.getItem(), ItemList.LEAD_DUST.getItem(), ItemList.LEAD_DUST.getItem(),
                                    ItemList.LEAD_DUST.getItem(), ItemList.LEAD_DUST.getItem(), ItemList.LEAD_DUST.getItem(),
                                    ItemList.LEAD_DUST.getItem(), ItemList.LEAD_DUST.getItem(), ItemList.LEAD_DUST.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.MAGNESIUM_NUGGET.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.MAGNESIUM_DUST.getItem(), ItemList.MAGNESIUM_DUST.getItem(), ItemList.MAGNESIUM_DUST.getItem(),
                                    ItemList.MAGNESIUM_DUST.getItem(), ItemList.MAGNESIUM_DUST.getItem(), ItemList.MAGNESIUM_DUST.getItem(),
                                    ItemList.MAGNESIUM_DUST.getItem(), ItemList.MAGNESIUM_DUST.getItem(), ItemList.MAGNESIUM_DUST.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.SILVER_NUGGET.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.SILVER_DUST.getItem(), ItemList.SILVER_DUST.getItem(), ItemList.SILVER_DUST.getItem(),
                                    ItemList.SILVER_DUST.getItem(), ItemList.SILVER_DUST.getItem(), ItemList.SILVER_DUST.getItem(),
                                    ItemList.SILVER_DUST.getItem(), ItemList.SILVER_DUST.getItem(), ItemList.SILVER_DUST.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.TIN_NUGGET.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.TIN_DUST.getItem(), ItemList.TIN_DUST.getItem(), ItemList.TIN_DUST.getItem(),
                                    ItemList.TIN_DUST.getItem(), ItemList.TIN_DUST.getItem(), ItemList.TIN_DUST.getItem(),
                                    ItemList.TIN_DUST.getItem(), ItemList.TIN_DUST.getItem(), ItemList.TIN_DUST.getItem()
                            })
                    .build();
        } else if (getId().equals(ItemList.ZINC_NUGGET.getId())) {
            return ImmutableTable.<CraftingType, Integer, ItemStack[]>builder()
                    .put(CraftingType.CRAFTING_TABLE, 1,
                            new ItemStack[]{
                                    ItemList.ZINC_DUST.getItem(), ItemList.ZINC_DUST.getItem(), ItemList.ZINC_DUST.getItem(),
                                    ItemList.ZINC_DUST.getItem(), ItemList.ZINC_DUST.getItem(), ItemList.ZINC_DUST.getItem(),
                                    ItemList.ZINC_DUST.getItem(), ItemList.ZINC_DUST.getItem(), ItemList.ZINC_DUST.getItem()
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
