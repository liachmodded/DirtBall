package info.tritusk.dirtball.impl.bukkit.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.spongepowered.api.scoreboard.Team;
import org.spongepowered.api.text.Text;

import java.util.Set;

public class DirtballScoreboardTeam implements org.bukkit.scoreboard.Team {

    final Team spongeTeam;

    DirtballScoreboardTeam(Team spongeTeam) {
        this.spongeTeam = spongeTeam;
    }

    @Override
    public String getName() throws IllegalStateException {
        return spongeTeam.getName();
    }

    @Override
    public String getDisplayName() throws IllegalStateException {
        return spongeTeam.getDisplayName().toString();
    }

    @Override
    public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException {
        spongeTeam.setDisplayName(Text.of(displayName));
    }

    @Override
    public String getPrefix() throws IllegalStateException {
        return spongeTeam.getPrefix().toString();
    }

    @Override
    public void setPrefix(String prefix) throws IllegalStateException, IllegalArgumentException {
        spongeTeam.setPrefix(Text.of(prefix));
    }

    @Override
    public String getSuffix() throws IllegalStateException {
        return spongeTeam.getSuffix().toString();
    }

    @Override
    public void setSuffix(String suffix) throws IllegalStateException, IllegalArgumentException {
        spongeTeam.setPrefix(Text.of(suffix));
    }

    @Override
    public ChatColor getColor() throws IllegalStateException {
        return null;
    }

    @Override
    public void setColor(ChatColor color) {

    }

    @Override
    public boolean allowFriendlyFire() throws IllegalStateException {
        return spongeTeam.allowFriendlyFire();
    }

    @Override
    public void setAllowFriendlyFire(boolean enabled) throws IllegalStateException {
        spongeTeam.setAllowFriendlyFire(enabled);
    }

    @Override
    public boolean canSeeFriendlyInvisibles() throws IllegalStateException {
        return spongeTeam.canSeeFriendlyInvisibles();
    }

    @Override
    public void setCanSeeFriendlyInvisibles(boolean enabled) throws IllegalStateException {
        spongeTeam.setCanSeeFriendlyInvisibles(enabled);
    }

    @Override
    public NameTagVisibility getNameTagVisibility() throws IllegalArgumentException {
        return null;
    }

    @Override
    public void setNameTagVisibility(NameTagVisibility visibility) throws IllegalArgumentException {

    }

    @Override
    public Set<OfflinePlayer> getPlayers() throws IllegalStateException {
        return null;
    }

    @Override
    public Set<String> getEntries() throws IllegalStateException {
        return null;
    }

    @Override
    public int getSize() throws IllegalStateException {
        return 0;
    }

    @Override
    public Scoreboard getScoreboard() {
        return null;
    }

    @Override
    public void addPlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException {

    }

    @Override
    public void addEntry(String entry) throws IllegalStateException, IllegalArgumentException {

    }

    @Override
    public boolean removePlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException {
        return false;
    }

    @Override
    public boolean removeEntry(String entry) throws IllegalStateException, IllegalArgumentException {
        return false;
    }

    @Override
    public void unregister() throws IllegalStateException {

    }

    @Override
    public boolean hasPlayer(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
        return false;
    }

    @Override
    public boolean hasEntry(String entry) throws IllegalArgumentException, IllegalStateException {
        return false;
    }

    @Override
    public OptionStatus getOption(Option option) throws IllegalStateException {
        return null;
    }

    @Override
    public void setOption(Option option, OptionStatus status) throws IllegalStateException {

    }
}
