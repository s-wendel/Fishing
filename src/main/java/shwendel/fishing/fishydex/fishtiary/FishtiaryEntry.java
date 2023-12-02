package shwendel.fishing.fishydex.fishtiary;

import shwendel.fishing.items.FishingItem;
import shwendel.fishing.user.User;

import java.util.List;
import java.util.function.Consumer;

public class FishtiaryEntry {

    // Represents a single Fishtiarydex entry, e.g. 1000x Cod

    private FishingItem item;
    private int required; // The required amount of items to unlock this Fishydex entry
    private List<String> rewards;
    private Consumer<User> rewardsGiven; // Implementation of rewards

    public FishtiaryEntry(FishingItem item, int required, List<String> rewards, Consumer<User> rewardsGiven) {
        this.item = item;
        this.required = required;
        this.rewards = rewards;
        this.rewardsGiven = rewardsGiven;
    }

    public FishingItem getItem() {
        return item;
    }

    /**
     * Returns the amount of items needed to unlock the rewards
     * @return The amount of items needed to unlock the rewards
     */
    public int getRequired() {
        return required;
    }

    /**
     * Returns the display of rewards
     * @return The display of rewards
     */
    public List<String> getRewards() {
        return rewards;
    }

    /**
     * Returns a consumer containing the implementation of the rewards
     * @return A consumer containing the implementation of the rewards
     */
    public Consumer<User> getRewardsGiven() {
        return rewardsGiven;
    }

}
