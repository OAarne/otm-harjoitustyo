package zhuLi.domain

import zhuLi.dao.SourceDao
import zhuLi.ui.models.Source

class SourceService(val sourceDao: SourceDao) {
    val sources = sourceDao.sources

    fun save(sources: List<Source>) {
        sourceDao.save(sources)
    }

    fun reload() {
    }

    // TODO: something like getByProject should go here?
}