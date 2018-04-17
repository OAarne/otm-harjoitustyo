package zhuLi.ui

import org.junit.Assert.assertTrue
import org.junit.Test
import zhuLi.ui.models.Source
import java.util.Date

class SourceTest {
    @Test
    fun sourceTest() {
        val source = Source(1, "TestSource", Date(), "curly braces go here", "Capitalist Pigs Inc.")
        assertTrue(source.pubDate <= Date())
    }
}
