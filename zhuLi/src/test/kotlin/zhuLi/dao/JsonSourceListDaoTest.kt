package zhuLi.dao

import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Test

class JsonSourceListDaoTest {

    @Test
    fun serializeToJsonAndParse() {
        val tempFile = createTempFile("tmp", ".json")
        val dao = JsonSourceListDao(tempFile)

        val testSources = dao.generateSampleSourceList()
        dao.save(testSources)

        val newSources = dao.load()

        val pairs = testSources.zip(newSources)

        pairs.forEach({ a -> assertTrue(a.first == a.second) })
    }

    @After
    fun tearDown() {
    }
}