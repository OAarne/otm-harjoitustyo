package zhuLi.ui

import javafx.scene.control.Alert
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import javafx.scene.input.KeyCode
import javafx.scene.layout.GridPane
import tornadofx.View
import tornadofx.action
import tornadofx.bindSelected
import tornadofx.borderpane
import tornadofx.bottom
import tornadofx.button
import tornadofx.center
import tornadofx.column
import tornadofx.hbox
import tornadofx.multiSelect
import tornadofx.right
import tornadofx.singleAssign
import tornadofx.smartResize
import tornadofx.tableview
import zhuLi.domain.Source
import zhuLi.domain.SourceService

class MainView : View("Zhu Li - Digital Research Assistant") {

    private val service: SourceService by inject()
    private val model: SourceViewModel by inject()
    private var sourceTable: TableView<Source> by singleAssign()

    override val root = borderpane {
        center {
            // TODO: do something with tablecellfragment to display files etc nicely
            tableview(service.sources) {
                sourceTable = this
//              TODO: date is shown in american format, this is unacceptable
                // TODO: Authors list doesn't update with changes properly
                column("Title", Source::titleProperty)
                column("Authors", Source::authorsProperty)
                column("Type", Source::typeProperty)
                column("Pub. Date", Source::pubDateProperty)
                column("Date Added", Source::addDateProperty)
                column("Publication", Source::publicationProperty)
                column("Publisher", Source::publisherProperty)
                bindSelected(model)
                multiSelect(true)
                isEditable = true
                prefWidth = 800.0
                smartResize()
            }
        }
        bottom {
            hbox {
                button("Add Source").action(::addSource)
                button("Delete selected source").action(::deleteSource)
                button("Autofill BibTex entries for Selected").action(::autofillBibTex)
                button("Generate .bib File for Selected").action(::bibFile)
                button("Save").action(::save)
                button("Reload").action(::reload)
            }
        }
        right {
            add<SourceEditor>()
        }
        setOnKeyPressed { event ->
            if (event.code == KeyCode.S) {
                save()
            }
        }
    }

    init {
        root.setPrefSize(1200.0, 800.0)
        sourceTable.selectionModel.select(0)
    }

    private fun save() {
        model.commit()
        service.save()
        sourceTable.refresh()
    }

    private fun reload() {
        model.rollback()
        service.reload()
        sourceTable.refresh()
    }

    private fun addSource() {
        val newSource = Source()
        service.addSource(newSource)
        sourceTable.selectionModel.select(newSource)
    }

    private fun deleteSource() {
        service.deleteSource(sourceTable.selectionModel.selectedItem)
    }

    /**
     * Automatically fills the bibTex fields of the currently selected Sources.
     */

    fun autofillBibTex() {
        sourceTable.selectionModel.selectedCells.forEach { cell ->
            val source = service.sources.get(cell.row)
            source.bibTex = service.generateSourceBibTex(source)
        }
    }

    /**
     * Shows an alert containing an editable textarea
     * with the bibtex entries of all currently selected Sources collected to be copied into a .bib file.
     * __Does not actually generate any file!__
     */

    private fun bibFile() {
        val pane = GridPane()
        pane.maxWidth = Double.MAX_VALUE
        pane.add(TextArea(service.generateBibliography(sourceTable.selectionModel.selectedItems)))

        val alert = Alert(Alert.AlertType.CONFIRMATION)
        alert.headerText = "Copy this to your .bib file"
        alert.dialogPane.content = pane

        alert.show()
    }
}