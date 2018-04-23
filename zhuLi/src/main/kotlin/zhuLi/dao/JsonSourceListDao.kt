package zhuLi.dao

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import com.squareup.moshi.Types
import zhuLi.domain.Source
import java.io.File
import java.util.Date

class JsonSourceListDao(val file: File) : SourceListDao {

    override val sources: List<Source>

    val listType = Types.newParameterizedType(List::class.java, Source::class.java)

    val moshiSource = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    val sourceAdapter = moshiSource.adapter(Source::class.java)

    val moshiList = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Source::class.java, sourceAdapter)
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    val listAdapter: JsonAdapter<List<Source>> = moshiList.adapter(listType)

    init {
        sources = load()
    }

    fun test() {
        var testSources = listOf(
            Source(1, "Top research", Date(1981, 12, 4), "", "ArXiv"),
            Source(2, "Important paper", Date(2001, 1, 23), "", "ArXiv"),
            Source(3, "Pointless publication", Date(1989, 5, 23), "", "ArXiv"),
            Source(4, "Awful article", Date(1998, 8, 11), "", "ArXiv")
        )
        var jsonList = listAdapter.toJson(testSources)
        var jsonSource = sourceAdapter.toJson(Source(1, "Top research", Date(1981, 12, 4), "", "ArXiv"))
        file.printWriter().use { out ->
            out.println(jsonList)
        }
    }

    override fun load(): List<Source> {
        val jsonList = file.readText()
        val sourceList = listAdapter.fromJson(jsonList)
        return sourceList!!
    }

    override fun save(sources: List<Source>) {
        var jsonList = listAdapter.toJson(sources)
        file.printWriter().use { out ->
            out.println(jsonList)
        }
    }
}
