package com.hiberus.handh.dialogandtesting.testingclasses

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@TestMethodOrder(OrderAnnotation::class)
class MyTestableClassTest {

    // sut = System Under Test
    private lateinit var sut: MyTestableClass

    @BeforeEach
    fun onSetup(){
        sut = MyTestableClass()
    }

    @Order(1)
    @DisplayName("when value is Test1 should return \"Mi Valor test 1\"")
    @Test
    fun whenValueIsTest1ShouldReturnMiValortest1(){
        // When
        val returnValue = sut.myTestableFunction(MyTestableClass.TestableValues.Test1)
        //Then
        Assertions.assertEquals("Mi Valor test 1", returnValue)
    }
    @Order(2)
    @DisplayName("when value is Test2 should return \"Mi Valor test 2\"")
    @Test
    fun whenValueIsTest2ShouldReturnMiValortest2(){
        // When
        val returnValue = sut.myTestableFunction(MyTestableClass.TestableValues.Test2)
        //Then
        Assertions.assertEquals("Mi Valor test 2", returnValue)
    }

    @Order(3)
    @DisplayName("when value is Test3 should return \"Mi Valor test 3\"")
    @Test
    fun whenValueIsTest3ShouldReturnMiValortest3(){
        // When
        val returnValue = sut.myTestableFunction(MyTestableClass.TestableValues.Test3)
        //Then
        Assertions.assertEquals("Mi Valor test 3", returnValue)
    }
}