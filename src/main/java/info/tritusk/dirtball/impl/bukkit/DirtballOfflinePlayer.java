package info.tritusk.dirtball.impl.bukkit;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.spongepowered.api.entity.living.player.User;

import java.util.Map;
import java.util.UUID;

public class DirtballOfflinePlayer implements OfflinePlayer {

    private final User spongePlayer;

    public DirtballOfflinePlayer(User spongePlayer) {
        this.spongePlayer = spongePlayer;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public String getName() {
        return spongePlayer.getName();
    }

    @Override
    public UUID getUniqueId() {
        return spongePlayer.getUniqueId();
    }

    @Override
    public boolean isBanned() {
        return false;
    }

    @Override
    public boolean isWhitelisted() {
        return false;
    }

    @Override
    public void setWhitelisted(boolean value) {

    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public long getFirstPlayed() {
        return 0;
    }

    @Override
    public long getLastPlayed() {
        return 0;
    }

    @Override
    public boolean hasPlayedBefore() {
        return false;
    }

    @Override
    public Location getBedSpawnLocation() {
        return null;
    }

    @Override
    public Map<String, Object> serialize() {
        return null;
    }

    @Override
    public boolean isOp() {
        return false;
    }

    @Override
    public void setOp(boolean value) {

    }
}
