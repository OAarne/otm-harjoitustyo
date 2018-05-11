package zhuLi.dao

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Test
import zhuLi.domain.Source

class JsonSourceListDaoTest {

    // Would have encapsulated the setup code, but Kotlin was being weird about it.

    @Test
    fun serializeToJsonAndParse() {
        val tempFile = createTempFile("serializin", ".json")
        val dao = JsonSourceListDao(tempFile)

        val testSources = dao.generateSampleSourceList()
        dao.save(testSources)

        val newSources = dao.load()

        val pairs = testSources.zip(newSources)

        pairs.forEach({ a -> assertTrue(a.first == a.second) })
    }

    @Test
    fun parseEmptyFile() {
        val tempFile = createTempFile("empty", ".json")
        val dao = JsonSourceListDao(tempFile)

        val noSources = dao.load()
        val expectedList: List<Source> = listOf()
        assertEquals(expectedList, noSources)
    }

    @After
    fun tearDown() {
    }
}