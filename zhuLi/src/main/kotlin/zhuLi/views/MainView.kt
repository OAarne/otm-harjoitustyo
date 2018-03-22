package zhuLi.views

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import tornadofx.View

//class MainView : View() {
//
////    override val root = WebView()
//    override val root = VBox()
//
//    init {
//        root += Button("Press Me")
//        root += Label("Waiting")
////        with(root) {
////            setPrefSize(800.0, 600.0)
////        }
//    }
//}

class MainView: View() {
    override val root = VBox()

    init {
        root.children.add(Label("Waiting"))
        root.children.add(Button("Press Me"))
    }
}