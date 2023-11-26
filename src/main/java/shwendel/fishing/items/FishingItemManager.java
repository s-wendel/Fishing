package shwendel.fishing.items;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import shwendel.fishing.config.Config;
import shwendel.fishing.config.ConfigManager;
import shwendel.fishing.config.ConfigType;
import shwendel.fishing.user.Stat;
import shwendel.fishing.util.FishingUtil;

import java.util.HashMap;
import java.util.Map;

public class FishingItemManager {

    private static final String ITEM_SECTION = "items";

    private static Map<FishingItem, FishingItemMemory> items;


    static {
        reload();
    }

    public static void reload() {

        items = new HashMap<>();

        for(FishingItemType itemType : FishingItemType.values()) {

            Config config = ConfigManager.getConfigs().get(ConfigType.valueOf(itemType.toString()));
            FileConfiguration fileConfiguration = config.getConfig();

            for(String stringId : fileConfiguration.getConfigurationSection(ITEM_SECTION).getKeys(false)) {

                FishingItem id = FishingItem.valueOf(stringId.toUpperCase());

                // Config sections
                final String ID_SECTION = ITEM_SECTION + "." + stringId;
                final String MATERIAL_SECTION = ID_SECTION + ".material";

                ItemStack item = new ItemStack(Material.valueOf(fileConfiguration.getString(MATERIAL_SECTION + ".material")));
                ItemMeta itemMeta = item.getItemMeta();

                Material material = item.getType();
                String materialName = material.toString();

                if(materialName.startsWith("LEATHER_") && !materialName.contains("HORSE")) {

                    final String RED_SECTION = MATERIAL_SECTION + ".red";
                    final String GREEN_SECTION = MATERIAL_SECTION + ".green";
                    final String BLUE_SECTION = MATERIAL_SECTION + ".blue";

                    if(fileConfiguration.isSet(RED_SECTION) && fileConfiguration.isSet(GREEN_SECTION) && fileConfiguration.isSet(BLUE_SECTION)) {

                        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;

                        leatherArmorMeta.setColor(Color.fromRGB(
                                fileConfiguration.getInt(RED_SECTION),
                                fileConfiguration.getInt(GREEN_SECTION),
                                fileConfiguration.getInt(BLUE_SECTION)
                        ));

                    }

                } else if(material == Material.PLAYER_HEAD) {

                    item = FishingUtil.getHead(
                            fileConfiguration.getString(MATERIAL_SECTION + ".uuid"),
                            fileConfiguration.getString(MATERIAL_SECTION + ".value"),
                            fileConfiguration.getString(MATERIAL_SECTION + ".url")
                    );

                }

                Map<Stat, Double> stats = new HashMap<>();

                for(String stat : fileConfiguration.getStringList(ID_SECTION + ".stats")) {

                    String[] split = stat.split("@");

                    stats.put(
                            Stat.valueOf(split[0]),
                            Double.parseDouble(split[1])
                    );

                }

                items.put(id, new FishingItemMemory(
                        id,
                        item,
                        ChatColor.translateAlternateColorCodes('&', fileConfiguration.getString(ID_SECTION + ".name")),
                        fileConfiguration.getLong(ID_SECTION + ".value"),
                        fileConfiguration.getStringList(ID_SECTION + ".description"),
                        stats
                ));

            }

        }


    }

    public static FishingItemMemory getItem(FishingItem item) {
        return items.getOrDefault(item, null);
    }

}
