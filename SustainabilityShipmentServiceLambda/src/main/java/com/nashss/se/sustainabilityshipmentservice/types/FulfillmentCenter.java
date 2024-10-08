package com.nashss.se.sustainabilityshipmentservice.types;

import java.util.Objects;

/**
 * Represents an Amazon fulfillment center.
 *
 * Fulfillment centers receive orders, pack the items, and ship them to customers.
 */
public class FulfillmentCenter {

    /**
     * The unique identifier code for a fulfillment center.
     */
    private String fcCode;

    /**
     * Instantiates a new FulfillmentCenter object.
     * @param fcCode - the unique identifier for the new fulfillment center
     */
    public FulfillmentCenter(String fcCode) {

        this.fcCode = fcCode;
    }

    public String getFcCode() {
        return fcCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FulfillmentCenter that = (FulfillmentCenter) obj;
        return Objects.equals(fcCode, that.fcCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fcCode);
    }
}
