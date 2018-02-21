package info.tritusk.dirtball.impl.bukkit.util;

import org.bukkit.util.CachedServerIcon;
import org.spongepowered.api.network.status.Favicon;

public class DirtballServerIcon implements CachedServerIcon {

    private final Favicon spongeIcon;

    public DirtballServerIcon(Favicon icon) {
        this.spongeIcon = icon;
    }

    public Favicon getIcon() {
        return spongeIcon;
    }
}
