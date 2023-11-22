package shwendel.fishing.items;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import shwendel.fishing.config.Config;
import shwendel.fishing.config.ConfigManager;
import shwendel.fishing.config.ConfigType;

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

                FishingItem id = FishingItem.valueOf(stringId);

                // Config sections
                final String ID_SECTION = ITEM_SECTION + "." + stringId;
                final String MATERIAL_SECTION = ID_SECTION + ".material";

                ItemStack item = new ItemStack(Material.valueOf(fileConfiguration.getString(MATERIAL_SECTION + ".material")));
                ItemMeta itemMeta = item.getItemMeta();

                String materialName = item.toString();

                if(materialName.startsWith("LEATHER_") && !materialName.contains("HORSE")) {

                    final String RED_SECTION = MATERIAL_SECTION + ".red";
                    final String GREEN_SECTION = MATERIAL_SECTION + ".green";
                    final String BLUE_SECTION = MATERIAL_SECTION + ".blue";

                    LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;

                    leatherArmorMeta

                }


                items.put(id, new FishingItemMemory(
                        id,
                        item,
                        name,
                        sellValue,
                        desc,
                        stats
                ));

            }

        }


    }

}
