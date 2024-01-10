package com.nashss.se.sustainabilityshipmentservice.service;

import com.nashss.se.sustainabilityshipmentservice.activity.PrepareShipmentRequest;
import com.nashss.se.sustainabilityshipmentservice.cost.CostStrategy;
import com.nashss.se.sustainabilityshipmentservice.dao.PackagingDAO;
import com.nashss.se.sustainabilityshipmentservice.exceptions.NoPackagingFitsItemException;
import com.nashss.se.sustainabilityshipmentservice.types.FulfillmentCenter;
import com.nashss.se.sustainabilityshipmentservice.types.Item;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentCost;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for finding the appropriate shipment option from all available options returned
 * by the PackagingDAO.
 */
public class ShipmentService {

    private static final Logger logger = LogManager.getLogger(ShipmentService.class);
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
            //logger.info("Finding shipment option with inputs - Item: {}, FulfillmentCenter: {}", item, fulfillmentCenter);

            PrepareShipmentRequest prepareShipmentRequest = PrepareShipmentRequest.builder()
                    .withFcCode(fulfillmentCenter.getFcCode())
                    .withItemAsin(item.getAsin())
                    .withItemDescription(item.getDescription())
                    .withItemLength(item.getLength().toString()) // Convert BigDecimal to String
                    .withItemWidth(item.getWidth().toString())   // Convert BigDecimal to String
                    .withItemHeight(item.getHeight().toString()) // Convert BigDecimal to String
                    .build();

            logger.info("Finding shipment option with inputs - Item: {}, FulfillmentCenter: {}", item, fulfillmentCenter);
            logger.debug("PrepareShipmentRequest: {}", prepareShipmentRequest);

            List<ShipmentOption> results = this.packagingDAO.findShipmentOptions(item, fulfillmentCenter);
            return getLowestCostShipmentOption(results);
        } catch (Exception e) {
            logger.error("Error finding shipment option", e);
            return null;
        }
    }

    private ShipmentOption getLowestCostShipmentOption(List<ShipmentOption> results) throws NoPackagingFitsItemException {
        if (results.isEmpty()) {
            throw new NoPackagingFitsItemException("No packaging options available for the given item and fulfillment center.");
        }

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
