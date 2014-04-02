package section6

import javax.swing.{JPanel, JButton, JFrame}
import java.awt.event.{ActionEvent, ActionListener, WindowEvent, WindowAdapter}

object ClickMeApplication extends App {

  // TODO: implement an implicit conversion so that
  // the commented code can compile and work as expected

  new JFrame("click Me!") {
    val exitListener = new WindowAdapter {
      override def windowClosing(event: WindowEvent){
        System.exit(0)
      }
    }
    addWindowListener(exitListener)

    val button = new JButton("click me")
    button.addActionListener(new ActionListener {
      def actionPerformed(event: ActionEvent) {
        println("button clicked in a java way")
      }
    })

    object MyImplicits {
      import scala.language.implicitConversions

      implicit def toListener(f: ActionEvent => Unit): ActionListener = new ActionListener {
        def actionPerformed(event: ActionEvent) {
          f(event)
        }
      }

    }

    import MyImplicits.toListener

    button.addActionListener { event: ActionEvent =>
      println("button clicked in a scala way")
    }

    val panel = new JPanel()
    panel.add(button)
    setContentPane(panel)
    setSize(200, 122)
    setVisible(true)
  }
}
