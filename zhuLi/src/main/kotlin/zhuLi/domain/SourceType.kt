package zhuLi.domain

enum class SourceType {
    MISC, PERIODICAL, ARTICLE, BOOK, INBOOK, REFERENCE, PROCEEDINGS, INPROCEEDINGS;

    override fun toString(): String {
        if (this == PERIODICAL) return "Journal Issue"
        if (this == ARTICLE) return "Journal Article"
        if (this == INBOOK) return "Section of a Book"
        if (this == PROCEEDINGS) return "Conference Proceedings"
        if (this == INPROCEEDINGS) return "Conference Article"
        return super.toString().toLowerCase().capitalize()
    }

    fun toBibtex(): String {
        return super.toString().toLowerCase()
    }
}