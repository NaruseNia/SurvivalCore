package one.nxeu.vlcore;

import one.nxeu.vlcore.command.MessageCommandExecutor;
import one.nxeu.vlcore.command.VLCommandCompleter;
import one.nxeu.vlcore.command.VLCommandExecutor;
import one.nxeu.vlcore.handler.BlockHandler;
import one.nxeu.vlcore.handler.CommonHandler;
import one.nxeu.vlcore.handler.PlayerHandler;
import one.nxeu.vlcore.services.ActionBarService;
import one.nxeu.vlcore.services.InventoryService;
import one.nxeu.vlcore.services.TabListService;
import one.nxeu.vlcore.services.TickService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class VLCore extends JavaPlugin {

    private static VLCore INSTANCE;
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

        getCommand("vl").setExecutor(new VLCommandExecutor());
        getCommand("vl").setTabCompleter(new VLCommandCompleter());

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

    public static VLCore getInstance() {
        return INSTANCE;
    }
}
