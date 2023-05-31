package one.nxeu.survivalcore;

import one.nxeu.survivalcore.core.command.MessageCommandExecutor;
import one.nxeu.survivalcore.core.command.SCCommandCompleter;
import one.nxeu.survivalcore.core.command.SCCommandExecutor;
import one.nxeu.survivalcore.core.handler.BlockHandler;
import one.nxeu.survivalcore.core.handler.CommonHandler;
import one.nxeu.survivalcore.core.handler.PlayerHandler;
import one.nxeu.survivalcore.core.services.ActionBarService;
import one.nxeu.survivalcore.core.services.InventoryService;
import one.nxeu.survivalcore.core.services.TabListService;
import one.nxeu.survivalcore.core.services.TickService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class SurvivalCore extends JavaPlugin {

    private static SurvivalCore INSTANCE;
    private static TabListService tabListService;
    private static BukkitTask tabListTask;
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

        getCommand("vl").setExecutor(new SCCommandExecutor());
        getCommand("vl").setTabCompleter(new SCCommandCompleter());

        getLogger().info("VLCore Services are starting...");
        startServices();
        getLogger().info("VLCore Services has started!");

        getLogger().info("VLCore was enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("VLCore Services are stopping...");
        stopServices();
        getLogger().info("VLCore Services has stopped!");

        getLogger().info("VLCore was disabled.");
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
