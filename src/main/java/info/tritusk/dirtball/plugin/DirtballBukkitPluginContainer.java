package info.tritusk.dirtball.plugin;

import info.tritusk.dirtball.Dirtball;
import org.bukkit.plugin.Plugin;
import org.slf4j.Logger;
import org.spongepowered.api.asset.Asset;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.plugin.meta.PluginDependency;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

public class DirtballBukkitPluginContainer implements PluginContainer {

    private final Plugin bukkitPluginObject;
    private final String pluginId;
    private final String pluginName;

    public DirtballBukkitPluginContainer(Plugin bukkitPlugin) {
        this.bukkitPluginObject = bukkitPlugin;
        // We use "bukkit-" prefix to avoid potential id conflict
        this.pluginId = "bukkit-" + bukkitPlugin.getName().toLowerCase(Locale.ENGLISH);
        this.pluginName = bukkitPlugin.getName();
    }

    @Override
    public String getId() {
        return pluginId;
    }

    @Override
    public String getName() {
        return pluginName;
    }

    @Override
    public Optional<String> getVersion() {
        return Optional.of(bukkitPluginObject.getDescription().getVersion());
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of(bukkitPluginObject.getDescription().getDescription());
    }

    @Override
    public Optional<String> getUrl() {
        return Optional.of(bukkitPluginObject.getDescription().getWebsite());
    }

    @Override
    public List<String> getAuthors() {
        return bukkitPluginObject.getDescription().getAuthors();
    }

    @Override
    public Set<PluginDependency> getDependencies() {
        Dirtball.platform.unimplementedWarning("PluginContainer::getDependencies");
        return Collections.emptySet();
    }

    @Override
    public Optional<PluginDependency> getDependency(String id) {
        Dirtball.platform.unimplementedWarning("PluginContainer::getDependency");
        return Optional.empty();
    }

    @Override
    public Optional<Asset> getAsset(String name) {
        Dirtball.platform.unimplementedWarning("PluginContainer::getAsset");
        return Optional.empty();
    }

    @Override
    public Optional<Path> getSource() {
        Dirtball.platform.getLogger().warn("Cannot determine source of plugin '{}'", pluginId);
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     * @implNote
     * An instance of Optional that contains {@linkplain Plugin} itself will be returned.
     * @return Instnace of {@linkplain Plugin Bukkit Plugin object}
     */
    @Override
    public Optional<Plugin> getInstance() {
        return Optional.of(bukkitPluginObject);
    }

    @Override
    public Logger getLogger() {
        return null;
    }
}
