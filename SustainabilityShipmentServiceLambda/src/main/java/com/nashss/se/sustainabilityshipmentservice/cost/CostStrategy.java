package com.nashss.se.sustainabilityshipmentservice.cost;

import com.nashss.se.sustainabilityshipmentservice.types.ShipmentCost;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;

/**
 * A strategy to calculate the cost of a ShipmentOption.
 *
 * The cost may be a monetary cost or more abstract like an environmental cost.
 */
public interface CostStrategy {

    /**
     * Get the cost of the shipment option.
     *
     * @param shipmentOption a shipment option with packaging
     * @return total cost of the shipment option
     */
    ShipmentCost getCost(ShipmentOption shipmentOption);
}
