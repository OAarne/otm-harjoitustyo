package zhuLi.ui.views

import javafx.scene.control.TableView
import tornadofx.View
import tornadofx.action
import tornadofx.borderpane
import tornadofx.bottom
import tornadofx.button
import tornadofx.center
import tornadofx.singleAssign
import zhuLi.ui.controllers.SourceController
import zhuLi.ui.models.Source
import zhuLi.ui.models.SourceModel
import java.util.Date

class MainView : View("Zhu Li - Digital Research Assistant") {

    private val controller: SourceController by inject()
    private val model: SourceModel by inject()
    private var sourceTable: TableView<Source> by singleAssign()

    override val root = borderpane {
        center {
            add<SourceTable>()
        }
        bottom { button("Add Source").action(::addSource) }
    }

    private fun addSource() {
        val newSource = Source(controller.sources.size + 1, "", Date(), "", "")
        controller.addSource(newSource)
        sourceTable.selectionModel.select(newSource)
        sourceTable.edit(sourceTable.items.size - 1, sourceTable.columns.first())
    }

    init {
//        controller = SourceController("sources.json")
        root.setPrefSize(800.0, 600.0)
    }
}