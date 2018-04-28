package zhuLi.dao

import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Test
import zhuLi.domain.Source
import java.time.LocalDate

class JsonSourceListDaoTest {

    @Test
    fun serializeToJsonAndParse() {
        val testSources = listOf(
            Source(1, "Top research", LocalDate.of(1981, 12, 4), "", "ArXiv"),
            Source(2, "Important paper", LocalDate.of(2001, 1, 23), "", "ArXiv"),
            Source(3, "Pointless publication", LocalDate.of(1989, 5, 23), "", "ArXiv"),
            Source(4, "Awful article", LocalDate.of(1998, 8, 11), "", "ArXiv")
        )

        val tempFile = createTempFile("tmp", ".json")
        val dao = JsonSourceListDao(tempFile)

        dao.save(testSources)

        val newSources = dao.load()

        val pairs = testSources.zip(newSources)

        pairs.forEach({ a -> assertTrue(a.first == a.second) })
    }

    @After
    fun tearDown() {
    }
}