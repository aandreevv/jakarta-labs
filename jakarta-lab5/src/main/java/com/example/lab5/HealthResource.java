package com.example.lab5;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/health")
public class HealthResource {
    @GET
    @Produces("text/plain")
    public String health() {
        return "Service is healthy";
    }
}