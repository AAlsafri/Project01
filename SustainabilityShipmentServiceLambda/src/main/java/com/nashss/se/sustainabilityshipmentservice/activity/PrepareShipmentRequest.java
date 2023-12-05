package com.nashss.se.sustainabilityshipmentservice.activity;

/**
 * A request for a shipment option
 */
public class PrepareShipmentRequest {
    private String fcCode;
    private String itemAsin;
    private String itemDescription;
    private String itemLength;
    private String itemWidth;
    private String itemHeight;

    private PrepareShipmentRequest(String fcCode, String itemAsin, String itemDescription, String itemLength, String itemWidth, String itemHeight) {
        this.fcCode = fcCode;
        this.itemAsin = itemAsin;
        this.itemDescription = itemDescription;
        this.itemLength = itemLength;
        this.itemWidth = itemWidth;
        this.itemHeight = itemHeight;
    }

    public String getFcCode() {
        return fcCode;
    }

    public String getItemAsin() {
        return itemAsin;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemLength() {
        return itemLength;
    }

    public String getItemWidth() {
        return itemWidth;
    }

    public String getItemHeight() {
        return itemHeight;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String fcCode;
        private String itemAsin;
        private String itemDescription;
        private String itemLength;
        private String itemWidth;
        private String itemHeight;

        public Builder withFcCode(String fcCode) {
            this.fcCode = fcCode;
            return this;
        }

        public Builder withItemAsin(String itemAsin) {
            this.itemAsin = itemAsin;
            return this;
        }

        public Builder withItemDescription(String itemDescription) {
            this.itemDescription = itemDescription;
            return this;
        }

        public Builder withItemLength(String itemLength) {
            this.itemLength = itemLength;
            return this;
        }

        public Builder withItemWidth(String itemWidth) {
            this.itemWidth = itemWidth;
            return this;
        }

        public Builder withItemHeight(String itemHeight) {
            this.itemHeight = itemHeight;
            return this;
        }

        public PrepareShipmentRequest build() {
            return new PrepareShipmentRequest(fcCode, itemAsin, itemDescription, itemLength, itemWidth, itemHeight);
        }
    }
}