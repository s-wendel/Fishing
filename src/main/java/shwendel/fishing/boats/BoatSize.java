package shwendel.fishing.boats;

public enum BoatSize {

    SMALL(7, 1),
    MEDIUM(7, 2),
    LARGE(7, 3),
    ;

    private int modifiersWidth;
    private int modifiersLength;

    BoatSize(int modifiersWidth, int modifiersLength) {
        this.modifiersWidth = modifiersWidth;
        this.modifiersLength = modifiersLength;
    }

    /**
     * Returns the grid width of modifiers
     * @return The grid width of modifiers
     */
    public int getModifiersWidth() {
        return modifiersWidth;
    }

    /**
     * Returns the grid length of modifiers
     * @return The grid length of modifiers
     */
    public int getModifiersLength() {
        return modifiersLength;
    }

    /**
     * Returns the total amount of modifiers
     * @return The total amount of modifiers
     */
    public int getModifiers() {
        return modifiersWidth * modifiersLength;
    }

}
