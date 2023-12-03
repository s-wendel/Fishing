package shwendel.fishing.fishing_mechanics;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import shwendel.fishing.Fishing;
import shwendel.fishing.fishing_mechanics.blocks.FishingBlock;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.user.UserManager;
import shwendel.fishing.util.FishingUtil;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
public class FishingCastListener implements Listener {

    @EventHandler
    public void fishEvent(PlayerFishEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player);

        event.getHook().setMaxWaitTime(100000000);

        if(event.getState() == PlayerFishEvent.State.FISHING || event.getState() == PlayerFishEvent.State.IN_GROUND) {

            new BukkitRunnable() {

                int c = 0;

                @Override
                public void run() {
                    if(c == 40) {
                        event.setCancelled(true);
                        event.getHook().remove();
                        cancel();
                        return;
                    }

                    ItemStack tool = player.getInventory().getItemInMainHand();

                    if(tool.getType() != Material.FISHING_ROD || event.getHook().isDead()) {
                        event.setCancelled(true);
                        event.getHook().remove();
                        cancel();
                        return;
                    }

                    FishingBlock fishingBlock = FishingBlock.getFishingBlock(event.getHook().getLocation().clone().add(0, -0.2, 0).getBlock().getType());

                    if(fishingBlock != null) {

                        for(ItemStack armor : player.getInventory().getArmorContents()) {
                            if(FishingArmor.isFishingEquipment(armor)) {
                                FishingArmor.fromItem(armor).onCast(user, event.getHook());
                            }
                        }

                        cancel();
                        Random random = new Random();
                        double d = random.nextInt(360);
                        double dr = d * Math.PI / 180;
                        final double r = (ThreadLocalRandom.current().nextDouble(0, 0.5) + 4.5) - (user.getStatValue(Stat.FISHING_SPEED, true) / 100d);
;
                        final double p = 1.5;

                        new BukkitRunnable() {

                            int c = 0;
                            //Degrees between each tick of fishing in the 2 sine waves
                            double db = 15;
                            // Current degrees
                            double cd = r * 360;
                            //Location location = event.getHook().getLocation();

                            @Override
                            public void run() {

                                Location location = event.getHook().getLocation();

                                ItemStack tool = player.getInventory().getItemInMainHand();

                                if(tool.getType() != Material.FISHING_ROD || event.getHook().isDead()) {
                                    event.setCancelled(true);
                                    event.getHook().remove();
                                    cancel();
                                    return;
                                }

                                if(c >= 360 / db * r) {

                                    cancel();

                                    user.setFishHook(event.getHook());

                                    ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location.clone().add(0, 0.25, 0), EntityType.ARMOR_STAND);
                                    armorStand.setInvisible(true);
                                    armorStand.setInvulnerable(true);
                                    armorStand.setAI(false);
                                    armorStand.setMarker(true);
                                    armorStand.setGravity(false);
                                    armorStand.setSilent(true);
                                    armorStand.getEquipment().setHelmet(FishingUtil.getHead("afb405c1-16ea-4a23-883f-97867e7db3f9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5YTVjOTVlZTE3YWJmZWY0NWM4ZGMyMjQxODk5NjQ5NDRkNTYwZjE5YTQ0ZjE5ZjhhNDZhZWYzZmVlNDc1NiJ9fX0=", "a79a5c95ee17abfef45c8dc224189964944d560f19a44f19f8a46aef3fee4756"));
                                    armorStand.setSmall(true);
                                    armorStand.setRotation(0, 0);

                                    player.getWorld().playSound(location, Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.5f, 1f);

                                    Location armorStandLocation = armorStand.getLocation();

                                    // I really love runnables
                                    new BukkitRunnable() {

                                        float yaw = 0;
                                        double y = 0;
                                        int yawi = 4 ;
                                        double yi = 0.04;
                                        boolean incrementingY = true;
                                        int c = 0;
                                        long reelTime = System.currentTimeMillis();
                                        int particles = 15;

                                        @Override
                                        public void run() {

                                            if(c == user.getStatValue(Stat.REEL_TIME, true) * 20 || !user.isFishing()) {

                                                for(ItemStack armor : player.getInventory().getArmorContents()) {
                                                    if(FishingArmor.isFishingEquipment(armor)) {
                                                        FishingArmor.fromItem(armor).beforeCatch(user, event.getHook(), reelTime);
                                                    }
                                                }

                                                armorStand.remove();

                                                event.setCancelled(true);
                                                event.getHook().remove();
                                                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 1f, 1f);

                                                cancel();
                                            }

                                            if(y > 1.5) {
                                                incrementingY = false;
                                            } else if(y < 0.5) {
                                                incrementingY = true;
                                            }

                                            if(yaw + yawi >= 180) {
                                                yaw = -180;
                                            }

                                            y += incrementingY ? yi : -1 * yi;
                                            yaw += yawi;

                                            armorStand.teleport(armorStandLocation.clone().add(0, y, 0));
                                            armorStand.setRotation(yaw, 0);

                                            c++;

                                        }

                                    }.runTaskTimer(Fishing.getInstance(), 1, 1);

                                }

                                double wx = p * cd / 360d;
                                double wz = Math.sin(cd * Math.PI / 180);

                                double rwx = wx * Math.cos(dr) - wz * Math.sin(dr);
                                double rwz = wx * Math.sin(dr) + wz * Math.cos(dr);

                                location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location.getX() + rwx, location.getY() + 0.4, location.getZ() + rwz, 0);

                                cd -= db;
                                c++;
                            }

                        }.runTaskTimer(Fishing.getInstance(), 0, 1);
                    }
                    c++;
                }

            }.runTaskTimer(Fishing.getInstance(), 0, 2);

        }
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if((event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)) {

            Player player = event.getPlayer();
            User user = UserManager.getUser(player);

            if(user.isFishing()) {
                event.setCancelled(true);

                ItemStack item = FishingBlock.getFishingBlock(user.getFishHook().getLocation().clone().add(0, -0.2, 0).getBlock().getType()).getRandomCatch(user, user.getFishHook(), Region.CORAL_COAST);

                player.getInventory().addItem(item);
                user.setFishHook(null);

                for(ItemStack armor : player.getInventory().getArmorContents()) {
                    if(FishingArmor.isFishingEquipment(armor)) {
                        FishingArmor.fromItem(armor).afterCatch(user, null);
                    }
                }

            }
        }
    }

}*/
