package com.nashss.se.sustainabilityshipmentservice.datastore;

import com.nashss.se.sustainabilityshipmentservice.types.Box;
import com.nashss.se.sustainabilityshipmentservice.types.FcPackagingOption;
import com.nashss.se.sustainabilityshipmentservice.types.FulfillmentCenter;
import com.nashss.se.sustainabilityshipmentservice.types.Packaging;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions;

    /**
     * PackagingDatastore Constructor.
     */
    public PackagingDatastore() {
        fcPackagingOptions = Arrays.asList(
                createBoxFcPackagingOption("IND1", "10", "10", "10"),
                createBoxFcPackagingOption("ABE2", "20", "20", "20"),
                createBoxFcPackagingOption("ABE2", "40", "40", "40"),
                createBoxFcPackagingOption("YOW4", "10", "10", "10"),
                createBoxFcPackagingOption("YOW4", "20", "20", "20"),
                createBoxFcPackagingOption("YOW4", "60", "60", "60"),
                createBoxFcPackagingOption("IAD2", "20", "20", "20"),
                createBoxFcPackagingOption("IAD2", "20", "20", "20"),
                createBoxFcPackagingOption("PDX1", "40", "40", "40"),
                createBoxFcPackagingOption("PDX1", "60", "60", "60"),
                createBoxFcPackagingOption("PDX1", "60", "60", "60")
        );
    }

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createBoxFcPackagingOption(String fcCode, String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Box(new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
