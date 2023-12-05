package com.nashss.se.sustainabilityshipmentservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.sustainabilityshipmentservice.activity.PrepareShipmentRequest;
import com.nashss.se.sustainabilityshipmentservice.activity.PrepareShipmentResponse;
import com.nashss.se.sustainabilityshipmentservice.dependency.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The entry-point to the application.
 */
public class PrepareShipmentServiceLambda
        implements RequestHandler<LambdaRequest<PrepareShipmentRequest>, LambdaResponse> {

    private ServiceProvider provider;

    /**
     * The method that will be called to invoke this lambda function
     * @param input The Lambda Function input
     * @param context The Lambda execution environment context object.
     * @return A LambdaResponse containing the results of the lambda function
     */
    @Override
    public LambdaResponse handleRequest(LambdaRequest<PrepareShipmentRequest> input, Context context) {
        try {
            PrepareShipmentRequest prepareShipmentRequest = input.fromQuery((query) ->
                    PrepareShipmentRequest.builder()
                            .withFcCode(query.get("fcCode"))
                            .withItemAsin(query.get("itemAsin"))
                            .withItemDescription(query.get("itemDescription"))
                            .withItemLength(query.get("itemLength"))
                            .withItemWidth(query.get("itemWidth"))
                            .withItemHeight(query.get("itemHeight"))
                            .build());

            PrepareShipmentResponse prepareShipmentResponse =
                    getProvider().providePrepareShipmentActivity().handleRequest(prepareShipmentRequest);
            return LambdaResponse.success(prepareShipmentResponse);
        } catch (Exception e) {
            return LambdaResponse.error(e);
        }
    }

    private ServiceProvider getProvider() {
        if (provider == null) {
            provider = new ServiceProvider();
        }
        return provider;
    }
}
