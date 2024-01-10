package com.nashss.se.sustainabilityshipmentservice.types;

import java.math.BigDecimal;

/**
 * A CORRUGATE Box with fixed length, width, and height (all measured in centimeters).
 */
public class Box extends Packaging {

    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;

    public Box(BigDecimal length, BigDecimal width, BigDecimal height) {
        super(Material.CORRUGATE);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    protected boolean compareDimensions(Packaging otherPackaging) {
        if (otherPackaging instanceof Box) {
            Box otherBox = (Box) otherPackaging;
            return length.equals(otherBox.length) && width.equals(otherBox.width) && height.equals(otherBox.height);
        }
        return false;
    }

    @Override
    public boolean canFitItem(Item item) {
        // Check if each dimension of the item is strictly less than the corresponding dimension of the box
        boolean fitsLength = item.getLength().compareTo(length) < 0;
        boolean fitsWidth = item.getWidth().compareTo(width) < 0;
        boolean fitsHeight = item.getHeight().compareTo(height) < 0;

        // The item can fit if it's smaller in all dimensions
        return fitsLength && fitsWidth && fitsHeight;
    }



    @Override
    public BigDecimal getMass() {
        // Calculate the volume of the box (length * width * height)
        BigDecimal volume = length.multiply(width).multiply(height);

        // Assuming density of the material is 0.5 (adjust as needed)
        BigDecimal density = new BigDecimal("0.5");

        // Calculate the mass using the density
        return volume.multiply(density);
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
