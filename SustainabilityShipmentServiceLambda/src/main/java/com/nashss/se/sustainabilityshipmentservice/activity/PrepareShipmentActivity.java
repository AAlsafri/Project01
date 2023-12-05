package com.nashss.se.sustainabilityshipmentservice.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.sustainabilityshipmentservice.service.ShipmentService;
import com.nashss.se.sustainabilityshipmentservice.types.FulfillmentCenter;
import com.nashss.se.sustainabilityshipmentservice.types.Item;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;

import java.math.BigDecimal;

/**
 * PARTICIPANTS: You are not expected to modify or use this class directly. Please do not modify the code contained
 * in this class as doing so might break the Shipment Service functionality.
 *
 * This is implementation of the PrepareShipment activity. It handles PrepareShipment requests by returning
 * the appropriate shipment option.
 */
public class PrepareShipmentActivity {
    /**
     * Shipment service used to retrieve shipment options.
     */
    private ShipmentService shipmentService;

    /**
     * Instantiates a new PrepareShipmentActivity object.
     * @param shipmentService Shipment service used to retrieve shipment options.
     */
    public PrepareShipmentActivity(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    /**
     * This method handles the incoming request by calling the shipment service and returning the
     * appropriate shipment option for the fulfillment center and item provided in the request.
     *
     * @param request contains information on fulfillment center and item
     * @return response that contains appropriate shipment option that can be used to pack the provided item
     */
    public PrepareShipmentResponse handleRequest(PrepareShipmentRequest request) {
        Item item = Item.builder()
            .withAsin(request.getItemAsin())
            .withDescription(request.getItemDescription())
            .withLength(new BigDecimal(request.getItemLength()))
            .withWidth(new BigDecimal(request.getItemWidth()))
            .withHeight(new BigDecimal(request.getItemHeight()))
            .build();

        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(request.getFcCode());

        ShipmentOption shipmentOption = shipmentService.findShipmentOption(item, fulfillmentCenter);

        return PrepareShipmentResponse.builder()
            .withShipmentOption(shipmentOption)
            .build();
    }
}

