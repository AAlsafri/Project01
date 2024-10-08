package com.nashss.se.sustainabilityshipmentservice.types;

import java.util.Objects;

/**
 * How to fulfill, package, and ship an item.
 */
public class ShipmentOption {
    /**
     * The item to ship.
     */
    private Item item;

    /**
     * The packaging option used.
     */
    private Packaging packaging;

    /**
     * The fulfillment center shipping the item.
     */
    private FulfillmentCenter fulfillmentCenter;

    private ShipmentOption(Builder builder) {
        item = builder.item;
        packaging = builder.packaging;
        fulfillmentCenter = builder.fulfillmentCenter;
    }

    /**
     * Returns a new ShipmentOption.Builder object for constructing a ShipmentOption.
     *
     * @return new builder ready for constructing a ShipmentOption
     */
    public static Builder builder() {
        return new Builder();
    }

    public Item getItem() {
        return item;
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public FulfillmentCenter getFulfillmentCenter() {
        return fulfillmentCenter;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ShipmentOption that = (ShipmentOption) obj;
        return Objects.equals(packaging, that.packaging) &&
                Objects.equals(item, that.item) &&
                Objects.equals(fulfillmentCenter, that.fulfillmentCenter);
    }



    @Override
    public int hashCode() {
        // Implement hashCode considering all relevant fields
        return Objects.hash(item, fulfillmentCenter, packaging);
    }

    /**
     * {@code ShipmentOption} builder static inner class.
     */
    public static class Builder {
        private Item item;
        private Packaging packaging;
        private FulfillmentCenter fulfillmentCenter;

        private Builder() {
        }

        /**
         * Sets the {@code item} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param itemToUse the {@code item} to set
         * @return a reference to this Builder
         */
        public Builder withItem(Item itemToUse) {
            this.item = itemToUse;
            return this;
        }

        /**
         * Sets the {@code packaging} and returns a reference to this Builder so that the methods can be chained
         * together.
         *
         * @param packagingToUse the {@code packaging} to set
         * @return a reference to this Builder
         */
        public Builder withPackaging(Packaging packagingToUse) {
            this.packaging = packagingToUse;
            return this;
        }

        /**
         * Sets the {@code fulfillmentCenter} and returns a reference to this Builder so that the methods can be
         * chained together.
         *
         * @param fulfillmentCenterToUse the {@code fulfillmentCenter} to set
         * @return a reference to this Builder
         */
        public Builder withFulfillmentCenter(FulfillmentCenter fulfillmentCenterToUse) {
            this.fulfillmentCenter = fulfillmentCenterToUse;
            return this;
        }

        /**
         * Returns a {@code ShipmentOption} built from the parameters previously set.
         *
         * @return a {@code ShipmentOption} built with parameters of this {@code ShipmentOption.Builder}
         */
        public ShipmentOption build() {
            return new ShipmentOption(this);
        }
    }
}
