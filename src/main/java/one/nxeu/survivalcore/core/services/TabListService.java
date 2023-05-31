package one.nxeu.survivalcore.core.services;

import net.minecraft.server.MinecraftServer;
import one.nxeu.survivalcore.core.text.TranslatableTexts;
import one.nxeu.survivalcore.core.util.TabUtils;
import one.nxeu.survivalcore.core.helper.ServerInfoHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TabListService implements Runnable {
    @Override
    public void run() {
        Date date = new Date();
        TimeZone zone = TimeZone.getTimeZone("Asia/Tokyo");
        SimpleDateFormat format = new SimpleDateFormat(TranslatableTexts.colorize("tab.header.format"));
        format.setTimeZone(zone);

        TabUtils.setTabText(
                TranslatableTexts.iridium("tab.header",
                    format.format(date)
                ),
                TranslatableTexts.iridium("tab.footer",
                        (double) Math.round(ServerInfoHelper.getTps(MinecraftServer.getServer()) * 100) / 100,
                        (double) Math.round(ServerInfoHelper.getMspt(MinecraftServer.getServer()) * 100) / 100
                ));
    }
}
