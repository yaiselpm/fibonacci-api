package com.killer.fibonacci_api.services;

import com.killer.fibonacci_api.model.FibonacciArray;
import com.killer.fibonacci_api.model.FibonacciNumber;
import com.killer.fibonacci_api.repositories.FibonacciArrayRepository;
import com.killer.fibonacci_api.repositories.FibonacciNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FibonacciServiceTest {

    @Mock
    private FibonacciNumberRepository fibonacciNumberRepository;

    @Mock
    private FibonacciArrayRepository fibonacciArrayRepository;

    @InjectMocks
    private FibonacciService fibonacciService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateFibonacciCached() {
        int n = 10;
        FibonacciNumber cachedFibonacciNumber = new FibonacciNumber(n, "55", 1);

        when(fibonacciNumberRepository.existsById(n)).thenReturn(true);
        when(fibonacciNumberRepository.getReferenceById(n)).thenReturn(cachedFibonacciNumber);

        FibonacciNumber result = fibonacciService.calculateFibonacci(n);

        assertEquals(cachedFibonacciNumber, result);
        verify(fibonacciNumberRepository).existsById(n);
        verify(fibonacciNumberRepository).getReferenceById(n);
        verify(fibonacciNumberRepository).save(cachedFibonacciNumber);
        verifyNoInteractions(fibonacciArrayRepository); // No debería interactuar con la repositorio de arrays
    }

    @Test
    public void testCalculateFibonacciNotCached() {
        int n = 10;
        List<BigInteger> intermediateResults = new ArrayList<>();
        BigInteger expectedResult = BigInteger.valueOf(55);

        // Configura el comportamiento de los mocks
        when(fibonacciNumberRepository.existsById(n)).thenReturn(false);
        when(fibonacciArrayRepository.save(any(FibonacciArray.class))).thenReturn(new FibonacciArray());

        FibonacciNumber result = fibonacciService.calculateFibonacci(n);

        assertEquals(expectedResult.toString(), result.getNumber()); // Verifica la conversión a String
        assertEquals(1, result.getCount());

        // Verifica que se haya llamado a los métodos necesarios
        verify(fibonacciNumberRepository).existsById(n);
        verify(fibonacciNumberRepository).save(any(FibonacciNumber.class));
        verify(fibonacciArrayRepository).save(any(FibonacciArray.class));
    }

    @Test
    public void testFib() {
        int n = 5;
        List<BigInteger> intermediateResults = new ArrayList<>();
        BigInteger expectedResult = BigInteger.valueOf(5);

        BigInteger result = fibonacciService.fib(n, intermediateResults);

        assertEquals(expectedResult, result);
        assertEquals(List.of(
                BigInteger.ZERO,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(5)
        ), intermediateResults);
    }

    @Test
    public void testConvertBigIntegerToString() {
        List<BigInteger> bigIntegerList = List.of(
                BigInteger.ZERO,
                BigInteger.ONE,
                BigInteger.valueOf(2)
        );
        List<String> expectedList = List.of(
                "0",
                "1",
                "2"
        );

        List<String> result = fibonacciService.convertBigIntegerToString(bigIntegerList);

        assertEquals(expectedList, result);
    }

    @Test
    public void testMostConsulted() {
        FibonacciNumber mostConsultedFibonacciNumber = new FibonacciNumber(10, "55", 10);

        when(fibonacciNumberRepository.findTopByOrderByCountDesc()).thenReturn(mostConsultedFibonacciNumber);

        FibonacciNumber result = fibonacciService.mostConsulted();

        assertEquals(mostConsultedFibonacciNumber, result);
        verify(fibonacciNumberRepository).findTopByOrderByCountDesc();
    }
    @Test
    void calculateFibonacci() {
    }

    @Test
    void fib() {
    }

    @Test
    void mostConsulted() {
    }

}