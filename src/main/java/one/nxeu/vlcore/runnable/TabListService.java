package one.nxeu.vlcore.runnable;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import net.minecraft.server.MinecraftServer;
import one.nxeu.vlcore.VLCore;
import one.nxeu.vlcore.util.Messages;
import one.nxeu.vlcore.util.ServerInfoHelper;
import one.nxeu.vlcore.util.TabUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class TabListService implements Runnable {
    @Override
    public void run() {
        Date date = new Date();
        TimeZone zone = TimeZone.getTimeZone("Asia/Tokyo");
        SimpleDateFormat format = new SimpleDateFormat(Messages.colorize("tab.header.format"));
        format.setTimeZone(zone);

        TabUtils.setTabText(
                Messages.iridium("tab.header",
                    format.format(date)
                ),
                Messages.iridium("tab.footer",
                        (double) Math.round(ServerInfoHelper.getTps(MinecraftServer.getServer()) * 100) / 100,
                        (double) Math.round(ServerInfoHelper.getMspt(MinecraftServer.getServer()) * 100) / 100
                ));
    }
}
