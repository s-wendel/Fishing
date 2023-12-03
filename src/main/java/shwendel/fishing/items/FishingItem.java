package shwendel.fishing.items;

public enum FishingItem {

    DIRT,
    EMERALD_CHESTPLATE,
    COD,
    SALMON,
    EXAMPLE_ROD,

    ;

    public FishingItemMemory getFishingItemMemory() {
        return FishingItemManager.getItem(this);
    }

}
