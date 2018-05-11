package zhuLi.dao

import zhuLi.domain.Source
import java.io.File
import java.io.IOException

class JsonSourceListFileDao(var file: File) : JsonSourceListDao() {
    override val sources: List<Source>

    init {
        sources = load()
    }

    override fun save(sources: List<Source>) {
        var jsonList = super.listAdapter.toJson(sources)
        file.printWriter().use { out ->
            out.println(jsonList)
        }
    }

    override fun load(): List<Source> {
        try {
            val jsonList = file.readText()

            val sourceList = super.listAdapter.fromJson(jsonList)

            return sourceList!!
        } catch (e: IOException) {
            return listOf()
        }
    }
}