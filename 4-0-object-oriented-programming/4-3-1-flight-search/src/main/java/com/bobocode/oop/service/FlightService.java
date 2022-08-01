package com.bobocode.oop.service;

import com.bobocode.oop.data.FlightDao;
import com.bobocode.util.ExerciseNotCompletedException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link FlightService} provides an API that allows to manage flight numbers
 * <p>
 * todo: 1. Using {@link com.bobocode.oop.data.FlightDao} implement method {@link FlightService#registerFlight(String)}
 * todo: 2. Using {@link com.bobocode.oop.data.FlightDao} implement method {@link FlightService#searchFlights(String)}
 */
public class FlightService {

    Flight flight;

    public FlightService(Flight flight) {
        this.flight = flight;
    }

    /**
     * Adds a new flight number
     *
     * @param flightNumber a flight number to add
     * @return {@code true} if a flight number was added, {@code false} otherwise
     */
    public boolean registerFlight(String flightNumber) {

        return flight.register(flightNumber);
    }

    /**
     * Returns all flight numbers that contains a provided key.
     *
     * @param query a search query
     * @return a list of found flight numbers
     */
    public List<String> searchFlights(String query) {

        return flight.findAll().stream()
                .filter(flightNum -> flightNum.toUpperCase().contains(query.toUpperCase()))
                .collect(Collectors.toList());
    }
}
