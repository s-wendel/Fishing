package shwendel.fishing.fishing_mechanics;

import org.bukkit.util.Vector;

public class SwimpathNode {
    private final Vector vec2D;
    private final double magnetism;
    private final double maxMagnitude;

    public SwimpathNode(double x, double z, double magnetism, double maxMagnitude) {
        vec2D = new Vector(x, 0, z);
        this.magnetism = magnetism;
        this.maxMagnitude = maxMagnitude;
    }

    public Vector vec2D() {
        return vec2D.clone(); // cloning to prevent mutation of original vector data
        // i hate bukkit vectors
    }

    public double magnetism() {
        return magnetism;
    }

    public double maxMagnitude() {
        return maxMagnitude;
    }
}
