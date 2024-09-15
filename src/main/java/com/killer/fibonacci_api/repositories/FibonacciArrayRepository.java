package com.killer.fibonacci_api.repositories;

import com.killer.fibonacci_api.model.FibonacciArray;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
@Cacheable("fibonacciCache")
public interface FibonacciArrayRepository extends JpaRepository<FibonacciArray, Integer> {
}