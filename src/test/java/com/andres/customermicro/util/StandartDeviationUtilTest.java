package com.andres.customermicro.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandartDeviationUtilTest {

    @Test
    public void standardDeviation(){
        double[] numbers = {30,40};
        assertEquals(StandartDeviationUtil.standardDeviation(numbers), 5);
    }

    @Test
    public void standardDeviation2(){
        double[] numbers = {};
        assertEquals(StandartDeviationUtil.standardDeviation(numbers), Double.NaN);
    }
}
