package com.nashss.se.sustainabilityshipmentservice.utils;

import java.util.Map;

/**
 * Functional interface for converting a Map<String, String> into a POJO
 * @param <T> The type of the POGO
 */
public interface MapToPojoConverter<T> {

    /**
     * Convert map into an instance of T
     * @param map Map<String, String> to be converted
     * @return An instance of T
     */
    T convert(Map<String, String> map);
}
