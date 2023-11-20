package shwendel.fishing.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FishingUtil {

    public static ItemStack getHead(String uuid, String value, String url) {
        PlayerProfile head = Bukkit.createProfile(UUID.fromString(uuid), "skin127569846");
        head.setProperty(new ProfileProperty("textures", value, url));
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setPlayerProfile(head);
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static String toReadableDate(long milliseconds) {
        Date date = new Date(milliseconds);

        String[] split = date.toString().split(" ");

        return split[1] + " " + split[2] + ", " + split[5];

    }

    public static String displayNumber(double number) {
        return (number == (int) number) ? ((int) number) + "" : (Math.round(number * 10.0) / 10.0) + "";
    }

    public static String toTime(long time) {
        StringBuilder builder = new StringBuilder();
        if(time >= 86400000) {
            long days = TimeUnit.MILLISECONDS.toDays(time);
            builder.append(days + "d ");
            time -= TimeUnit.DAYS.toMillis(days);
        }
        if(time >= 3600000) {
            long hours = TimeUnit.MILLISECONDS.toHours(time);
            builder.append(hours + "h ");
            time -= TimeUnit.HOURS.toMillis(hours);
        }
        if(time >= 60000) {
            long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
            builder.append(minutes + "m ");
            time -= TimeUnit.MINUTES.toMillis(minutes);
        }
        if(time >= 1000) {
            long seconds = TimeUnit.MILLISECONDS.toSeconds(time);
            builder.append(seconds + "s ");
            time -= TimeUnit.SECONDS.toMillis(seconds);
        }
        String text = builder.toString();
        if(text.isEmpty())
            return "0s";
        return text;
    }

}
