package one.nxeu.survivalcore.helper

class TickHelper {
    companion object {
        fun <T : Number> toMilliseconds(value: T): Double {
            return value.toDouble() * 1.0e6
        }
    }
}