package info.tritusk.dirtball.impl.bukkit.plugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;

import java.util.Collection;
import java.util.List;

public class DirtballServicesManager implements ServicesManager {
    @Override
    public <T> void register(Class<T> service, T provider, Plugin plugin, ServicePriority priority) {

    }

    @Override
    public void unregisterAll(Plugin plugin) {

    }

    @Override
    public void unregister(Class<?> service, Object provider) {

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
