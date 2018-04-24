package zhuLi.ui.controllers

import org.junit.Test
import zhuLi.domain.Source
import java.util.Date
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class SourceControllerTest {

    @Test
    fun addAndRemoveSourceTest() {
        val controller = SourceController()
        val testSource = Source(1, "Test Source", Date(10, 10, 2010), "some bibtex", "Elsevier")
        controller.addSource(testSource)
        assertEquals(testSource, controller.sources.last())
        controller.deleteSource(testSource)
        assertNotEquals(testSource, controller.sources.last())
    }
}