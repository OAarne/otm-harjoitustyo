package zhuLi.ui.controllers

import org.junit.Test
import zhuLi.domain.Source
import zhuLi.domain.SourceListService
import zhuLi.domain.SourceType
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SourceListServiceTest {

    @Test
    fun addAndRemoveSourceTest() {
        val service = SourceListService()
        val testSource = Source("Test Source", listOf("Generic MacResearcher"), SourceType.ARTICLE, LocalDate.of(2010, 10, 10), "some bibtex", "Elsevier", "Nature")
        service.addSource(testSource)
        assertEquals(testSource, service.sources.last())
        service.deleteSource(testSource)
        assertNotEquals(testSource, service.sources.last())
    }
}