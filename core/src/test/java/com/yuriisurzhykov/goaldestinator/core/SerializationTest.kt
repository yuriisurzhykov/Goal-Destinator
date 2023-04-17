package com.yuriisurzhykov.goaldestinator.core

import com.yuriisurzhykov.goaldestinator.core.data.Serialization
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideGson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SerializationTest {

    private lateinit var serialization: Serialization

    @Before
    fun init() {
        serialization = Serialization.Base(ProvideGson.Base())
    }

    @Test
    fun `test string serialization`() {
        val actual = serialization.toJson("sourceString")
        val expected = "\"sourceString\""
        assertEquals(expected, actual)
    }

    @Test
    fun `test number serialization`() {
        val actual = serialization.toJson(14)
        val expected = "14"
        assertEquals(expected, actual)
    }

    @Test
    fun `test complex data object serialization`() {
        val valueToSave = TestDTO("Yurii", 22)
        val expectedJson = "{\"name\":\"Yurii\",\"age\":22}"
        assertEquals(expectedJson, serialization.toJson(valueToSave))
    }
}

private data class TestDTO(
    private val name: String,
    private val age: Int
)
