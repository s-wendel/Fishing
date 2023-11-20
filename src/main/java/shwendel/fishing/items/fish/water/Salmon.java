package shwendel.fishing.items.fish.water;

import shwendel.fishing.islands.Region;
import shwendel.fishing.items.fish.Fish;
import shwendel.fishing.util.FishingUtil;

public class Salmon extends Fish {

    public Salmon(Region region, double weight, int edition, long catchTime) {
        name = "Salmon";
        item = FishingUtil.getHead(
                "eb3c8c72-af80-4a62-8f4c-076877428a13",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc3MGQ5MTdkMWNjYzEyZjNhMWNmNzNmZTdkZThjNjU0OGY0YTg0MjA4NjkyM2M3YmI0NDQ2YmNjN2FhZWJmYSJ9fX0=",
                "b770d917d1ccc12f3a1cf73fe7de8c6548f4a842086923c7bb4446bcc7aaebfa");
        description = null;
        super.region = region;
        isSellable = true;
        value = 20;
        super.edition = edition;
        super.weight = weight;
        super.catchTime = catchTime;
    }

}
