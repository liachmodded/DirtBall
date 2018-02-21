package info.tritusk.dirtball.impl.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.spongepowered.api.scoreboard.Score;

public class DirtballScoreboardScore implements org.bukkit.scoreboard.Score {

    final Score spongeScore;

    DirtballScoreboardScore(Score spongeScore) {
        this.spongeScore = spongeScore;
    }

    @Override
    public OfflinePlayer getPlayer() {
        return null;
    }

    @Override
    public String getEntry() {
        return spongeScore.toString();
    }

    @Override
    public Objective getObjective() {
        return null;
    }

    @Override
    public int getScore() throws IllegalStateException {
        return spongeScore.getScore();
    }

    @Override
    public void setScore(int score) throws IllegalStateException {
        spongeScore.setScore(score);
    }

    @Override
    public boolean isScoreSet() throws IllegalStateException {
        return true; // TODO Assertion
    }

    @Override
    public Scoreboard getScoreboard() {
        return null;
    }
}
