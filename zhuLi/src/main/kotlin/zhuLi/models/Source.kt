package models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*
import java.time.LocalDate

class Source(
        id: Int,
        title: String,
        authors: List<String>,
        pubDate: LocalDate,
        bibTex: String,
        publisher: String) {

    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val titleProperty = SimpleStringProperty(title)
    var title by titleProperty

    val authorsProperty = FXCollections.observableArrayList<String>(authors)

    val pubDateProperty = SimpleObjectProperty<LocalDate>(pubDate)
    var pubDate by pubDateProperty

    val addDateProperty = SimpleObjectProperty<LocalDate>(LocalDate.now())
    var addDate by addDateProperty

    val bibTexProperty = SimpleStringProperty(bibTex)
    var bibTex by bibTexProperty

    val publisherProperty = SimpleStringProperty(publisher)
    var publisher by publisherProperty
}

class SourceModel : ItemViewModel<Source>() {
    val id = bind(Source::idProperty)
    val title = bind(Source::titleProperty)
    val pubDate = bind(Source::pubDateProperty)
}