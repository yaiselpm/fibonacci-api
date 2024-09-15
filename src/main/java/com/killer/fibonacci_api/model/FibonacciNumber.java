package com.killer.fibonacci_api.model;

//import com.killer.fibonacci_api.services.Node;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "fibonacci_number")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class FibonacciNumber {
    @Id
    @Column(unique = true, name = "id", nullable = false)
    private int id;
    @Column(length = 1000, name = "number")
    private String number;
    private int count;

}