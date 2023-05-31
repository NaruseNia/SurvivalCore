package one.nxeu.vlcore.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;

import java.lang.reflect.Field;

public class ServerInfoHelper {

    public static long[] getRecentMspt(MinecraftServer server) {
        return server.k;
    }

    public static double[] getRecentTps(MinecraftServer server) {
        return server.recentTps;
    }

    public static double getTps(MinecraftServer server) {
        return TpsHelper.average(getRecentTps(server));
    }

    public static double getMspt(MinecraftServer server) {
        return TpsHelper.toMilliseconds(TpsHelper.average(ServerInfoHelper.getRecentMspt(server)));
    }
}
