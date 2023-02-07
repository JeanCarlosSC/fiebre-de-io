fun main() {
    val appController = AppController()

    appController.addEvent(
        Event("Investigación de operaciones", Hour(8, 0),Hour(2, 0),
            Days(monday = true, thursday = true, friday = true))
    )

    appController.printCalendar()
}

class AppController {
    private val events = mutableListOf<Event>()

    fun addEvent(event: Event) {
        events.add(event)
    }

    fun printCalendar() {
        val days = listOf("sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday")
        for (day in days) {
            println("- - $day - -")
            for (event in events) {
                if(event.days.isActive(day)) {
                    println(event)
                }
            }
            println()
        }
    }
}

class Event(val name: String, val startTime: Hour, private val duration: Hour, val days: Days) {
    override fun toString(): String {
        return "$startTime - ${getEndTime()} $name"
    }
    fun getEndTime(): Hour {
        return startTime.plus(duration)
    }
}

class Days(val sunday: Boolean = false, val monday: Boolean = false, val tuesday: Boolean = false,
           val wednesday: Boolean = false, val thursday: Boolean = false, val friday: Boolean = false,
           val saturday: Boolean = false) {
    fun isActive(day: String): Boolean {
        if(day == "sunday") {
            return sunday
        }
        if(day == "monday") {
            return monday
        }
        if(day == "tuesday") {
            return tuesday
        }
        if(day == "wednesday") {
            return wednesday
        }
        if(day == "thursday") {
            return thursday
        }
        if(day == "friday") {
            return friday
        }
        if (day == "saturday") {
            return saturday
        }
        return false
    }
}

class Hour(var hour: Int, var minutes: Int) {
    override fun toString(): String {
        return String.format("%02d:%02d", hour, minutes)
    }
}
fun Hour.plus(duration: Hour): Hour {
    return Hour(this.hour+duration.hour, this.minutes+duration.minutes)
}
