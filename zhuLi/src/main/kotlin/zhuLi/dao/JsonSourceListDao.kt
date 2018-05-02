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

    // TODO: I know this is a mess, the author list isn't currently getting saved so this is a WIP

    val listType = Types.newParameterizedType(List::class.java, Source::class.java)
    val authorListType = Types.newParameterizedType(List::class.java, String::class.java)
    //
    val moshiAuthorList = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val authorListAdapter: JsonAdapter<List<String>> = moshiAuthorList.adapter(authorListType)

//    val moshiSource = Moshi.Builder()
//        .add(LocalDate::class.java, LocalDateJsonAdapter().nullSafe())
//        .add(File::class.java, FileJsonAdapter())
//        .add(authorListType, authorListAdapter)
//        .add(KotlinJsonAdapterFactory())
//        .build()

//    val sourceAdapter = moshiSource.adapter(Source::class.java)

    val moshiList = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
//        .add(Source::class.java, sourceAdapter)
        .add(LocalDate::class.java, LocalDateJsonAdapter().nullSafe())
        .add(File::class.java, FileJsonAdapter())
//        .add(authorListType, authorListAdapter)
        .build()

    val listAdapter: JsonAdapter<List<Source>> = moshiList.adapter(listType)

    init {
//        generateSampleSources()
        sources = load()
    }

    /**
     * Generates a sample sources file for testing purposes.
     */

    fun generateSampleSourcesFile() {
        val sampleSources = generateSampleSourceList()
        save(sampleSources)
    }

    /**
     * Generates a list of sample sources for testing purposes
     */

    //TODO: make the tests actually use this
    //TODO: add authors back in once they work
    fun generateSampleSourceList(): List<Source> {
        var testSources = listOf(
            Source(1, "Top research", LocalDate.of(1981, 12, 4), "", "ArXiv"),
            Source(2, "Important paper", LocalDate.of(2001, 1, 23), "", "ArXiv"),
            Source(3, "Pointless publication", LocalDate.of(1989, 5, 23), "", "ArXiv"),
            Source(4, "Awful article", LocalDate.of(1998, 8, 11), "", "ArXiv")
        )

        return testSources
    }

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
}

class FileJsonAdapter() : JsonAdapter<File>() {
    override fun fromJson(reader: JsonReader?): File? {
        return File(reader?.nextString())
    }

    override fun toJson(writer: JsonWriter?, file: File?) {
        writer?.value(file?.absolutePath)
    }
}
