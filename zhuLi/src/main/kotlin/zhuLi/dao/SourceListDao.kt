package zhuLi.dao

import zhuLi.domain.Source

interface SourceListDao {
    val sources: List<Source>

    fun load(): List<Source>
    fun save(sources: List<Source>)
    fun generateSampleSourcesFile()
}