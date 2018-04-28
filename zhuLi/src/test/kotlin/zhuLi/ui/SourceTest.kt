package zhuLi.ui

import org.junit.Assert.assertTrue
import org.junit.Test
import zhuLi.domain.Source
import java.time.LocalDate

class SourceTest {
    @Test
    fun sourceTest() {
        val source = Source(1, "TestSource", LocalDate.now(), "curly braces go here", "Capitalist Pigs Inc.")
        assertTrue(source.pubDate <= LocalDate.now())
    }
}
