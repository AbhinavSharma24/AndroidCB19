package com.example.unittesting

import org.junit.Test
import org.junit.Assert.*

class CalcFareTest{
    @Test
    fun calcFare_for_0km_0min(){
        assertEquals(60f,CalcFareUtils.calcFare(0f,0))
    }
}