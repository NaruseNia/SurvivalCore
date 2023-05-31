package one.nxeu.survivalcore.core.services;

import org.bukkit.scheduler.BukkitTask;

public class ServiceHolder <S extends Runnable> {
    private S service;
    private BukkitTask task;

    public ServiceHolder(S service, BukkitTask task) {
        this.service = service;
        this.task = task;
    }

    public S getService() {
        return service;
    }
    public BukkitTask getTask() {
        return task;
    }

    public void setService(S service) {
        this.service = service;
    }
    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public void dispose() {
        service = null;

        task.cancel();
        task = null;
    }
}
