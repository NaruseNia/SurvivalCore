package one.nxeu.survivalcore

import org.bukkit.plugin.java.JavaPlugin

class SurvivalCore : JavaPlugin() {
    private lateinit var instance: SurvivalCore

    override fun onEnable() {
        instance = this
    }

    override fun onDisable() {
    }
}