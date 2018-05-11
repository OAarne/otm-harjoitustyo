package zhuLi

import tornadofx.App
import tornadofx.launch
import zhuLi.ui.MainView

class Main : App(MainView::class)

fun main(args: Array<String>) {
    launch<Main>(args)
}
