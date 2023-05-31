package one.nxeu.survivalcore;

import one.nxeu.survivalcore.core.command.MessageCommandExecutor;
import one.nxeu.survivalcore.core.command.SVCCommandCompleter;
import one.nxeu.survivalcore.core.command.SVCCommandExecutor;
import one.nxeu.survivalcore.core.handler.BlockHandler;
import one.nxeu.survivalcore.core.handler.CommonHandler;
import one.nxeu.survivalcore.core.handler.PlayerHandler;
import one.nxeu.survivalcore.core.services.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class SurvivalCore extends JavaPlugin {

    private static SurvivalCore INSTANCE;
    private static ServiceHolder<TabListService> tabListService;
    private static ServiceHolder<TickService> tickService;
    private static ServiceHolder<ActionBarService> actionBarService;
    private static ServiceHolder<ActionBarService> inventoryService;

    @Override
    public void onEnable() {
        INSTANCE = this;
        getServer().getPluginManager().registerEvents(new CommonHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
        getServer().getPluginManager().registerEvents(new BlockHandler(), this);

        // Register commands
        getCommand("message").setExecutor(new MessageCommandExecutor());

        getCommand("svc").setExecutor(new SVCCommandExecutor());
        getCommand("svc").setTabCompleter(new SVCCommandCompleter());

        getLogger().info("SurvivalCore Services are starting...");
        startServices();
        getLogger().info("SurvivalCore Services has started!");

        getLogger().info("SurvivalCore was enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("SurvivalCore Services are stopping...");
        stopServices();
        getLogger().info("SurvivalCore Services has stopped!");

        getLogger().info("SurvivalCore was disabled.");
    }

    public void startServices() {
        tabListService.setService(new TabListService());
        tabListService.setTask(Bukkit.getScheduler().runTaskTimer(this, tabListService.getService(), 0, 10));

        tickService.setService(new TickService());
        tickService.setTask(Bukkit.getScheduler().runTaskTimer(this, tickService.getService(), 0, 1));

        actionBarService.setService(new ActionBarService());
        actionBarService.setTask(Bukkit.getScheduler().runTaskTimer(this, actionBarService.getService(), 0, 1));

        inventoryService.setService(new InventoryService());
        inventoryService.setTask(Bukkit.getScheduler().runTaskTimer(this, inventoryService.getService(), 0, 1));
    }

    public void stopServices() {
        tabListService.dispose();
        tickService.dispose();
        actionBarService.dispose();
        inventoryService.dispose();
    }

    public static SurvivalCore getInstance() {
        return INSTANCE;
    }
}
