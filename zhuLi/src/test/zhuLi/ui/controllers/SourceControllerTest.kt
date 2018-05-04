package zhuLi.ui.controllers

import org.junit.Test
import zhuLi.domain.Source
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SourceControllerTest {

    @Test
    fun addAndRemoveSourceTest() {
        val controller = SourceController()
        val testSource = Source("Test Source", listOf("Generic MacResearcher"), LocalDate.of(2010, 10, 10), "some bibtex", "Elsevier")
        controller.addSource(testSource)
        assertEquals(testSource, controller.sources.last())
        controller.deleteSource(testSource)
        assertNotEquals(testSource, controller.sources.last())
    }
}