package info.tritusk.dirtball.impl.bukkit.plugin.messaging;

import info.tritusk.dirtball.DirtballConstants;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.plugin.messaging.PluginMessageListenerRegistration;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.network.ChannelBinding;

import java.util.Set;

public class DirtballMessenger implements Messenger {

    private final ChannelBinding.RawDataChannel masterChannel;

    private DirtballMessenger() {
        this.masterChannel = Sponge.getChannelRegistrar().createRawChannel(
                Sponge.getPluginManager()
                        .getPlugin(DirtballConstants.PLUGIN_ID)
                        .orElseThrow(IllegalStateException::new)
                        .getInstance()
                        .orElseThrow(IllegalStateException::new)
        , "bukkit_messenger");
    }

    @Override
    public boolean isReservedChannel(String channel) {
        return false;
    }

    @Override
    public void registerOutgoingPluginChannel(Plugin plugin, String channel) {

    }

    @Override
    public void unregisterOutgoingPluginChannel(Plugin plugin, String channel) {

    }

    @Override
    public void unregisterOutgoingPluginChannel(Plugin plugin) {

    }

    @Override
    public PluginMessageListenerRegistration registerIncomingPluginChannel(Plugin plugin, String channel, PluginMessageListener listener) {
        return null;
    }

    @Override
    public void unregisterIncomingPluginChannel(Plugin plugin, String channel, PluginMessageListener listener) {

    }

    @Override
    public void unregisterIncomingPluginChannel(Plugin plugin, String channel) {

    }

    @Override
    public void unregisterIncomingPluginChannel(Plugin plugin) {

    }

    @Override
    public Set<String> getOutgoingChannels() {
        return null;
    }

    @Override
    public Set<String> getOutgoingChannels(Plugin plugin) {
        return null;
    }

    @Override
    public Set<String> getIncomingChannels() {
        return null;
    }

    @Override
    public Set<String> getIncomingChannels(Plugin plugin) {
        return null;
    }

    @Override
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(Plugin plugin) {
        return null;
    }

    @Override
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(String channel) {
        return null;
    }

    @Override
    public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(Plugin plugin, String channel) {
        return null;
    }

    @Override
    public boolean isRegistrationValid(PluginMessageListenerRegistration registration) {
        return false;
    }

    @Override
    public boolean isIncomingChannelRegistered(Plugin plugin, String channel) {
        return false;
    }

    @Override
    public boolean isOutgoingChannelRegistered(Plugin plugin, String channel) {
        return false;
    }

    @Override
    public void dispatchIncomingMessage(Player source, String channel, byte[] message) {

    }
}
