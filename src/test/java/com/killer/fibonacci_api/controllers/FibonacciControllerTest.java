package com.killer.fibonacci_api.controllers;

import org.junit.jupiter.api.Test;
import com.killer.fibonacci_api.model.FibonacciNumber;
import com.killer.fibonacci_api.services.FibonacciService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FibonacciService fibonacciService;

    @InjectMocks
    private FibonacciController fibonacciController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fibonacciController).build();
    }

    @Test
    public void testGetFibonacci() throws Exception {
        int n = 10;
        FibonacciNumber fibonacciNumber = new FibonacciNumber(n, "55", 1);

        when(fibonacciService.calculateFibonacci(n)).thenReturn(fibonacciNumber);

        mockMvc.perform(get("/fibonacci/{n}", n)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", is("55")))
                .andExpect(jsonPath("$.count", is(1)));

        verify(fibonacciService).calculateFibonacci(n);
    }

    @Test
    public void testGetFibonacciMostConsumed() throws Exception {
        FibonacciNumber mostConsumedFibonacciNumber = new FibonacciNumber(10, "55", 10);

        when(fibonacciService.mostConsulted()).thenReturn(mostConsumedFibonacciNumber);

        mockMvc.perform(get("/fibonacci/most-consumed")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", is("55")))
                .andExpect(jsonPath("$.count", is(10)));

        verify(fibonacciService).mostConsulted();
    }
    @Test
    void getFibonacci() {
    }

    @Test
    void getFibonacciMostConsumed() {
    }
}