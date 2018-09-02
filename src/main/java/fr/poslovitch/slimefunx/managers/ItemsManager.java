package fr.poslovitch.slimefunx.managers;

import fr.poslovitch.slimefunx.SlimefunX;
import fr.poslovitch.slimefunx.api.Category;
import fr.poslovitch.slimefunx.api.SlimefunItem;
import fr.poslovitch.slimefunx.api.machines.SlimefunMachine;
import fr.poslovitch.slimefunx.api.recipes.MachineRecipe;
import fr.poslovitch.slimefunx.api.recipes.Recipe;
import fr.poslovitch.slimefunx.util.YamlItemParser;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public class ItemsManager {

    private final SlimefunX plugin;
    private final List<SlimefunItem> items;

    public ItemsManager(SlimefunX plugin) {
        this.plugin = plugin;
        this.items = new ArrayList<>();
    }

    public void loadItems() {
        Map<String[], YamlConfiguration> preload = new LinkedHashMap<>();

        // Gather all existing files and put them in a map
        for (Category c : plugin.getCategoriesManager().getCategories()) {
            for (File file : Objects.requireNonNull(c.getDirectory().listFiles((dir, name) -> name.endsWith(".yml") && !name.equals("category_descriptor.yml")))) {
                String id = file.getPath().substring(file.getPath().lastIndexOf(File.separator) + 1).replaceAll(".yml", "");

                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
                preload.putIfAbsent(new String[] {id, c.getId()}, yamlConfiguration);
            }
        }

        for (Map.Entry<String[], YamlConfiguration> entry : preload.entrySet()) {
            String id = entry.getKey()[0];
            Category category = plugin.getCategoriesManager().getCategoryById(entry.getKey()[1]);
            YamlConfiguration yaml = entry.getValue();

            if (yaml.contains("meta.type")) {
                if (yaml.getString("meta.type").equalsIgnoreCase("item")) {
                    loadSlimefunItem(id, category, yaml);
                }
                else if (yaml.getString("meta.type").equalsIgnoreCase("machine")) {
                    loadSlimefunMachine(id, category, yaml);
                }
            }
        }

        plugin.getLogger().info("Registered " + items.size() + " categories.");
    }

    private void loadSlimefunItem(String id, Category category, YamlConfiguration yaml) {
        SlimefunItem.Builder builder = new SlimefunItem.Builder()
                .id(id)
                .category(category)
                .item(YamlItemParser.parseYaml(yaml.getConfigurationSection("item")))
                .researchCost(yaml.getInt("meta.research-cost"));

        // Recipe
        Recipe.Builder recipeBuilder = new Recipe.Builder()
                .type(Recipe.Type.MACHINE)
                .recipeHolderId(yaml.getString("recipe.machine", "ENHANCED_CRAFTING_TABLE"));

        String[][] recipePattern = new String[5][5];
        int rowIndex = 0;
        for (String row : yaml.getStringList("recipe.pattern")) {

            int columnIndex = 0;
            for (String column : row.split(",")) {
                recipePattern[rowIndex][columnIndex] = column;
                columnIndex++;
            }
            rowIndex++;
        }
        recipeBuilder.recipe(recipePattern);

        // Set recipe
        builder.recipe(recipeBuilder.build());

        items.add(builder.build());
        System.out.println("Loaded: " + id);
    }

    private void loadSlimefunMachine(String id, Category category, YamlConfiguration yaml) {

    }

    public List<SlimefunItem> getItems() {
        return items;
    }

    public Optional<SlimefunItem> getItemById(String id) {
        return items.stream().filter(item -> item.getId().equalsIgnoreCase(id)).findFirst();
    }

    public Optional<SlimefunMachine> getMachineById(String id) {
        return Optional.ofNullable((SlimefunMachine) getItemById(id).filter(item -> item instanceof SlimefunMachine).orElse(null));
    }
}
