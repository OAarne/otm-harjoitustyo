package zhuLi.dao

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import zhuLi.domain.Source
import java.io.File
import java.io.IOException
import java.time.LocalDate

class JsonSourceListDao(val file: File) : SourceListDao {

    override val sources: List<Source>

    val listType = Types.newParameterizedType(List::class.java, Source::class.java)

    val moshiSource = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(LocalDate::class.java, LocalDateJsonAdapter().nullSafe())
        .build()

    val sourceAdapter = moshiSource.adapter(Source::class.java)

    val moshiList = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Source::class.java, sourceAdapter)
        .add(LocalDate::class.java, LocalDateJsonAdapter().nullSafe())
        .build()

    val listAdapter: JsonAdapter<List<Source>> = moshiList.adapter(listType)

    init {
        sources = load()
    }

//    fun generateSampleSources() {
//        var testSources = listOf(
//            Source(1, "Top research", LocalDate.of(1981, 12, 4), "", "ArXiv"),
//            Source(2, "Important paper", LocalDate.of(2001, 1, 23), "", "ArXiv"),
//            Source(3, "Pointless publication", LocalDate.of(1989, 5, 23), "", "ArXiv"),
//            Source(4, "Awful article", LocalDate.of(1998, 8, 11), "", "ArXiv")
//        )
//        var jsonList = listAdapter.toJson(testSources)
//        var jsonSource = sourceAdapter.toJson(Source(1, "Top research", LocalDate.of(1981, 12, 4), "", "ArXiv"))
//        file.printWriter().use { out ->
//            out.println(jsonList)
//        }
//    }

    override fun load(): List<Source> {
        try {
            val jsonList = file.readText()

            val sourceList = listAdapter.fromJson(jsonList)

            return sourceList!!
        } catch (e: IOException) {
            return listOf()
        }
    }

    override fun save(sources: List<Source>) {
        var jsonList = listAdapter.toJson(sources)
        file.printWriter().use { out ->
            out.println(jsonList)
        }
    }
}

class LocalDateJsonAdapter() : JsonAdapter<LocalDate>() {
    override fun toJson(writer: JsonWriter?, localDate: LocalDate?) {
        writer?.value(localDate.toString())
    }

    override fun fromJson(reader: JsonReader?): LocalDate? {
        return LocalDate.parse(reader?.nextString())
    }

//    @ToJson
//    fun toJson(localDate: LocalDate?): String {
////        val date = Date.from(value?.atStartOfDay(ZoneId.systemDefault())?.toInstant())
////        Rfc3339DateJsonAdapter.toJson()
//        return localDate.toString()
//    }
//
//    @FromJson
//    fun fromJson(localDate: String): LocalDate? {
//        return LocalDate.parse(localDate)
//    }
}
