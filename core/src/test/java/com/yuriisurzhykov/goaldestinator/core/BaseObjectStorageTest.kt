package com.yuriisurzhykov.goaldestinator.core

import com.yuriisurzhykov.goaldestinator.core.data.Serialization
import com.yuriisurzhykov.goaldestinator.core.data.cache.ObjectStorage
import com.yuriisurzhykov.goaldestinator.core.data.cache.StringStorage
import com.yuriisurzhykov.goaldestinator.core.data.retrofit.ProvideGson
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseObjectStorageTest {

    @Test
    fun `put value to storage and check save`() {
        val provideGson = ProvideGson.Base()
        val serialization = Serialization.Base(provideGson)
        val stringStorage = TestStringStorage()
        val key = "SaveKey"
        val objectStorage = ObjectStorage.Base(serialization, stringStorage)
        val valueToSave = TestDataObject("Yurii", 22)
        objectStorage.save(key, valueToSave)
        val json = "{\"name\":\"Yurii\",\"age\":22}"
        val readValue = objectStorage.read(key, TestDataObject("Yurii", 22))
        assertEquals(valueToSave, readValue)
        assertEquals(stringStorage.read(key, json), serialization.toJson(readValue))
    }
}

private data class TestDataObject(
    private val name: String,
    private val age: Int
)

private class TestStringStorage : StringStorage {

    val map = mutableMapOf<String, String>()

    override fun save(key: String, value: String) {
        map[key] = value
    }

    override fun read(key: String, default: String): String {
        return map[key] ?: default
    }
}