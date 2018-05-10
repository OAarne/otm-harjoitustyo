package zhuLi.ui.views

import javafx.scene.control.TableView
import javafx.scene.input.KeyCode
import tornadofx.View
import tornadofx.action
import tornadofx.bindSelected
import tornadofx.borderpane
import tornadofx.bottom
import tornadofx.button
import tornadofx.center
import tornadofx.column
import tornadofx.hbox
import tornadofx.right
import tornadofx.singleAssign
import tornadofx.smartResize
import tornadofx.tableview
import zhuLi.domain.Source
import zhuLi.domain.SourceListService
import zhuLi.ui.SourceViewModel

class MainView : View("Zhu Li - Digital Research Assistant") {

    private val service: SourceListService by inject()
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
                column("Pub. Date", Source::pubDateProperty)
                column("Date Added", Source::addDateProperty)
                column("Publication", Source::publicationProperty)
                column("Publisher", Source::publisherProperty)
                bindSelected(model)
                isEditable = true
                prefWidth = 800.0
                smartResize()
            }
        }
        bottom {
            hbox {
                button("Add Source").action(::addSource)
                button("Delete selected source").action(::deleteSource)
                button("Save").action(::save)
                button("Reload").action(::reload)
            }
        }
        right {
            add<SourceEditor>()
        }
        setOnKeyPressed { event ->
            if (event.code == KeyCode.S) { save() }
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
    // TODO: Make it save on close?
}