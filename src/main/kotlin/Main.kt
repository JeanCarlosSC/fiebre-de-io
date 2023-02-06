import lib.sRAD.gui.sComponent.SFrame
import javax.swing.JButton
import javax.swing.JFrame

val sleepTime = Event(Hour(21, 35), Hour(9, 15), "sleep")

fun main() {
    AppController(GUI())
}

class AppController(gui: GUI) {

}

class GUI: SFrame() {
    init {
        loadComponents()
        loadProperties()
    }
    private fun loadComponents() {
        setMainBar("Personal manager")

        val btAddConstraint = JButton("Add constraint")
        btAddConstraint.setBounds(32, 32, 128, 32)
        add(btAddConstraint)

        val btShowCalendar = JButton("Show calendar")
        btShowCalendar.setBounds(192, 32, 128, 32)
        btShowCalendar.addActionListener { showCalendar() }
        add(btShowCalendar)
    }
    private fun showCalendar() {
        println(sleepTime)
    }
    private fun loadProperties() {
        setSize(1280, 720)
        setLocationRelativeTo(null)
        layout = null
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }
}

class Event(val startTime: Hour, val duration: Hour, val name: String) {
    override fun toString(): String {
        return "$name ($startTime - ${startTime.plus(duration)})"
    }
}

class Hour(var hour: Int, var minutes: Int) {
    init {
        while(minutes > 59) {
            minutes -= 60
            hour++
        }
        while(hour > 23) {
            hour -= 24
        }
    }
    override fun toString(): String {
        return "$hour:$minutes"
    }
}
fun Hour.plus(duration: Hour): Hour {
    return Hour(this.hour+duration.hour, this.minutes+duration.minutes)
}
