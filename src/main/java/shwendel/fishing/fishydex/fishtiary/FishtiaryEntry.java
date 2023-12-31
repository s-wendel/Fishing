package shwendel.fishing.fishydex.fishtiary;

import shwendel.fishing.user.User;

import java.util.function.Consumer;

public class FishtiaryEntry {

    // Represents a single Fishtiary entry, e.g. 1000x Cod

    private int required; // The required amount of items to unlock this Fishydex entry
    private String[] rewards;
    private Consumer<User> rewardsGiven; // Implementation of rewards

    public FishtiaryEntry(int required, String[] rewards, Consumer<User> rewardsGiven) {
        this.required = required;
        this.rewards = rewards;
        this.rewardsGiven = rewardsGiven;
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
    public String[] getRewards() {
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