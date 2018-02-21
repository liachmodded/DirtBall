package info.tritusk.dirtball.impl.bukkit.scheduler;

import info.tritusk.dirtball.Dirtball;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitWorker;
import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.SpongeExecutorService;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DirtballScheduler implements BukkitScheduler {

    private final Scheduler spongeScheduler;

    public DirtballScheduler(Scheduler spongeScheduler) {
        this.spongeScheduler = spongeScheduler;
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task, long delay) {
        SpongeExecutorService.SpongeFuture<?> spongeFuture = spongeScheduler.createSyncExecutor(plugin).schedule(task, delay * 50, TimeUnit.MILLISECONDS);
        return 0;
    }

    @Deprecated
    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task, long delay) {
        Dirtball.platform.getLogger().error(
                "Plugin {} is calling scheduleSyncDelayedTask(Plugin, BukkitRunnable, long), which is deprecated! Delegates to recommended implementation.",
                plugin);
        task.runTaskLater(plugin, delay);
        return 0;
    }

    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, Runnable task) {
        return 0;
    }

    @Deprecated
    @Override
    public int scheduleSyncDelayedTask(Plugin plugin, BukkitRunnable task) {
        return 0;
    }

    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period) {
        return 0;
    }

    @Deprecated
    @Override
    public int scheduleSyncRepeatingTask(Plugin plugin, BukkitRunnable task, long delay, long period) {
        return 0;
    }

    @Deprecated
    @Override
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task, long delay) {
        return 0;
    }

    @Deprecated
    @Override
    public int scheduleAsyncDelayedTask(Plugin plugin, Runnable task) {
        return 0;
    }

    @Deprecated
    @Override
    public int scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period) {
        return 0;
    }

    @Override
    public <T> Future<T> callSyncMethod(Plugin plugin, Callable<T> task) {
        return null;
    }

    @Override
    public void cancelTask(int taskId) {

    }

    @Override
    public void cancelTasks(Plugin plugin) {

    }

    @Override
    public void cancelAllTasks() {

    }

    @Override
    public boolean isCurrentlyRunning(int taskId) {
        return false;
    }

    @Override
    public boolean isQueued(int taskId) {
        return false;
    }

    @Override
    public List<BukkitWorker> getActiveWorkers() {
        return null;
    }

    @Override
    public List<BukkitTask> getPendingTasks() {
        return null;
    }

    @Override
    public BukkitTask runTask(Plugin plugin, Runnable task) throws IllegalArgumentException {
        return null;
    }

    @Deprecated
    @Override
    public BukkitTask runTask(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        return null;
    }

    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, Runnable task) throws IllegalArgumentException {
        return null;
    }

    @Deprecated
    @Override
    public BukkitTask runTaskAsynchronously(Plugin plugin, BukkitRunnable task) throws IllegalArgumentException {
        return null;
    }

    @Override
    public BukkitTask runTaskLater(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException {
        return null;
    }

    @Deprecated
    @Override
    public BukkitTask runTaskLater(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        return null;
    }

    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) throws IllegalArgumentException {
        return null;
    }

    @Deprecated
    @Override
    public BukkitTask runTaskLaterAsynchronously(Plugin plugin, BukkitRunnable task, long delay) throws IllegalArgumentException {
        return null;
    }

    @Override
    public BukkitTask runTaskTimer(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException {
        return null;
    }

    @Deprecated
    @Override
    public BukkitTask runTaskTimer(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        return null;
    }

    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, Runnable task, long delay, long period) throws IllegalArgumentException {
        return null;
    }

    @Deprecated
    @Override
    public BukkitTask runTaskTimerAsynchronously(Plugin plugin, BukkitRunnable task, long delay, long period) throws IllegalArgumentException {
        return null;
    }
}
