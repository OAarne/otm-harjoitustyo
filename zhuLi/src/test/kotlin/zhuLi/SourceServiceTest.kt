package zhuLi

import org.junit.Test
import zhuLi.domain.Source
import zhuLi.domain.SourceService
import zhuLi.domain.SourceType
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SourceServiceTest {

    @Test
    fun addAndRemoveSourceTest() {
        val tempConfig = createTempFile("config", ".properties")
        tempConfig.writeText("sourceFile=tmp.json")

        createTempFile("tmp", ".json")
        val service = SourceService()

        val testSource = Source("Test Source", listOf("Generic MacResearcher"), SourceType.ARTICLE, LocalDate.of(2010, 10, 10), "Concrete applications of concrete", "some bibtex", "Elsevier", "Nature")
        service.addSource(testSource)
        assertEquals(testSource, service.sources.last())
        service.deleteSource(testSource)
        assertNotEquals(testSource, service.sources.last())
    }

    @Test
    fun useProperties() {
        val tempConfig = createTempFile("config", ".properties")
        tempConfig.writeText("sourceFile=altTempSources.json")
        val tempSourceFile = createTempFile("altTempSources.json")
        tempSourceFile.writeText("""[{"title":"Top research","authors":["Eminent Expert"],"type":"ARTICLE","pub_date":"1981-12-04","abstract":"five dollar words.","bibtex":"","publisher":"ArXiv","publication":"A Journal","add_date":"2018-05-11"}]""")
        val service = SourceService()
        assertEquals("Top research", service.sources.get(0).title)
    }

    @Test
    fun initializeWithoutProperties() {
        val tempSourceFile = createTempFile("sources.json")
        tempSourceFile.writeText("""[{"title":"Top research","authors":["Eminent Expert"],"type":"ARTICLE","pub_date":"1981-12-04","abstract":"five dollar words.","bibtex":"","publisher":"ArXiv","publication":"A Journal","add_date":"2018-05-11"}]""")

        val service = SourceService()
        assertEquals("Top research", service.sources.get(0).title)
    }
}