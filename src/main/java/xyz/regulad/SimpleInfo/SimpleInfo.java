package xyz.regulad.SimpleInfo;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.regulad.SimpleInfo.tasks.UpdateScoreboardTask;
import xyz.regulad.SimpleInfo.tasks.UpdateTablistTask;

/**
 * A template plugin to be used in Minecraft plugins.
 */
public class SimpleInfo extends JavaPlugin {
    private final @NotNull UpdateScoreboardTask updateScoreboardTask = new UpdateScoreboardTask(this);
    private final @NotNull UpdateTablistTask updateTablistTask = new UpdateTablistTask(this);
    private final @NotNull Metrics metrics = new Metrics(this, 13488);

    @Override
    public final void onEnable() {
        this.saveDefaultConfig();

        this.updateScoreboardTask.runTaskTimer(this, 10L, 10L);
        this.updateTablistTask.runTaskTimer(this, 10L, 10L);
    }

    @Override
    public final void onDisable() {
        if (!this.updateScoreboardTask.isCancelled())
            this.updateScoreboardTask.cancel();
        if (!this.updateTablistTask.isCancelled())
            this.updateTablistTask.cancel();
    }
}
