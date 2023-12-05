package com.nashss.se.sustainabilityshipmentservice.types;

/**
 * Represents a pairing between a packaging option and a fulfillment center that supports that packaging option.
 */
public class FcPackagingOption {

    /**
     * The fulfillment center we are providing packaging information about.
     */
    private FulfillmentCenter fulfillmentCenter;

    /**
     * A packaging that is available at the fulfillment center.
     */
    private Packaging packaging;


    /**
     * Instantiates a new FcPackagingOption object.
     * @param fulfillmentCenter - the FC where the packaging Option is available
     * @param packaging - the packaging option available at the provided FC
     */
    public FcPackagingOption(FulfillmentCenter fulfillmentCenter, Packaging packaging) {
        this.fulfillmentCenter = fulfillmentCenter;
        this.packaging = packaging;
    }

    public FulfillmentCenter getFulfillmentCenter() {
        return fulfillmentCenter;
    }

    public Packaging getPackaging() {
        return packaging;
    }
}
