package shwendel.fishing.fishing_mechanics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import shwendel.fishing.Fishing;
import shwendel.fishing.fishing_mechanics.blocks.FishingBlock;
import shwendel.fishing.islands.Region;
import shwendel.fishing.user.Stat;
import shwendel.fishing.user.User;
import shwendel.fishing.user.UserManager;

import java.util.Random;

public class FishingCastListener implements Listener {

    private final SwimpathNode[][] swimpathArray = {
            {
                    new SwimpathNode(2, 6, 0.3, 2.5),
                    new SwimpathNode(-2, 5, 0.3, 2.5),
                    new SwimpathNode(1, 4, 0.3, 2.5),
                    new SwimpathNode(-1, 2, 0.3, 2.5),
                    new SwimpathNode(0, 0, 1, 2.5)
            },
            {
                    new SwimpathNode(1, 2, 1, 3),
                    new SwimpathNode(-1, 1, 1, 3.5),
                    new SwimpathNode(0, 0, 2, 4)
            }
    };

    @EventHandler
    public void fishEvent(PlayerFishEvent event) {
        FishHook hook = event.getHook();
        hook.setWaitTime(100000000);

        PlayerFishEvent.State state = event.getState();
        if(state != PlayerFishEvent.State.FISHING && state != PlayerFishEvent.State.IN_GROUND) { return; }

        UserManager.getUser(event.getPlayer()).setFishHook(hook);

        rodCast(hook, event);
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) { return; }

        Player player = event.getPlayer();
        User user = UserManager.getUser(player);

        // to check if user is currently fishing, see if they have a hook associated with them
        if(!user.hasHook()) { return; }

