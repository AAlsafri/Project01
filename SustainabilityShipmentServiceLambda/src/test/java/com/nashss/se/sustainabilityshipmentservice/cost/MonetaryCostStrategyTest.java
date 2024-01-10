package com.nashss.se.sustainabilityshipmentservice.cost;

import com.nashss.se.sustainabilityshipmentservice.types.Box;
import com.nashss.se.sustainabilityshipmentservice.types.Packaging;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentCost;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonetaryCostStrategyTest {

    private static final Packaging BOX_10x10x20 =
        new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(20));

    private MonetaryCostStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new MonetaryCostStrategy();
    }

    @Test
    void getCost_corrugateMaterial_returnsCorrectCost() {
        // GIVEN
        ShipmentOption option = ShipmentOption.builder()
                .withPackaging(BOX_10x10x20)
                .build();

        // WHEN
        ShipmentCost shipmentCost = strategy.getCost(option);

        // THEN
        BigDecimal expectedCost = BOX_10x10x20.getMass()
                .multiply(BigDecimal.valueOf(0.005))
                .add(BigDecimal.valueOf(0.43));                

        assertEquals(expectedCost, shipmentCost.getCost(),
                "Incorrect monetary cost calculation for a box with dimensions 10x10x20.");
    }
}
