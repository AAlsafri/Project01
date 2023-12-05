package com.nashss.se.sustainabilityshipmentservice.activity;

import com.nashss.se.sustainabilityshipmentservice.service.ShipmentService;
import com.nashss.se.sustainabilityshipmentservice.types.FulfillmentCenter;
import com.nashss.se.sustainabilityshipmentservice.types.Item;
import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PrepareShipmentActivityTest {

    private PrepareShipmentRequest request = PrepareShipmentRequest.builder()
        .withFcCode("fcCode")
        .withItemAsin("itemAsin")
        .withItemDescription("description")
        .withItemLength("10.0")
        .withItemWidth("10.0")
        .withItemHeight("10.0")
        .build();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void handleRequest_noAvailableShipmentOption_returnsNull() {
        // GIVEN
        PrepareShipmentActivity activity = new PrepareShipmentActivity(shipmentService);
        when(shipmentService.findShipmentOption(any(Item.class), any(FulfillmentCenter.class)))
                .thenReturn(null);

        // WHEN
        PrepareShipmentResponse response = activity.handleRequest(request);

        // THEN
        assertNull(response.getShipmentOption());
    }

    @Test
    public void handleRequest_availableShipmentOption_returnsNonEmptyResponse() {
        // GIVEN
        PrepareShipmentActivity activity = new PrepareShipmentActivity(shipmentService);
        when(shipmentService.findShipmentOption(any(Item.class), any(FulfillmentCenter.class)))
            .thenReturn(ShipmentOption.builder().build());

        // WHEN
        PrepareShipmentResponse response = activity.handleRequest(request);

        // THEN
        assertNotNull(response.getShipmentOption());
    }
}
