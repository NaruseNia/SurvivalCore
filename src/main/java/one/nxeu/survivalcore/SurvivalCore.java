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
    private static ServiceHolder<>
    private static TickService tickService;
    private static BukkitTask tickTask;
    private static ActionBarService actionBarService;
    private static BukkitTask actionBarTask;
    private static InventoryService inventoryService;
    private static BukkitTask inventoryTask;

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
        tabListService = new TabListService();
        tabListTask = Bukkit.getScheduler().runTaskTimer(this, tabListService, 0, 10);

        tickService = new TickService();
        tickTask = Bukkit.getScheduler().runTaskTimer(this, tickService, 0, 1);

        actionBarService = new ActionBarService();
        actionBarTask = Bukkit.getScheduler().runTaskTimer(this, actionBarService, 0, 1);

        inventoryService = new InventoryService();
        inventoryTask = Bukkit.getScheduler().runTaskTimer(this, inventoryService, 0, 20);
    }

    public void stopServices() {
        tabListService = null;
        tickService = null;
        actionBarService = null;
        inventoryService = null;
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static SurvivalCore getInstance() {
        return INSTANCE;
    }
}
