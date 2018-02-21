package info.tritusk.dirtball.plugin;

import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.plugin.PluginManager;

import java.util.Collection;
import java.util.Optional;

public class DirtballBukkitPluginManager implements PluginManager {
    @Override
    public Optional<PluginContainer> fromInstance(Object instance) {
        return null;
    }

    @Override
    public Optional<PluginContainer> getPlugin(String id) {
        return null;
    }

    @Override
    public Collection<PluginContainer> getPlugins() {
        return null;
    }

    @Override
    public boolean isLoaded(String id) {
        return false;
    }
}
