package com.nashss.se.sustainabilityshipmentservice.dependency;

import com.nashss.se.sustainabilityshipmentservice.activity.PrepareShipmentActivity;
import com.nashss.se.sustainabilityshipmentservice.cost.CostStrategy;
import com.nashss.se.sustainabilityshipmentservice.cost.MonetaryCostStrategy;
import com.nashss.se.sustainabilityshipmentservice.dao.PackagingDAO;
import com.nashss.se.sustainabilityshipmentservice.datastore.PackagingDatastore;
import com.nashss.se.sustainabilityshipmentservice.service.ShipmentService;

import java.util.Objects;

/**
 * Class that provides our service's dependencies.
 *
 * This class acts as a form of simple dependency injection.
 *
 * PARTICIPANTS: We will replace this with Dagger as part of the project
 */
public class ServiceProvider {
    private PackagingDatastore packagingDatastore;
    private CostStrategy costStrategy;
    private ShipmentService shipmentService;
    private PackagingDAO packagingDAO;

    /**
     * Provides a new PrepareShipmentActivity with injected dependencies.
     *
     * @return a new PrepareShipmentActivity with injected dependencies
     */
    public PrepareShipmentActivity providePrepareShipmentActivity() {
        return new PrepareShipmentActivity(provideShipmentService());
    }

    /**
     * Lazily provides a {@link ShipmentService} singleton instance.
     *
     * @return a {@link ShipmentService} instance
     */
    private ShipmentService provideShipmentService() {
        if (shipmentService == null) {
            shipmentService = new ShipmentService(providePackagingDAO(), provideCostStrategy());
        }
        return shipmentService;
    }

    /**
     * Lazily provides a {@link PackagingDAO} singleton instance.
     *
     * @return a {@link PackagingDAO} instance
     */
    private PackagingDAO providePackagingDAO() {
        if (packagingDAO == null) {
            packagingDAO = new PackagingDAO(providePackagingDatastore());
        }

        return packagingDAO;
    }


    /**
     * Lazily provides a {@link CostStrategy} singleton instance.
     *
     * @return a {@link CostStrategy} instance
     */
    private CostStrategy provideCostStrategy() {
        if (Objects.isNull(costStrategy)) {
            costStrategy = new MonetaryCostStrategy();
        }
        return costStrategy;
    }


    /**
     * Lazily provides a {@link PackagingDatastore} singleton instance.
     *
     * @return a {@link PackagingDatastore} instance
     */
    private PackagingDatastore providePackagingDatastore() {
        if (Objects.isNull(packagingDatastore)) {
            packagingDatastore = new PackagingDatastore();
        }

        return packagingDatastore;
    }
}
