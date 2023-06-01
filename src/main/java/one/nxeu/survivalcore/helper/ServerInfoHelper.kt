package one.nxeu.survivalcore.helper

import net.minecraft.server.MinecraftServer
import org.bukkit.Server
import org.bukkit.craftbukkit.v1_19_R3.CraftServer

class ServerInfoHelper {
    companion object {
        fun getRecentMspt(server: Server): LongArray {
            val minecraftServer: MinecraftServer = (server as CraftServer).server
            return minecraftServer.tickTimes
        }
        fun getRecentTps(server: Server): DoubleArray {
            val minecraftServer: MinecraftServer = (server as CraftServer).server
            return minecraftServer.recentTps
        }

        fun getMspt(server: Server): Double {
            return TickHelper.toMilliseconds(getRecentMspt(server).average())
        }
        fun getTps(server: Server): Double {
            return TickHelper.toMilliseconds(getRecentTps(server).average())
        }
    }
}