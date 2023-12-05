package com.nashss.se.sustainabilityshipmentservice.cost;

import com.nashss.se.sustainabilityshipmentservice.types.Material;
import com.nashss.se.sustainabilityshipmentservice.types.Packaging;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentCost;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * A strategy to calculate the cost of a ShipmentOption based on its dollar cost.
 */
public class MonetaryCostStrategy implements CostStrategy {

    private static final BigDecimal LABOR_COST = BigDecimal.valueOf(0.43);
    private final Map<Material, BigDecimal> materialCostPerGram;

    /**
     * Initializes a MonetaryCostStrategy.
     */
    public MonetaryCostStrategy() {
        materialCostPerGram = new HashMap<>();
        materialCostPerGram.put(Material.CORRUGATE, BigDecimal.valueOf(.005));
    }

    /**
     * The cost is calculated by multiplying the cost per gram of the packaging
     * material by the mass in grams of the packaging. The cost of labor is then included
     * to get the total cost to ship.
     *
     * @param shipmentOption a shipment option with packaging
     * @return a ShipmentCost containing the provided ShipmentOption, decorated with its cost
     */
    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        Packaging packaging = shipmentOption.getPackaging();
        BigDecimal materialCost = this.materialCostPerGram.get(packaging.getMaterial());

        BigDecimal cost = packaging.getMass().multiply(materialCost)
            .add(LABOR_COST);

        return new ShipmentCost(shipmentOption, cost);
    }
}
