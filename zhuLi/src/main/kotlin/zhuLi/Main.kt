package zhuLi

import tornadofx.App
import tornadofx.launch
import zhuLi.ui.views.MainView

class Main : App(MainView::class)

fun main(args: Array<String>) {
//    Application.launch(App::class.java)
    launch<Main>(args)
}
