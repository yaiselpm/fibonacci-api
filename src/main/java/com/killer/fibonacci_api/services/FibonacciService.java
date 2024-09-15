package com.killer.fibonacci_api.services;

import com.killer.fibonacci_api.model.FibonacciArray;
import com.killer.fibonacci_api.model.FibonacciNumber;
import com.killer.fibonacci_api.repositories.FibonacciArrayRepository;
import com.killer.fibonacci_api.repositories.FibonacciNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class FibonacciService {


    private FibonacciNumberRepository fibonacciNumberRepository;
    private FibonacciArrayRepository fibonacciArrayRepository;
    @Autowired
    public FibonacciService(FibonacciNumberRepository fibonacciNumberRepository,
                            FibonacciArrayRepository fibonacciArrayRepository) {
        this.fibonacciNumberRepository = fibonacciNumberRepository;
        this.fibonacciArrayRepository = fibonacciArrayRepository;
    }


    public FibonacciNumber calculateFibonacci(int n) {
        boolean cachedResult = fibonacciNumberRepository.existsById(n);
        FibonacciArray fibonacciArray = new FibonacciArray();
        List<BigInteger> intermediateResults = new ArrayList<>();
        List<String> listToString = new ArrayList<>();
        BigInteger result = null;
        FibonacciNumber fibonacciNumber = new FibonacciNumber();
        if (cachedResult) {
            fibonacciNumber = fibonacciNumberRepository.getReferenceById(n);
            int inc = fibonacciNumber.getCount();
            inc++;
            fibonacciNumber.setCount(inc);
        } else {
            result = fib(n, intermediateResults);
            fibonacciNumber = new FibonacciNumber(n, result.toString(),1);
            listToString = convertBigIntegerToString(intermediateResults);
            fibonacciArray.setSequence(listToString);
            fibonacciArrayRepository.save(fibonacciArray);
        }
        fibonacciNumberRepository.save(fibonacciNumber);
        return fibonacciNumber;

    }
    public BigInteger fib(int n, List<BigInteger> intermediateResults) {
        if (n <= 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        // Inicializar los resultados intermedios
        List<BigInteger> fibs = new ArrayList<>();
        fibs.add(BigInteger.ZERO); // Fib(0)
        fibs.add(BigInteger.ONE);  // Fib(1// Para el caso n = 1


        // Guardar el estado inicial
        intermediateResults.add(fibs.get(0));
        intermediateResults.add(fibs.get(1));
        // Guardamos el segundo número de Fibonacci
        // Calcular la secuencia de Fibonacci hasta n
        for (int i = 2; i <= n; i++) {
            BigInteger nextFib = fibs.get(i - 1).add(fibs.get(i - 2));
            fibs.add(nextFib);
            intermediateResults.add(nextFib);
        }


        // El resultado final es a + b
        return fibs.get(n);
   }

   List<String> convertBigIntegerToString(List<BigInteger> bigIntegerList){
        List<String> resultList = new ArrayList<>(bigIntegerList.size());
        for (BigInteger number: bigIntegerList) {
            resultList.add(number.toString());
        }
        return resultList;
   }

   public FibonacciNumber mostConsulted() {
        return fibonacciNumberRepository.findTopByOrderByCountDesc();
   }

}
