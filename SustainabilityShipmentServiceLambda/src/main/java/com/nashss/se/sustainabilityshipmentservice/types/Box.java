package com.nashss.se.sustainabilityshipmentservice.types;

import java.math.BigDecimal;

/**
 * A CORRUGATE Box with fixed length, width, and height (all measured in centimeters).
 */
public class Box extends Packaging {

    /**
     * This box's length.
     */
    private BigDecimal length;

    /**
     * This box's smallest dimension.
     */
    private BigDecimal width;

    /**
     * This box's largest dimension.
     */
    private BigDecimal height;

    /**
     * Instantiates a new Box object.
     * @param length - the length of the box
     * @param width - the width of the box
     * @param height - the height of the box
     */
    public Box(BigDecimal length, BigDecimal width, BigDecimal height) {
        super(Material.CORRUGATE);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public BigDecimal getLength() {
        return length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    @Override
    public boolean canFitItem(Item item) {
        return this.length.compareTo(item.getLength()) > 0 &&
                this.width.compareTo(item.getWidth()) > 0 &&
                this.height.compareTo(item.getHeight()) > 0;
    }

    @Override
    public BigDecimal getMass() {
        BigDecimal two = BigDecimal.valueOf(2);

        // For simplicity, we ignore overlapping flaps
        BigDecimal endsArea = length.multiply(width).multiply(two);
        BigDecimal shortSidesArea = length.multiply(height).multiply(two);
        BigDecimal longSidesArea = width.multiply(height).multiply(two);

        return endsArea.add(shortSidesArea).add(longSidesArea);
    }

    @Override
    public String toString() {
        return "Box{" +
                "length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
