package zhuLi.ui.views

import tornadofx.View
import tornadofx.bindSelected
import tornadofx.column
import tornadofx.makeEditable
import tornadofx.smartResize
import tornadofx.tableview
import zhuLi.ui.controllers.SourceController
import zhuLi.ui.models.Source
import zhuLi.ui.models.SourceModel

class SourceTable : View() {
    private val controller: SourceController by inject()
    private val model: SourceModel by inject()
//    private var sourceTable: TableView<Source> by singleAssign()

    override val root = tableview(controller.sources) {
        //        sourceTable = this
//              TODO: date is shown in american format, this is unacceptable
//              TODO: implement a way to edit authors
        column("id", Source::idProperty)
        column("title", Source::titleProperty).makeEditable()
//        readonlyColumn("authors", Source::authorsProperty)
        column("pub. date", Source::pubDateProperty)//.makeEditable()
        column("Date Added", Source::addDateProperty)
        column("BibTex", Source::bibTexProperty).makeEditable()
        column("publisher", Source::publisherProperty).makeEditable()
        bindSelected(model)
        isEditable = true
        prefWidth = 800.0
        smartResize()
    }
}