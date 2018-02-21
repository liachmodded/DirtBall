package info.tritusk.dirtball;

import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameConstructionEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameLoadCompleteEvent;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * Dirtball is an experimental Sponge plugin that implements full Bukkit API and SpigotAPI
 * on top of SpongeAPI. Concept only, not for actual use.
 * <p>
 * Long overdue. Everyone knows that Bukkit has been here for long. And (almost)
 * everyone relies on that. While sure it is stable, it blocks the way to go further.
 * This plugin, though serving as both an experiment on wiring Bukkit plugin to Sponge
 * and an examination on to what extents can SpongeAPI replace Bukkit's functionality,
 * is still the very embodiment of our belief:
 * </p>
 * <p>
 *     <i>History is fading; while we have yet stopped expanding.</i>
 * </p>
 * <p>
 * And here, is the entry class of Sponge plugin "DirtBall", a standalone implementation
 * of BukkitAPI, experimental usage only.
 * <br>
 * The {@linkplain org.bukkit.Server Bukkit Server} instance, which is a
 * wrapper of the {@linkplain org.spongepowered.api.Server Sponge Server} instance,
 * will be injected at the earliest possible time.
 * </p>
 */
@Plugin(id = "dirtball",
        name = "DirtBall",
        version = "0.0.0",
        description = "Final resolution: let Spigot plugins run on SpongeAPI",
        url = "http://dirtball.tritusk.info/",
        authors = {"3TUSK", "ustc-zzzz"}) // Note: zzzz only serves as consultant
public final class Dirtball {

    public static DirtballPlatform platform;

    @Inject
    public void injectLogger(Logger logger) {
        platform.setLogger(logger);
    }

    @Listener
    public final void loadingStage1(GameConstructionEvent event) {

    }

    @Listener
    public final void loadingStage2(GamePreInitializationEvent event) {

    }

    @Listener
    public final void loadingStage3(GameInitializationEvent event) {

    }

    @Listener
    public final void loadingStage4(GamePostInitializationEvent event) {

    }

    @Listener
    public final void loadingStage5(GameLoadCompleteEvent event) {

    }
}
