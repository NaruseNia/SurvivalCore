package one.nxeu.vlcore.core.helper;

import net.minecraft.server.MinecraftServer;

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
