package info.tritusk.dirtball;

public final class DirtballConstants {

    private DirtballConstants() {
        throw new UnsupportedOperationException("You just don't get instance.");
    }

    public static final String PLUGIN_ID = "dirtball";

    public static final String PLUGIN_NAME = "DirtBall";

    public static final String BUKKIT_IMPL_NAME = "Dirtball";

    public static final String BUKKIT_IMPL_VERSION = "0.0.0";

    public static final String BUKKIT_API_VERSION = "1.12.2-R0.1";

    public static final boolean USE_BUKKIT_UNSAFE_VALUES = Boolean.parseBoolean(System.getProperty("dirtball.enableUnsafeValues"));
}
