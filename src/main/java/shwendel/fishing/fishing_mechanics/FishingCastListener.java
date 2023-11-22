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
import org.bukkit.util.Vector;
import shwendel.fishing.Fishing;
import shwendel.fishing.fishing_mechanics.blocks.FishingBlock;
import shwendel.fishing.islands.Region;
import shwendel.fishing.items.armor.FishingArmor;
import shwendel.fishing.items.armor.emerald.EmeraldBoots;
import shwendel.fishing.items.armor.emerald.EmeraldChestplate;
import shwendel.fishing.items.armor.emerald.EmeraldHelmet;
import shwendel.fishing.items.armor.emerald.EmeraldLeggings;
import shwendel.fishing.items.armor.water.BarrelHat;
import shwendel.fishing.items.materials.emerald.CompactEmerald;
import shwendel.fishing.items.materials.emerald.Emerald;
import shwendel.fishing.items.materials.emerald.EmeraldHandle;
import shwendel.fishing.items.materials.emerald.ShinyEmerald;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.user.UserManager;
import shwendel.fishing.util.FishingUtil;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FishingCastListener implements Listener {

    @EventHandler
    public void fishEvent(PlayerFishEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player);

        FishHook hook = event.getHook();

        hook.setWaitTime(100000000);

        PlayerFishEvent.State state = event.getState();
        if(state != PlayerFishEvent.State.FISHING && state != PlayerFishEvent.State.IN_GROUND) { return; }


        new BukkitRunnable() {

            @Override
            public void run() {
                if(hook.isDead()) {
                    event.setCancelled(true);
                    hook.remove();
                    cancel();
                    return;
                }

                FishingBlock fishingBlock = FishingBlock.getFishingBlock(hook.getLocation().clone().add(0, -0.2, 0).getBlock().getType());
                // if still casting, return to loop
                if (fishingBlock == null) { return; }

                // bobber has hit fishable block, start fish behavior
                cancel();

//                for(ItemStack armor : player.getInventory().getArmorContents()) {
//                    if(FishingArmor.isFishingEquipment(armor)) {
//                        FishingArmor.fromItem(armor).onCast(user, hook);
//                    }
//                }

                Random random = new Random();
                double randomScalar = 4.0 + random.nextDouble(4.0);
                double randomAngle = Math.toRadians(random.nextInt(360));
                Vector startingOffset = new Vector(randomScalar * Math.sin(randomAngle), 0, randomScalar * Math.cos(randomAngle));

                int velocityAngleOffset = 60 + random.nextInt(30);
                Vector velocityNormal = startingOffset.clone().normalize().rotateAroundY(Math.toRadians((-1 * random.nextInt(2)) * velocityAngleOffset + 180));


                new BukkitRunnable() {

                    Location hookLoc;
                    Vector fishPos = startingOffset;
                    Vector fishVel = velocityNormal.multiply(3); // TODO: randomize magnitude

                    @Override
                    public void run() {
                        if(hook.isDead()) {
                            event.setCancelled(true);
                            cancel();
                            return;
                        }

                        hookLoc = hook.getLocation();

                        // simulate fish behavior

                        // need to cap the "gravity" effect
                        fishVel.add(fishPos.clone().normalize().multiply(-1 * 0.1));
                        fishPos.add(fishVel.clone().multiply(0.05));
                        System.out.println(fishPos);

                        // if fish is in biting range
                        if(fishPos.length() < 0.2) {

                            cancel();

                            user.setFishHook(hook);

                            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(hookLoc.clone().add(0, 0.25, 0), EntityType.ARMOR_STAND);
                            armorStand.setInvisible(true);
                            armorStand.setInvulnerable(true);
                            armorStand.setAI(false);
                            armorStand.setMarker(true);
                            armorStand.setGravity(false);
                            armorStand.setSilent(true);
                            armorStand.getEquipment().setHelmet(FishingUtil.getHead("afb405c1-16ea-4a23-883f-97867e7db3f9", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5YTVjOTVlZTE3YWJmZWY0NWM4ZGMyMjQxODk5NjQ5NDRkNTYwZjE5YTQ0ZjE5ZjhhNDZhZWYzZmVlNDc1NiJ9fX0=", "a79a5c95ee17abfef45c8dc224189964944d560f19a44f19f8a46aef3fee4756"));
                            armorStand.setSmall(true);
                            armorStand.setRotation(0, 0);

                            player.getWorld().playSound(hookLoc, Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.5f, 1f);

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

                                @Override
                                public void run() {

                                    if(c == user.getStatValue(Stat.REEL_TIME, true) * 20 || !user.isFishing()) {

                                        for(ItemStack armor : player.getInventory().getArmorContents()) {
                                            if(FishingArmor.isFishingEquipment(armor)) {
                                                FishingArmor.fromItem(armor).beforeCatch(user, hook, reelTime);
                                            }
                                        }

                                        armorStand.remove();

                                        event.setCancelled(true);
                                        hook.remove();
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

                        hookLoc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, hookLoc.add(fishPos), 0);
                    }

                }.runTaskTimer(Fishing.getInstance(), 0, 1);
            }
        }.runTaskTimer(Fishing.getInstance(), 0, 2);
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) { return; }

        Player player = event.getPlayer();
        User user = UserManager.getUser(player);

        if(!user.isFishing()) { return; }

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