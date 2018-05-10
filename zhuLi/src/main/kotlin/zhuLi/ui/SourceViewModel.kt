package zhuLi.ui

import javafx.scene.control.Alert
import javafx.stage.FileChooser
import tornadofx.FileChooserMode
import tornadofx.ItemViewModel
import tornadofx.alert
import tornadofx.chooseFile
import zhuLi.domain.Source
import java.awt.Desktop
import kotlin.concurrent.thread

/**
 * An implementation of the ViewModel from the MVVM pattern, mediates between the Source model and the views.
 */

class SourceViewModel : ItemViewModel<Source>() {
    val title = bind(Source::titleProperty)
    var authors = bind(Source::authorsProperty)
    var file = bind(Source::fileProperty)
    var type = bind(Source::typeProperty)
    val pubDate = bind(Source::pubDateProperty)
    val addDate = bind(Source::addDateProperty)
    val bibTex = bind(Source::bibTexProperty)
    val publication = bind(Source::publicationProperty)
    val publisher = bind(Source::publisherProperty)

    /**
     * Opens a file explorer window where the user can select a file to be associated with this source.
     */

    fun setFile() {
        val fileList = chooseFile(
            "Choose File",
            arrayOf(FileChooser.ExtensionFilter("documents", listOf(
                "*.pdf",
                "*.epub")
            )),
            FileChooserMode.Single,
            null,
            {}
        )
        if (fileList.isNotEmpty()) {
            var observableFile = fileList[0]
            this.file.value = observableFile
            alert(
                Alert.AlertType.INFORMATION,
                "File Set",
                "The file " + observableFile.toString() + " has been selected."
            )
        }
    }

    /**
     * Opens the file associated with this source with the system default app, if possible.
     */

    fun openFile() {
        if (this.file.value == null) alert(Alert.AlertType.WARNING, "No file set", "No file set") else {
            if (Desktop.isDesktopSupported()) {
                thread(true,
                    false,
                    null,
                    "zhuLi-viewer",
                    -1,
                    { Desktop.getDesktop().open(this.file.value) }
                )
            } else {
                alert(
                    Alert.AlertType.WARNING,
                    "Unsupported Action",
                    "Your platform does not support opening files this way."
                )
            }
        }
    }

    fun addAuthor(name: String) {
        this.authors.value.add(name)
    }

    fun removeAuthor(name: String) {
        this.authors.value.remove(name)
    }
}