package zhuLi.ui.views

import tornadofx.View
import tornadofx.bindSelected
import tornadofx.column
import tornadofx.makeEditable
import tornadofx.smartResize
import tornadofx.tableview
import tornadofx.vbox
import zhuLi.domain.Source
import zhuLi.domain.SourceModel
import zhuLi.ui.controllers.SourceController

class SourceTable : View() {
    private val controller: SourceController by inject()
//    private var sourceTable: TableView<Source> by singleAssign()

    override val root = vbox {

    }
}