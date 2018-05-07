package zhuLi

import tornadofx.App
import tornadofx.launch
import zhuLi.domain.SourceListService
import zhuLi.ui.views.MainView

class Main : App(MainView::class)

fun main(args: Array<String>) {

    // Initialize the Service
    SourceListService()

    launch<Main>(args)
}
