package one.nxeu.vlcore.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class TickService implements Runnable {
    @Override
    public void run() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                Location location = entity.getLocation();
                Location down = location.clone();
                down.setY(down.getY() - 1);
                if (down.getBlock().getType().equals(Material.GOLD_BLOCK) && location.getBlock().getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
                    entity.setVelocity(entity.getLocation().getDirection().normalize().multiply(4).setY(2));
                } else if (down.getBlock().getType().equals(Material.IRON_BLOCK) && location.getBlock().getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE)) {
                    entity.setVelocity(entity.getLocation().getDirection().normalize().multiply(4));
                }
            }
        }
    }
}
