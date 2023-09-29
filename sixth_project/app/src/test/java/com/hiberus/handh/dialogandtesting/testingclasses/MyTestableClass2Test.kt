package com.hiberus.handh.dialogandtesting.testingclasses

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MyTestableClass2Test{
    @Mock
    private val testableClass: MyTestableClass = mock()
    @Mock
    private val api: MyTestableClass.MiSuperApiCall = mock()

    private lateinit var sut: MyTestableClass2

    @BeforeEach
    fun onSetup(){
        sut = MyTestableClass2(testableClass, api)
    }

    @DisplayName("when api returns test1 should return \"Mi Valor Test 1\"")
    @Test
    fun whenApiReturnsTest1ShouldReturnMiValortest1(){
        // Given
        whenever(api.getTesteableValue()).thenReturn(MyTestableClass.TestableValues.Test1)
        // When
        sut.getValue()
        // Then
        verify(testableClass).myTestableFunction(MyTestableClass.TestableValues.Test1)
    }

    @DisplayName("when api returns test1 do not execute myTestableFunction with value not equal to Test1")
    @Test
    fun whenApiReturnsTest1ShouldReturnMiValortest2(){
        // Given
        whenever(api.getTesteableValue()).thenReturn(MyTestableClass.TestableValues.Test1)
        // When
        sut.getValue()
        // Then
        verify(testableClass, never()).myTestableFunction(MyTestableClass.TestableValues.Test2)
    }

    @DisplayName("when api returns test1 do not execute myTestableFunction with value not equal to Test1")
    @Test
    fun whenApiReturnsTest1ShouldReturnMiValortest1Suspend() = runTest {
            // Given
            whenever(api.getTestableValue2()).thenReturn(MyTestableClass.TestableValues.Test1)
            whenever(testableClass.myTestableFunction(any())).thenReturn("Hola que tal")
            // When
            val value = sut.isCheckedApi()
            // Then
            Assertions.assertTrue(value)
        }

}