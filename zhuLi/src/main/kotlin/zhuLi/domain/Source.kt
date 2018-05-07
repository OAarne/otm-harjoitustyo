package zhuLi.domain

import com.squareup.moshi.Json
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.getValue
import tornadofx.observable
import tornadofx.setValue
import java.io.File
import java.time.LocalDate

class Source(
    title: String,
    authors: List<String>,
    @Json(name = "pub_date") pubDate: LocalDate,
    @Json(name = "bibtex") bibTex: String,
    publisher: String,
    publication: String
) {

    constructor() : this("", listOf(""), LocalDate.now(), "", "", "")

    val titleProperty = SimpleStringProperty(title)
    var title by titleProperty

    val authorsProperty = SimpleListProperty<String>(authors.toMutableList().observable())
    val authors by authorsProperty

    val fileProperty = SimpleObjectProperty<File>()
    var file by fileProperty

    val pubDateProperty = SimpleObjectProperty<LocalDate>(pubDate)
    @Json(name = "pub_date")
    var pubDate by pubDateProperty

    val addDateProperty = SimpleObjectProperty<LocalDate>(LocalDate.now())
    @Json(name = "add_date")
    var addDate by addDateProperty

    val bibTexProperty = SimpleStringProperty(bibTex)
    var bibTex by bibTexProperty

    val publicationProperty = SimpleStringProperty(publication)
    var publication by publicationProperty

    val publisherProperty = SimpleStringProperty(publisher)
    var publisher by publisherProperty

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Source

        if (title != other.title) return false
        if (authors.size != other.authors.size) return false
        if (pubDate != other.pubDate) return false
        if (addDate != other.addDate) return false
        if (bibTex != other.bibTex) return false
        if (publisher != other.publisher) return false
        if (publication != other.publication) return false

        return true
    }
}
