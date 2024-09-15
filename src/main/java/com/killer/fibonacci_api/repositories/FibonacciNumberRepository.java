package com.killer.fibonacci_api.repositories;

import com.killer.fibonacci_api.model.FibonacciNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FibonacciNumberRepository extends JpaRepository<FibonacciNumber, Integer> {


    FibonacciNumber findTopByOrderByCountDesc();
}