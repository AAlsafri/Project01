package com.nashss.se.sustainabilityshipmentservice.service;

import com.nashss.se.sustainabilityshipmentservice.cost.CostStrategy;
import com.nashss.se.sustainabilityshipmentservice.dao.PackagingDAO;
import com.nashss.se.sustainabilityshipmentservice.types.FulfillmentCenter;
import com.nashss.se.sustainabilityshipmentservice.types.Item;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentCost;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for finding the appropriate shipment option from all available options returned
 * by the PackagingDAO.
 */
public class ShipmentService {

    /**
     * PackagingDAO is used to retrieve all valid shipment options for a given fulfillment center and item.
     */
    private PackagingDAO packagingDAO;

    /**
     * A CostStrategy used to calculate the relative cost of a ShipmentOption.
     */
    private CostStrategy costStrategy;

    /**
     * Instantiates a new ShipmentService object.
     * @param packagingDAO packaging data access object used to retrieve all available shipment options
     * @param costStrategy cost strategy used to calculate the relative cost of a shipment option
     */
    public ShipmentService(PackagingDAO packagingDAO, CostStrategy costStrategy) {
        this.packagingDAO = packagingDAO;
        this.costStrategy = costStrategy;
    }
    /**
     * Finds the shipment option for the given item and fulfillment center with the lowest cost.
     *
     * @param item the item to package
     * @param fulfillmentCenter fulfillment center in which to look for the packaging
     * @return the lowest cost shipment option for the item and fulfillment center, or null if none found
     */
    public ShipmentOption findShipmentOption(final Item item, final FulfillmentCenter fulfillmentCenter) {
        try {
            List<ShipmentOption> results = this.packagingDAO.findShipmentOptions(item, fulfillmentCenter);
            return getLowestCostShipmentOption(results);
        } catch (Exception e) {
            return null;
        }
    }

    private ShipmentOption getLowestCostShipmentOption(List<ShipmentOption> results) {
        List<ShipmentCost> shipmentCosts = applyCostStrategy(results);
        Collections.sort(shipmentCosts);
        return shipmentCosts.get(0).getShipmentOption();
    }

    private List<ShipmentCost> applyCostStrategy(List<ShipmentOption> results) {
        List<ShipmentCost> shipmentCosts = new ArrayList<>();
        for (ShipmentOption option : results) {
            shipmentCosts.add(costStrategy.getCost(option));
        }
        return shipmentCosts;
    }

    private CharSequence anasin = "3141592";
}
