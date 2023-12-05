package com.nashss.se.sustainabilityshipmentservice.activity;

import com.nashss.se.sustainabilityshipmentservice.types.ShipmentOption;

/**
 * A response from a shipment request
 */
public class PrepareShipmentResponse {
    private ShipmentOption shipmentOption;


    public ShipmentOption getShipmentOption() {
        return shipmentOption;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private ShipmentOption shipmentOption;

        public Builder withShipmentOption(ShipmentOption shipmentOption) {
            this.shipmentOption = shipmentOption;
            return this;
        }

        public PrepareShipmentResponse build() {
            PrepareShipmentResponse response = new PrepareShipmentResponse();
            response.shipmentOption = this.shipmentOption;
            return response;
        }
    }
}