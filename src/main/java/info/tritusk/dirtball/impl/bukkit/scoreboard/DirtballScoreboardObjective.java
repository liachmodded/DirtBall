package info.tritusk.dirtball.impl.bukkit.scoreboard;

import org.apache.commons.lang3.Validate;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.spongepowered.api.scoreboard.Scoreboard;
import org.spongepowered.api.scoreboard.objective.Objective;
import org.spongepowered.api.text.Text;

public class DirtballScoreboardObjective implements org.bukkit.scoreboard.Objective {

    private final DirtballScoreboard wrapper;
    private final Scoreboard spongeScoreboard;
    private final Objective spongeObjective;

    DirtballScoreboardObjective(DirtballScoreboard wrapper, Scoreboard spongeScoreboard, Objective spongeObjective) {
        this.wrapper = wrapper;
        this.spongeScoreboard = spongeScoreboard;
        this.spongeObjective = spongeObjective;
        Validate.isTrue(wrapper.spongeScoreboard == spongeScoreboard);
    }

    @Override
    public String getName() throws IllegalStateException {
        return spongeObjective.getName();
    }

    @Override
    public String getDisplayName() throws IllegalStateException {
        return spongeObjective.getDisplayName().toPlain();
    }

    @Override
    public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException {
        this.spongeObjective.setDisplayName(Text.of(displayName));
    }

    @Override
    public String getCriteria() throws IllegalStateException {
        return spongeObjective.getCriterion().getId();
    }

    @Override
    public boolean isModifiable() throws IllegalStateException {
        return true; // TODO Assertion
    }

    @Override
    public org.bukkit.scoreboard.Scoreboard getScoreboard() {
        return wrapper;
    }

    @Override
    public void unregister() throws IllegalStateException {
        this.spongeScoreboard.removeObjective(this.spongeObjective);
    }

    @Override
    public void setDisplaySlot(DisplaySlot slot) throws IllegalStateException {

    }

    @Override
    public DisplaySlot getDisplaySlot() throws IllegalStateException {
        return null;
    }

    @Override
    public Score getScore(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public Score getScore(String entry) throws IllegalArgumentException, IllegalStateException {
        return null;
    }
}
