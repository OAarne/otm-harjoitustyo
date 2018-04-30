package zhuLi.ui.views

import javafx.scene.control.Alert
import javafx.stage.FileChooser
import tornadofx.FileChooserMode
import tornadofx.ItemViewModel
import tornadofx.alert
import tornadofx.chooseFile
import zhuLi.domain.Source
import java.awt.Desktop
import kotlin.concurrent.thread

class SourceViewModel : ItemViewModel<Source>() {
    val id = bind(Source::idProperty)
    val title = bind(Source::titleProperty)
    var file = bind(Source::fileProperty)
    val pubDate = bind(Source::pubDateProperty)
    val addDate = bind(Source::addDateProperty)
    val bibTex = bind(Source::bibTexProperty)
    val publisher = bind(Source::publisherProperty)

    // TODO: Source editing logic goes here?

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

    fun openFile() {
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
                "Your platform does not support opening files like this."
            )
        }
    }
}