package com.nashss.se.sustainabilityshipmentservice.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoxTest {

    private BigDecimal packagingLength = BigDecimal.valueOf(5.6);
    private BigDecimal packagingWidth = BigDecimal.valueOf(3.3);
    private BigDecimal packagingHeight = BigDecimal.valueOf(8.1);

    private Box box;

    @BeforeEach
    public void setUp() {
        box = new Box(packagingLength, packagingWidth, packagingHeight);
    }

    @Test
    public void canFitItem_itemLengthTooLong_doesNotFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(packagingLength.add(BigDecimal.ONE))
                .withWidth(packagingWidth)
                .withHeight(packagingHeight)
                .build();

        // WHEN
        boolean canFit = box.canFitItem(item);

        // THEN
        assertFalse(canFit, "Item with longer length than package should not fit in the package.");
    }

    @Test
    public void canFitItem_itemWidthTooLong_doesNotFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(packagingLength)
                .withWidth(packagingWidth.add(BigDecimal.ONE))
                .withHeight(packagingHeight)
                .build();

        // WHEN
        boolean canFit = box.canFitItem(item);

        // THEN
        assertFalse(canFit, "Item with longer width than package should not fit in the package.");
    }

    @Test
    public void canFitItem_itemHeightTooLong_doesNotFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(packagingLength)
                .withWidth(packagingWidth)
                .withHeight(packagingHeight.add(BigDecimal.ONE))
                .build();

        // WHEN
        boolean canFit = box.canFitItem(item);

        // THEN
        assertFalse(canFit, "Item with longer height than package should not fit in the package.");
    }

    //TODO
    @Test
    public void canFitItem_itemSameSizeAsBox_doesNotFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(packagingLength)
                .withWidth(packagingWidth)
                .withHeight(packagingHeight)
                .build();

        // WHEN
        boolean canFit = box.canFitItem(item);

        // THEN
        assertFalse(canFit, "Item the same size as the package should not fit in the package.");
    }

    @Test
    public void canFitItem_itemSmallerThanBox_doesFit() {
        // GIVEN
        Item item = Item.builder()
                .withLength(packagingLength.subtract(BigDecimal.ONE))
                .withWidth(packagingWidth.subtract(BigDecimal.ONE))
                .withHeight(packagingHeight.subtract(BigDecimal.ONE))
                .build();

        // WHEN
        boolean canFit = box.canFitItem(item);

        // THEN
        assertTrue(canFit, "Item smaller than the package should fit in the package.");
    }

    @Test
    public void getMass_calculatesMass_returnsCorrectMass() {
        // GIVEN
        box = new Box(BigDecimal.TEN, BigDecimal.TEN, BigDecimal.valueOf(20));

        // WHEN
        BigDecimal mass = box.getMass();

        // THEN
        System.out.println("Actual Mass: " + mass); // Add this line

        // Adjust the scale of the expected value to match the scale of the actual value
        BigDecimal expectedMass = BigDecimal.valueOf(1000).setScale(mass.scale(), RoundingMode.UNNECESSARY);

        assertEquals(expectedMass, mass, "Item smaller than the box should fit in the package.");
    }

}
