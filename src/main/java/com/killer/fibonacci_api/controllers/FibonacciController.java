package com.killer.fibonacci_api.controllers;

import com.killer.fibonacci_api.model.FibonacciNumber;
import com.killer.fibonacci_api.services.FibonacciService;
//import com.killer.fibonacci_api.services.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @GetMapping("/{n}")
    public ResponseEntity<?> getFibonacci(@PathVariable @Validated int n) {
        try {
            if (n <= 0) {
                throw new RuntimeException("Fibonacci number cannot be negative");
            }
            FibonacciNumber result = fibonacciService.calculateFibonacci(n);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @GetMapping("/most-consumed")
    public FibonacciNumber getFibonacciMostConsumed() {
        return fibonacciService.mostConsulted();
    }
}
