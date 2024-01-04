package shwendel.fishing.open_world;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import shwendel.fishing.Fishing;

import static java.lang.Math.PI;

public class Tornado {

    private Location location;

    public Tornado(Location location) {
        this.location = location;
    }

    public void run() {

        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(97, 105, 117), 1);
        World world = location.getWorld();
        //player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ(), 0, 0.001, 1, 0, 1, dustOptions);

        new BukkitRunnable() {

            @Override
            public void run() {

                for(double h = 0; h <= 4; h += 0.25) {

                    double radius = 2.2 * h;

                    for(double d = 0; d <= PI * 2; d += PI / 12) {

                        world.spawnParticle(Particle.REDSTONE, location.clone().add(radius * Math.cos(d), h, radius * Math.sin(d)), 0, 0.001, 0, 0, 0, dustOptions);

                    }

                }

            }

        }.runTaskTimer(Fishing.getInstance(), 1, 1);

    }

}
