package zhuLi.domain

import zhuLi.dao.JsonSourceListDao

class SourceListModel(val sourceListDao: JsonSourceListDao) {
    val sources = sourceListDao.sources

    fun save(sources: List<Source>) {
        sourceListDao.save(sources)
    }

    fun reload() {
        sourceListDao.load()
    }

    // TODO: something like getByProject should go here?
}