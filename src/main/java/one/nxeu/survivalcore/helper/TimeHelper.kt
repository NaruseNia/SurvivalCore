package one.nxeu.survivalcore.helper

class TimeHelper {
    companion object {
        fun parseTime(time: Long):String {
            var gameTime = time
            var hours = gameTime / 1000 + 6
            var minutes = (gameTime % 1000) * 60 / 1000
            var ampm = "AM";
            if (hours >= 12) {
                hours -= 12
                ampm = "PM"
            }
            if (hours >= 12) {
                hours -= 12
                ampm = "AM"
            }
            if (hours == 0L) hours = 12
            var mm = "0$minutes"
            mm = mm.substring(mm.length - 2, mm.length)
            return "$hours:mm $ampm"
        }
    }
}