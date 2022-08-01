package com.bobocode.oop.service;

import java.util.Set;

public interface Flight {
    boolean register(String flightNumber);

    Set<String> findAll();
}
