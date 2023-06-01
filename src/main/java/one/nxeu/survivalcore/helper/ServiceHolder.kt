package one.nxeu.survivalcore.helper

import org.bukkit.scheduler.BukkitTask

class ServiceHolder<S : Runnable>(var service: S, var task: BukkitTask) {
    fun dispose() {
        task.cancel()
    }
}