        switch (user.getFishingState()) {
            case PARRY:
                FishHook hook = user.getFishHook();
                player.playSound(player, Sound.BLOCK_ANVIL_PLACE, 1, 1.4f + new Random().nextFloat(0.2f));
                player.getWorld().spawnParticle(Particle.CRIT, hook.getLocation(), 10);
                hook.setVelocity(new Vector(0, 0.11f, 0));
                user.setFishingState(User.FishingState.NONE);
                event.setCancelled(true);
                break;
            case CATCH:
                ItemStack item = FishingBlock.getFishingBlock(user.getFishHook().getLocation().clone().add(0, -0.2, 0).getBlock().getType()).getRandomCatch(user, user.getFishHook(), Region.CORAL_COAST);
                player.getInventory().addItem(item);
                user.setFishHook(null);
                break;
            default:
        }
    }

    private void rodCast(FishHook hook, PlayerFishEvent event) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(hook.isDead()) {
                    event.setCancelled(true);
                    cancel();
                    return;
                }

                FishingBlock fishingBlock = FishingBlock.getFishingBlock(hook.getLocation().clone().add(0, -0.2, 0).getBlock().getType());
                // bobber has hit fishable block, start fish behavior
                if (fishingBlock != null) {
                    cancel();
                    startFishing(hook, event);
                }
            }
        }.runTaskTimer(Fishing.getInstance(), 0, 2); // can potentially slow this down if performance becomes an issue
    }

    private void startFishing(FishHook hook, PlayerFishEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player);
        Random random = new Random();

        double initialDistance = 7;
        double catchDistance = 0.2;

        //int velocityAngleOffset = 60 + random.nextInt(30);
        //Vector velocityNormal = startingPos.clone().normalize().rotateAroundY(Math.toRadians((-1 * random.nextInt(2)) * velocityAngleOffset + 180));


        new BukkitRunnable() {

            Location hookLoc;
            double randomStartingAngle = Math.toRadians(random.nextInt(360));
            final Vector fishPos = new Vector(0, 0, initialDistance).rotateAroundY(randomStartingAngle);
            final Vector fishVel = new Vector();

            int swimpathIndex = 0;
            int nodeIndex = 0;
            SwimpathNode[] nodes = swimpathArray[swimpathIndex];
            Vector target = nodes[nodeIndex].vec2D().rotateAroundY(randomStartingAngle);
            double magnetism = nodes[nodeIndex].magnetism();
            double additiveMagnetism = 0;
            double maxMagnitude = nodes[nodeIndex].maxMagnitude();
            
            int parryWaitTicks = 0;
            boolean catchable = true;

            double distanceFromHook;

            @Override
            public void run() {
                if(hook.isDead()) {
                    event.setCancelled(true); // hasn't this... already happened if hook is dead?
                    cancel();
                    return;
                }

                if (user.getFishingState() == User.FishingState.PARRY) {
                    parryWaitTicks++;
                    if (parryWaitTicks > 30) {
                        event.setCancelled(true);
                        hook.remove();
                        user.setFishHook(null);
                        user.setFishingState(User.FishingState.NONE);
                        // snapping sfx
                        player.sendMessage("waited too long! :(");
                        cancel();
                    }
                    return;
                }

                distanceFromHook = fishPos.clone().subtract(target).length();

                if (distanceFromHook >= catchDistance && !catchable) {
                    catchable = true;
                }
                else if (distanceFromHook < catchDistance && catchable) {
                    // if we're on the last node, which should always be (0,0)
                    if (nodeIndex == nodes.length - 1) {
                        // if we're on the last swimpath
                        if (swimpathIndex == swimpathArray.length - 1) {
                            user.setFishingState(User.FishingState.CATCH);
                            player.playSound(hookLoc, Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.5f, 1f);
                            hook.setVelocity(fishVel.clone().multiply(0.04).setY(-0.2));
                            cancel();
                            catchFish(user, player, hook, hookLoc, event, fishVel);
                            return;
                        }
                        // otherwise, setup for next swimpath
                        user.setFishingState(User.FishingState.PARRY);
                        player.playSound(hookLoc, Sound.ENTITY_FISHING_BOBBER_SPLASH, 0.3f, 2);
                        hook.setVelocity(fishVel.clone().multiply(0.04).setY(0.05));

                        swimpathIndex++;
                        nodes = swimpathArray[swimpathIndex];
                        randomStartingAngle = Math.toRadians(random.nextInt(360));
                        nodeIndex = 0;
                    } else {
                        nodeIndex++;
                    }
                    target = nodes[nodeIndex].vec2D().rotateAroundY(randomStartingAngle);
                    magnetism = nodes[nodeIndex].magnetism();
                    additiveMagnetism = 0;
                    maxMagnitude = nodes[nodeIndex].maxMagnitude();
                }

                // take difference of target and fishPos, then set magnitude to magnetism
                // TODO: force slow rotation of fish, 180s should be difficult to achieve (maybe?)
                additiveMagnetism += 0.01;
                // TODO: Maybe have additive magnetism value an attribute of the node?
                fishVel.add(target.clone().subtract(fishPos).normalize().multiply(magnetism + additiveMagnetism));
                // cap magnitude
                if (fishVel.length() > maxMagnitude) {
                    fishVel.normalize().multiply(maxMagnitude);
                }

                // step the fish pos
                fishPos.add(fishVel.clone().multiply(0.05));

                hookLoc = hook.getLocation();
                hookLoc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, hookLoc.clone().add(fishPos), 0);

                // particles to help test swimpath nodes
                hookLoc.getWorld().spawnParticle(Particle.FLAME, hookLoc.clone().add(target), 0);
            }

        }.runTaskTimer(Fishing.getInstance(), 0, 1);
    }

    private void catchFish(User user, Player player, FishHook hook, Location hookLoc, PlayerFishEvent event, Vector fishVel) {

        TextDisplay textDisplay = (TextDisplay) player.getWorld().spawnEntity(hookLoc.clone().add(0, 0.2, 0), EntityType.TEXT_DISPLAY);
        textDisplay.text(Component.text("Caught!", TextColor.color(0, 255, 0)));
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setShadowed(true);
        textDisplay.setBackgroundColor(Color.fromARGB(0));

        // set interp
//        Transformation transform = textDisplay.getTransformation();
//        transform.getTranslation().set(0, 4, 0);
//        textDisplay.setTransformation(transform);
//        textDisplay.setInterpolationDuration(20);
//        textDisplay.setInterpolationDelay(-1);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Fishing.getInstance(), () -> {
            Transformation transform = textDisplay.getTransformation();
            transform.getTranslation().set(0, 2, 0);
            textDisplay.setTransformation(transform);
            textDisplay.setInterpolationDuration(100);
            textDisplay.setInterpolationDelay(0);
        }, 1);

        new BukkitRunnable() {
            int c = 0;
            final long reelTime = System.currentTimeMillis();

            @Override
            public void run() {
                c++;
                if(c == user.getStatValue(Stat.REEL_TIME, true) * 20 || !user.hasHook()) {

                    textDisplay.remove();

                    user.setFishingState(User.FishingState.NONE);

                    event.setCancelled(true);
                    hook.remove();
                    player.getWorld().playSound(player, Sound.ENTITY_FISHING_BOBBER_RETRIEVE, 1f, 1f);

                    cancel();
                }
            }

        }.runTaskTimer(Fishing.getInstance(), 1, 1);
    }
}