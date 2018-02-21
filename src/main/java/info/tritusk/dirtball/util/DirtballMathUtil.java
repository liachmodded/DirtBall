package info.tritusk.dirtball.util;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.world.World;

public class DirtballMathUtil {

    public static Vector3d vector3dOf(org.bukkit.Location location) {
        return new Vector3d(location.getX(), location.getY(), location.getZ());
    }

    public static org.spongepowered.api.world.Location<World> locationOf(World spongeWorld, double x, double y, double z) {
        return new org.spongepowered.api.world.Location<>(spongeWorld, x, y, z);
    }

    public static org.spongepowered.api.world.Location<World> locationOf(World spongeWorld, org.bukkit.Location location) {
        return new org.spongepowered.api.world.Location<>(spongeWorld, vector3dOf(location));
    }
}
