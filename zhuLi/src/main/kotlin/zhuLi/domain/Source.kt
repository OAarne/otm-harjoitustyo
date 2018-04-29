package zhuLi.domain

import com.squareup.moshi.Json
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.getValue
import tornadofx.setValue
import java.io.File
import java.time.LocalDate

class Source(
    id: Int,
    title: String,
//    authors: List<String>,
    @Json(name = "pub_date") pubDate: LocalDate,
    @Json(name = "bibtex") bibTex: String,
    publisher: String
) {

    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val titleProperty = SimpleStringProperty(title)
    var title by titleProperty

    val fileProperty = SimpleObjectProperty<File>()
    var file by fileProperty
//    val authorsProperty = FXCollections.observableArrayList<String>(authors)

    //  TODO: Preferably upgrade to LocalDate, currently downgraded for JSON's sake
    val pubDateProperty = SimpleObjectProperty<LocalDate>(pubDate)
    @Json(name = "pub_date")
    var pubDate by pubDateProperty

    val addDateProperty = SimpleObjectProperty<LocalDate>(LocalDate.now())
    @Json(name = "add_date")
    var addDate by addDateProperty

    val bibTexProperty = SimpleStringProperty(bibTex)
    var bibTex by bibTexProperty

    val publisherProperty = SimpleStringProperty(publisher)
    var publisher by publisherProperty

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Source

        if (id != other.id) return false
        if (title != other.title) return false
        if (pubDate != other.pubDate) return false
        if (addDate != other.addDate) return false
        if (bibTex != other.bibTex) return false
        if (publisher != other.publisher) return false

        return true
    }
}
