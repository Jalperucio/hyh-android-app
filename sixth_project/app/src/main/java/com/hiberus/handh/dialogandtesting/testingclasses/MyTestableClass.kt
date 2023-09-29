package com.hiberus.handh.dialogandtesting.testingclasses

open class MyTestableClass {
    enum class TestableValues{
        Test1,
        Test2,
        Test3
    }

    interface MiSuperApiCall {
        fun getTesteableValue(): TestableValues
        suspend fun getTestableValue2(): TestableValues
    }

    open fun myTestableFunction(value: TestableValues): String =
        when(value){
            TestableValues.Test1 -> "Mi Valor test 1"
            TestableValues.Test2 -> "Mi Valor test 2"
            TestableValues.Test3 -> "Mi Valor test 3"
        }
}

open class MyTestableClass2 (
    private val testableClass: MyTestableClass,
    private val api: MyTestableClass.MiSuperApiCall
){
    fun getValue(): String =
        testableClass.myTestableFunction(api.getTesteableValue())

    suspend fun isCheckedApi(): Boolean =
        testableClass.myTestableFunction(api.getTestableValue2()).isNotEmpty()

}