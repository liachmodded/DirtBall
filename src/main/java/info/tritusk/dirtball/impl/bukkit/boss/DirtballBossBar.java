package info.tritusk.dirtball.impl.bukkit.boss;

import info.tritusk.dirtball.impl.bukkit.entity.DirtballPlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.spongepowered.api.boss.BossBarColor;
import org.spongepowered.api.boss.BossBarColors;
import org.spongepowered.api.boss.BossBarOverlay;
import org.spongepowered.api.boss.BossBarOverlays;
import org.spongepowered.api.boss.ServerBossBar;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.stream.Collectors;

public class DirtballBossBar implements BossBar {

    private final ServerBossBar spongeBossBar;

    public DirtballBossBar(ServerBossBar bossBar) {
        this.spongeBossBar = bossBar;
    }

    @Override
    public String getTitle() {
        return spongeBossBar.getName().toPlain();
    }

    @Override
    public void setTitle(String title) {
        this.spongeBossBar.setName(Text.of(title));
    }

    @Override
    public BarColor getColor() {
        BossBarColor color = spongeBossBar.getColor();
        if (color == BossBarColors.RED) {
            return BarColor.RED;
        } else if (color == BossBarColors.BLUE) {
            return BarColor.BLUE;
        } else if (color == BossBarColors.PINK) {
            return BarColor.PINK;
        } else if (color == BossBarColors.GREEN) {
            return BarColor.GREEN;
        } else if (color == BossBarColors.WHITE) {
            return BarColor.WHITE;
        } else if (color == BossBarColors.PURPLE) {
            return BarColor.PURPLE;
        } else if (color == BossBarColors.YELLOW) {
            return BarColor.YELLOW;
        } else {
            return null;
        }
    }

    @Override
    public void setColor(BarColor color) {
        if (color == null) {
            return;
        }

        switch (color) {
            case RED: this.spongeBossBar.setColor(BossBarColors.RED); break;
            case BLUE: this.spongeBossBar.setColor(BossBarColors.BLUE); break;
            case PINK: this.spongeBossBar.setColor(BossBarColors.PINK); break;
            case GREEN: this.spongeBossBar.setColor(BossBarColors.GREEN); break;
            case WHITE: this.spongeBossBar.setColor(BossBarColors.WHITE); break;
            case PURPLE: this.spongeBossBar.setColor(BossBarColors.PURPLE); break;
            case YELLOW: this.spongeBossBar.setColor(BossBarColors.YELLOW); break;
            default: break;
        }
    }

    @Override
    public BarStyle getStyle() {
        BossBarOverlay overlay = spongeBossBar.getOverlay();
        if (overlay == BossBarOverlays.PROGRESS) {
            return BarStyle.SOLID;
        } else if (overlay == BossBarOverlays.NOTCHED_6) {
            return BarStyle.SEGMENTED_6;
        } else if (overlay == BossBarOverlays.NOTCHED_10) {
            return BarStyle.SEGMENTED_10;
        } else if (overlay == BossBarOverlays.NOTCHED_12) {
            return BarStyle.SEGMENTED_12;
        } else if (overlay == BossBarOverlays.NOTCHED_20) {
            return BarStyle.SEGMENTED_20;
        } else {
            return null;
        }
    }

    @Override
    public void setStyle(BarStyle style) {
        if (style == null) {
            return;
        }

        switch (style) {
            case SOLID: spongeBossBar.setOverlay(BossBarOverlays.PROGRESS);
            case SEGMENTED_6: spongeBossBar.setOverlay(BossBarOverlays.NOTCHED_6);
            case SEGMENTED_10: spongeBossBar.setOverlay(BossBarOverlays.NOTCHED_10);
            case SEGMENTED_12: spongeBossBar.setOverlay(BossBarOverlays.NOTCHED_12);
            case SEGMENTED_20: spongeBossBar.setOverlay(BossBarOverlays.NOTCHED_20);
        }
    }

    @Override
    public void removeFlag(BarFlag flag) {
        if (flag == null) {
            return;
        }

        switch (flag) {
            case CREATE_FOG: spongeBossBar.setCreateFog(false);
            case DARKEN_SKY: spongeBossBar.setDarkenSky(false);
            case PLAY_BOSS_MUSIC: spongeBossBar.setPlayEndBossMusic(false);
        }
    }

    @Override
    public void addFlag(BarFlag flag) {
        if (flag == null) {
            return;
        }

        switch (flag) {
            case CREATE_FOG: spongeBossBar.setCreateFog(true);
            case DARKEN_SKY: spongeBossBar.setDarkenSky(true);
            case PLAY_BOSS_MUSIC: spongeBossBar.setPlayEndBossMusic(true);
        }
    }

    @Override
    public boolean hasFlag(BarFlag flag) {
        if (flag == null) {
            return false;
        }

        switch (flag) {
            case CREATE_FOG:
                return spongeBossBar.shouldCreateFog();
            case DARKEN_SKY:
                return spongeBossBar.shouldDarkenSky();
            case PLAY_BOSS_MUSIC:
                return spongeBossBar.shouldPlayEndBossMusic();
            default:
                return false;
        }
    }

    @Override
    public void setProgress(double progress) {
        spongeBossBar.setPercent((float)progress);
    }

    @Override
    public double getProgress() {
        return spongeBossBar.getPercent();
    }

    @Override
    public void addPlayer(Player player) {
        if (player instanceof DirtballPlayer) {
            spongeBossBar.addPlayer(((DirtballPlayer) player).getSpongePlayer());
        }
    }

    @Override
    public void removePlayer(Player player) {
        if (player instanceof DirtballPlayer) {
            spongeBossBar.removePlayer(((DirtballPlayer) player).getSpongePlayer());
        }
    }

    @Override
    public void removeAll() {
        spongeBossBar.removePlayers(spongeBossBar.getPlayers());
    }

    @Override
    public List<Player> getPlayers() {
        return spongeBossBar.getPlayers().stream().map(DirtballPlayer::new).collect(Collectors.toList());
    }

    @Override
    public void setVisible(boolean visible) {
        spongeBossBar.setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return spongeBossBar.isVisible();
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public void show() {
        spongeBossBar.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public void hide() {
        spongeBossBar.setVisible(false);
    }
}
