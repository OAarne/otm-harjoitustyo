package zhuLi

import javafx.application.Application
import tornadofx.App
import zhuLi.ui.views.MainView

class Main : App(MainView::class)

fun main(args: Array<String>) {
    Application.launch(App::class.java)
}
