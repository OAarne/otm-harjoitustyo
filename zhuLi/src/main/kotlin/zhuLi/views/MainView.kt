package zhuLi.views

import javafx.scene.web.WebView
import tornadofx.View

class MainView : View() {

    override val root = WebView()

    init {
        with(root) {
            setPrefSize(800.0, 600.0)
        }
    }
}