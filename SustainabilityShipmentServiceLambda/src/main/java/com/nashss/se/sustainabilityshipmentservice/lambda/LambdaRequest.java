package com.nashss.se.sustainabilityshipmentservice.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.sustainabilityshipmentservice.utils.MapToPojoConverter;

/**
 * Represents a generic "APIGateway" request made to a lambda function.
 * @param <T> The type of the concrete request that should be created from this LambdaRequest
 */
public class LambdaRequest<T> extends APIGatewayProxyRequestEvent {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Dserialize a T (aka 'requestClass`) from the body of the request
     * @param requestClass The type that should be created from the body of this LambdaRequest
     * @return A new instance of T that contains data from the request body
     */
    public T fromBody(Class<T> requestClass) {
        try {
            return MAPPER.readValue(super.getBody(), requestClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    String.format("Unable to deserialize %s from request body", requestClass.getSimpleName()),
                    e);
        }
    }

    /**
     * Use the given converter to create an instance of T from the request's query string
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's query string
     */
    public T fromQuery(MapToPojoConverter<T> converter) {
        return converter.convert(super.getQueryStringParameters());
    }

    /**
     * Use the given converter to create an instance of T from the request's path parameters
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's path parameters
     */
    public T fromPath(MapToPojoConverter<T> converter) {
        return converter.convert(super.getPathParameters());
    }
}

