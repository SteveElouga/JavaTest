package com.openclassrooms.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    private Calculator calculatorUnderTest;

    private static Instant startedAt;

    @BeforeAll
    public static void initStartingTime(){
        System.out.println("Appel avant tous les tests");
        startedAt = Instant.now();
    }

    @AfterAll
    public static void showTestDuration(){
        System.out.println("Appel apres tous les tests");
        Instant endAt = Instant.now();
        long duration = Duration.between(startedAt, endAt).toMillis();
        System.out.println(MessageFormat.format("Duree des tests : {0} ms", duration));
    }

    @BeforeEach
    public void initCalculator(){
        calculatorUnderTest = new Calculator();
        System.out.println("Appel avant chaque test");
    }

    @AfterEach
    public void underCalculator(){
        System.out.println("Appel apres chaque test");
        calculatorUnderTest = null;
    }
    @ParameterizedTest(name = "{0} + {1} doit etre egal {2}")
    @CsvSource({"1,2,3", "2,3,5", "42,57,99"})
    void testAddTwoPositivsNumbers(int arg1, int arg2, int expectResult){
        //ARRANGE
        //Deja configure

        //ACT
        final int actualResult = calculatorUnderTest.add(arg1,arg2);

        //ASSERT
        assertThat(actualResult).isEqualTo(expectResult);
    }


    @ParameterizedTest(name = "{0} * 0 doit etre egal a 0")
    @ValueSource(ints = {1, 2, 5, 99, 1000})
    void testMultiplyTwoPositivsNumbers(int arg){
        //ARRANGE
        //Deja configure


        //ACT
        final int actualResult = calculatorUnderTest.multiply(arg,0);

        //AASERT
        assertThat(actualResult).isZero();
    }

    @Test
    void testNumbers(){

        int n;

        n = 1229476;

        Set<Integer> actualDigits = calculatorUnderTest.digitsSet(n);

        assertThat(actualDigits).containsExactlyInAnyOrder(1,2,4,6,7,9);

    }
}
