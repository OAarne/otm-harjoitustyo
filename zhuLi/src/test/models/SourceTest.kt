package models

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDate

class SourceTest {
    @Test
    fun sourceTest() {
        val source = Source(1, "TestSource", listOf("Top Scientist"), LocalDate.now(), "curly braces go here", "Capitalist Pigs Inc.")
        assertTrue(source.pubDate <= LocalDate.now())
    }
}

