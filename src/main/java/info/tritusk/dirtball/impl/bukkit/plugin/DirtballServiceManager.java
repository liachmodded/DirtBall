package info.tritusk.dirtball.impl.bukkit.plugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public final class DirtballServiceManager implements ServicesManager {

    private final Map<Class<?>, List<?>> servicesByClass = new IdentityHashMap<>();
    private final Map<Plugin, ?> servicesByPlugin = new IdentityHashMap<>();

    @Override
    public <T> void register(Class<T> service, T provider, Plugin plugin, ServicePriority priority) {

    }

    @Override
    public void unregisterAll(Plugin plugin) {

    }

    @Override
    public void unregister(Class<?> service, Object provider) {
        servicesByClass.getOrDefault(service, Collections.EMPTY_LIST).remove(provider);
    }

    @Override
    public void unregister(Object provider) {

    }

    @Override
    public <T> T load(Class<T> service) {
        return null;
    }

    @Override
    public <T> RegisteredServiceProvider<T> getRegistration(Class<T> service) {
        return null;
    }

    @Override
    public List<RegisteredServiceProvider<?>> getRegistrations(Plugin plugin) {
        return null;
    }

    @Override
    public <T> Collection<RegisteredServiceProvider<T>> getRegistrations(Class<T> service) {
        return null;
    }

    @Override
    public Collection<Class<?>> getKnownServices() {
        return null;
    }

    @Override
    public <T> boolean isProvidedFor(Class<T> service) {
        return false;
    }
}
