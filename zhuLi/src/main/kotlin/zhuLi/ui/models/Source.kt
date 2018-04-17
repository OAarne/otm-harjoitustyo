package zhuLi.ui.models

import com.squareup.moshi.Json
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue
import java.util.Date

class Source(
    id: Int,
    title: String,
//    authors: List<String>,
    @Json(name = "pub_date") pubDate: Date,
    @Json(name = "bibtex") bibTex: String,
    publisher: String
) {

    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val titleProperty = SimpleStringProperty(title)
    var title by titleProperty

//    val authorsProperty = FXCollections.observableArrayList<String>(authors)

    //  TODO: Preferably upgrade to LocalDate, currently downgraded for JSON's sake
    val pubDateProperty = SimpleObjectProperty<Date>(pubDate)
    @Json(name = "pub_date")
    var pubDate by pubDateProperty

    val addDateProperty = SimpleObjectProperty<Date>(Date())
    @Json(name = "add_date")
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
    val addDate = bind(Source::addDateProperty)
    val bibTex = bind(Source::bibTexProperty)
    val publisher = bind(Source::publisherProperty)
}