package zhuLi


import zhuLi.views.MainView
import javafx.application.Application
import tornadofx.App

class Main : App(MainView::class)

fun main(args: Array<String>) {
        Application.launch(App::class.java)
}
