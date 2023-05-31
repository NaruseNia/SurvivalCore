package one.nxeu.survivalcore.core.services;

import org.bukkit.scheduler.BukkitTask;

public class ServiceHolder <S extends Runnable, T extends BukkitTask> {
    private S service;
    private T task;

    public ServiceHolder(S service, T task) {
        this.service = service;
        this.task = task;
    }

    public S getService() {
        return service;
    }
    public T getTask() {
        return task;
    }

    public void setService(S service) {
        this.service = service;
    }
    public void setTask(T task) {
        this.task = task;
    }
}
