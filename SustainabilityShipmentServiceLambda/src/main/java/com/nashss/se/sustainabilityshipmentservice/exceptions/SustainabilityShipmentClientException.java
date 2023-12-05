package com.nashss.se.sustainabilityshipmentservice.exceptions;

public class SustainabilityShipmentClientException extends RuntimeException {
    public SustainabilityShipmentClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SustainabilityShipmentClientException(String message) {
        this(message, null);
    }
}
