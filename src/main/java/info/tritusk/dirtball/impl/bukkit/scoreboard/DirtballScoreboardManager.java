package info.tritusk.dirtball.impl.bukkit.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.spongepowered.api.Sponge;

public class DirtballScoreboardManager implements ScoreboardManager {

    public static final DirtballScoreboardManager INSTANCE = new DirtballScoreboardManager();

    private DirtballScoreboardManager() {}

    @Override
    public Scoreboard getMainScoreboard() {
        return Sponge.getServer().getServerScoreboard().map(DirtballScoreboard::new).orElseThrow(NullPointerException::new);
    }

    @Override
    public Scoreboard getNewScoreboard() {
        return new DirtballScoreboard(org.spongepowered.api.scoreboard.Scoreboard.builder().build());
    }
}
