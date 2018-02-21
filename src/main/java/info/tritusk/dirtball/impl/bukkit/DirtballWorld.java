package info.tritusk.dirtball.impl.bukkit;

import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import info.tritusk.dirtball.Dirtball;
import info.tritusk.dirtball.impl.bukkit.entity.DirtballPlayer;
import info.tritusk.dirtball.util.DirtballMathUtil;
import info.tritusk.dirtball.util.DirtballObjectsConverter;
import org.bukkit.BlockChangeDelegate;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.TreeType;
import org.bukkit.WorldBorder;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.DimensionTypes;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.difficulty.Difficulties;
import org.spongepowered.api.world.explosion.Explosion;
import org.spongepowered.api.world.storage.WorldProperties;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DirtballWorld implements org.bukkit.World {

    private final org.spongepowered.api.world.World spongeWorld;

    public DirtballWorld(org.spongepowered.api.world.World world) {
        this.spongeWorld = world;
    }

    public org.spongepowered.api.world.World getSpongeWorld() {
        return spongeWorld;
    }

    @Override
    public Block getBlockAt(int x, int y, int z) {
        return null;
    }

    @Override
    public Block getBlockAt(Location location) {
        return null;
    }

    @Override
    public int getBlockTypeIdAt(int x, int y, int z) {
        return 0;
    }

    @Override
    public int getBlockTypeIdAt(Location location) {
        return 0;
    }

    @Override
    public int getHighestBlockYAt(int x, int z) {
        return spongeWorld.getHighestYAt(x, z);
    }

    @Override
    public int getHighestBlockYAt(Location location) {
        return spongeWorld.getHighestYAt(Vector2i.from(location.getBlockX(), location.getBlockZ()));
    }

    @Override
    public Block getHighestBlockAt(int x, int z) {
        return null;
    }

    @Override
    public Block getHighestBlockAt(Location location) {
        return null;
    }

    @Override
    public org.bukkit.Chunk getChunkAt(int x, int z) {
        return null;
    }

    @Override
    public org.bukkit.Chunk getChunkAt(Location location) {
        return null;
    }

    @Override
    public org.bukkit.Chunk getChunkAt(Block block) {
        return null;
    }

    @Override
    public boolean isChunkLoaded(org.bukkit.Chunk chunk) {
        return false;
    }

    @Override
    public org.bukkit.Chunk[] getLoadedChunks() {
        return StreamSupport.stream(spongeWorld.getLoadedChunks().spliterator(), true)
                .map(DirtballChunk::new)
                .toArray(org.bukkit.Chunk[]::new);
    }

    @Override
    public void loadChunk(org.bukkit.Chunk chunk) {
        Dirtball.platform.getLogger().warn("World::loadChunk(Chunk chunk) is not supported on this platform. Trying loading chunk at location of given chunk instead...");
        spongeWorld.loadChunk(chunk.getX() << 4, 0, chunk.getZ(), true);
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return spongeWorld.getChunk(x << 4, 0, z << 4).isPresent();
    }

    @Override
    public boolean isChunkInUse(int x, int z) {
        return spongeWorld.getChunk(x << 4, 0, z << 4)
                .filter(chunk -> !chunk
                        .getEntities(e -> e instanceof org.spongepowered.api.entity.living.player.Player)
                        .isEmpty())
                .isPresent();
    }

    @Override
    public void loadChunk(int x, int z) {
        spongeWorld.loadChunk(x << 4, 0, z << 4, true);
    }

    @Override
    public boolean loadChunk(int x, int z, boolean generate) {
        return spongeWorld.loadChunk(x << 4, 0, z << 4, generate).isPresent();
    }

    @Override
    public boolean unloadChunk(org.bukkit.Chunk chunk) {
        if (chunk instanceof DirtballChunk) {
            return spongeWorld.unloadChunk(((DirtballChunk) chunk).getSpongeChunk());
        } else {
            Optional<org.spongepowered.api.world.Chunk> c = spongeWorld.getChunk(chunk.getX() << 4, 0, chunk.getZ() << 4);
            return c.isPresent() && spongeWorld.unloadChunk(c.get());
        }
    }

    @Override
    public boolean unloadChunk(int x, int z) {
        return false;
    }

    @Override
    public boolean unloadChunk(int x, int z, boolean save) {
        return false;
    }

    @Override
    public boolean unloadChunk(int x, int z, boolean save, boolean safe) {
        return false;
    }

    @Override
    public boolean unloadChunkRequest(int x, int z) {
        return false;
    }

    @Override
    public boolean unloadChunkRequest(int x, int z, boolean safe) {
        return false;
    }

    @Override
    public boolean regenerateChunk(int x, int z) {
        return false;
    }

    @Override
    public boolean refreshChunk(int x, int z) {
        return false;
    }

    @Override
    public Item dropItem(Location location, ItemStack item) {
        return null;
    }

    @Override
    public Item dropItemNaturally(Location location, ItemStack item) {
        return null;
    }

    @Override
    public Arrow spawnArrow(Location location, Vector direction, float speed, float spread) {
        return null;
    }

    @Override
    public <T extends Arrow> T spawnArrow(Location location, Vector direction, float speed, float spread, Class<T> clazz) {
        return null;
    }

    @Override
    public boolean generateTree(Location location, TreeType type) {
        return false;
    }

    @Override
    public boolean generateTree(Location loc, TreeType type, BlockChangeDelegate delegate) {
        return false;
    }

    @Override
    public Entity spawnEntity(Location loc, EntityType type) {
        return null;
    }

    @Override
    public LightningStrike strikeLightning(Location loc) {
        return null;
    }

    @Override
    public LightningStrike strikeLightningEffect(Location loc) {
        return null;
    }

    @Override
    public List<Entity> getEntities() {
        return null;
    }

    @Override
    public List<LivingEntity> getLivingEntities() {
        return null;
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... classes) {
        return null;
    }

    @Override
    public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> cls) {
        return null;
    }

    @Override
    public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        return spongeWorld.getPlayers().stream().map(DirtballPlayer::new).collect(Collectors.toList());
    }

    @Override
    public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z) {
        return null;
    }

    @Override
    public String getName() {
        return spongeWorld.getName();
    }

    @Override
    public UUID getUID() {
        return spongeWorld.getUniqueId();
    }

    @Override
    public Location getSpawnLocation() {
        org.spongepowered.api.world.Location<World> spawn = spongeWorld.getSpawnLocation();
        return new Location(this, spawn.getX(), spawn.getY(), spawn.getZ());
    }

    @Override
    public boolean setSpawnLocation(Location location) {
        spongeWorld.getProperties().setSpawnPosition(DirtballMathUtil.vector3dOf(location).toInt());
        return true;
    }

    @Override
    public boolean setSpawnLocation(int x, int y, int z) {
        spongeWorld.getProperties().setSpawnPosition(Vector3i.from(x, y, z));
        return true;
    }

    @Override
    public long getTime() {
        return 0;
    }

    @Override
    public void setTime(long time) {

    }

    @Override
    public long getFullTime() {
        return 0;
    }

    @Override
    public void setFullTime(long time) {

    }

    @Override
    public boolean hasStorm() {
        WorldProperties properties = spongeWorld.getProperties();
        return properties.isRaining() && properties.isThundering();
    }

    @Override
    public void setStorm(boolean hasStorm) {

    }

    @Override
    public int getWeatherDuration() {
        return 0;
    }

    @Override
    public void setWeatherDuration(int duration) {
        spongeWorld.setWeather(spongeWorld.getWeather(), duration);
    }

    /**
     * {@inheritDoc}
     *
     * @implNote
     * This method will determine whether the world has thunderstorm going on, regardless the main weather.
     * If checking main weather is required, use {@link org.bukkit.World#hasStorm()}.
     */
    @Override
    public boolean isThundering() {
        return spongeWorld.getProperties().isThundering();
    }

    @Override
    public void setThundering(boolean thundering) {
        spongeWorld.getProperties().setThundering(thundering);
    }

    @Override
    public int getThunderDuration() {
        return spongeWorld.getProperties().getThunderTime();
    }

    @Override
    public void setThunderDuration(int duration) {
        spongeWorld.getProperties().setThunderTime(duration);
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power) {
        Explosion explosion = Explosion.builder()
                .location(DirtballMathUtil.locationOf(spongeWorld, x, y, z))
                .radius(power)
                .build();
        spongeWorld.triggerExplosion(explosion);
        Dirtball.platform.getLogger().error("createExplosion currently cannot determine whether the explosion is cancelled or not. Assuming true by default");
        return true;
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
        Explosion explosion = Explosion.builder()
                .location(DirtballMathUtil.locationOf(spongeWorld, x, y, z))
                .radius(power)
                .canCauseFire(setFire)
                .build();
        spongeWorld.triggerExplosion(explosion);
        Dirtball.platform.getLogger().error("createExplosion currently cannot determine whether the explosion is cancelled or not. Assuming true by default");
        return true;
    }

    @Override
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
        Explosion explosion = Explosion.builder()
                .location(DirtballMathUtil.locationOf(spongeWorld, x, y, z))
                .radius(power)
                .canCauseFire(setFire)
                .shouldBreakBlocks(breakBlocks)
                .build();
        spongeWorld.triggerExplosion(explosion);
        Dirtball.platform.getLogger().error("createExplosion currently cannot determine whether the explosion is cancelled or not. Assuming true by default");
        return true;
    }

    @Override
    public boolean createExplosion(Location loc, float power) {
        Explosion explosion = Explosion.builder()
                .location(DirtballMathUtil.locationOf(spongeWorld, loc))
                .radius(power)
                .build();
        spongeWorld.triggerExplosion(explosion);
        Dirtball.platform.getLogger().error("createExplosion currently cannot determine whether the explosion is cancelled or not. Assuming true by default");
        return true;
    }

    @Override
    public boolean createExplosion(Location loc, float power, boolean setFire) {
        Explosion explosion = Explosion.builder()
                .location(DirtballMathUtil.locationOf(spongeWorld, loc))
                .radius(power)
                .canCauseFire(setFire)
                .build();
        spongeWorld.triggerExplosion(explosion);
        Dirtball.platform.getLogger().error("createExplosion currently cannot determine whether the explosion is cancelled or not. Assuming true by default");
        return true;
    }

    @Override
    public Environment getEnvironment() {
        DimensionType dimensionType = spongeWorld.getProperties().getDimensionType();
        if (dimensionType == DimensionTypes.OVERWORLD) {
            return Environment.NORMAL;
        } else if (dimensionType == DimensionTypes.NETHER) {
            return Environment.NETHER;
        } else if (dimensionType == DimensionTypes.THE_END) {
            return Environment.THE_END;
        } else {
            Dirtball.platform.getLogger().warn("Cannot convert Sponge DimensionType {} to Bukkit counterpart, assuming Environment.NORMAL.", dimensionType);
            return Environment.NORMAL;
        }
    }

    @Override
    public long getSeed() {
        return spongeWorld.getProperties().getSeed();
    }

    @Override
    public boolean getPVP() {
        return spongeWorld.getProperties().isPVPEnabled();
    }

    @Override
    public void setPVP(boolean pvp) {
        spongeWorld.getProperties().setPVPEnabled(pvp);
    }

    @Override
    public ChunkGenerator getGenerator() {
        return null;
    }

    @Override
    public void save() {
        try {
            spongeWorld.save();
        } catch (IOException e) {
            Dirtball.platform.getLogger().error("Failed on saving world", e);
        }
    }

    @Override
    public List<BlockPopulator> getPopulators() {
        return null;
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
        return null;
    }

    @Override
    public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> function) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, MaterialData data) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, Material material, byte data) throws IllegalArgumentException {
        return null;
    }

    @Override
    public FallingBlock spawnFallingBlock(Location location, int blockId, byte blockData) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void playEffect(Location location, Effect effect, int data) {

    }

    @Override
    public void playEffect(Location location, Effect effect, int data, int radius) {

    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T data) {

    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T data, int radius) {

    }

    @Override
    public ChunkSnapshot getEmptyChunkSnapshot(int x, int z, boolean includeBiome, boolean includeBiomeTempRain) {
        return null;
    }

    @Override
    public void setSpawnFlags(boolean allowMonsters, boolean allowAnimals) {

    }

    @Override
    public boolean getAllowAnimals() {
        return false;
    }

    @Override
    public boolean getAllowMonsters() {
        return false;
    }

    @Override
    public Biome getBiome(int x, int z) {
        return null;
    }

    @Override
    public void setBiome(int x, int z, Biome bio) {

    }

    @Override
    public double getTemperature(int x, int z) {
        return spongeWorld.getBiome(x, 63, z).getTemperature();
    }

    @Override
    public double getHumidity(int x, int z) {
        return spongeWorld.getBiome(x, 63, z).getHumidity();
    }

    @Override
    public int getMaxHeight() {
        return spongeWorld.getBlockMax().getY();
    }

    @Override
    public int getSeaLevel() {
        return spongeWorld.getSeaLevel();
    }

    @Override
    public boolean getKeepSpawnInMemory() {
        return spongeWorld.doesKeepSpawnLoaded();
    }

    @Override
    public void setKeepSpawnInMemory(boolean keepLoaded) {
        spongeWorld.setKeepSpawnLoaded(keepLoaded);
    }

    @Override
    public boolean isAutoSave() {
        return false;
    }

    @Override
    public void setAutoSave(boolean value) {

    }

    @Override
    public void setDifficulty(org.bukkit.Difficulty difficultyBukkit) {
        org.spongepowered.api.world.difficulty.Difficulty difficultySponge = null;
        switch (difficultyBukkit) {
            case PEACEFUL: difficultySponge = Difficulties.PEACEFUL; break;
            case EASY: difficultySponge = Difficulties.EASY; break;
            case NORMAL: difficultySponge = Difficulties.NORMAL; break;
            case HARD: difficultySponge = Difficulties.HARD; break;
        }
        spongeWorld.getProperties().setDifficulty(Objects.requireNonNull(difficultySponge, "Missing valid difficulty setting"));
    }

    @Override
    public org.bukkit.Difficulty getDifficulty() {
        org.spongepowered.api.world.difficulty.Difficulty difficulty = spongeWorld.getDifficulty();
        if (difficulty == Difficulties.PEACEFUL) {
            return org.bukkit.Difficulty.PEACEFUL;
        } else if (difficulty == Difficulties.EASY) {
            return org.bukkit.Difficulty.EASY;
        } else if (difficulty == Difficulties.NORMAL) {
            return org.bukkit.Difficulty.NORMAL;
        } else if (difficulty == Difficulties.HARD) {
            return org.bukkit.Difficulty.HARD;
        } else {
            throw new RuntimeException("Missing valid difficulty setting");
        }
    }

    @Override
    public File getWorldFolder() {
        return spongeWorld.getDirectory().toFile();
    }

    @Override
    public WorldType getWorldType() {
        return null;
    }

    @Override
    public boolean canGenerateStructures() {
        return spongeWorld.getProperties().usesMapFeatures();
    }

    @Override
    public long getTicksPerAnimalSpawns() {
        return 0;
    }

    @Override
    public void setTicksPerAnimalSpawns(int ticksPerAnimalSpawns) {

    }

    @Override
    public long getTicksPerMonsterSpawns() {
        return 0;
    }

    @Override
    public void setTicksPerMonsterSpawns(int ticksPerMonsterSpawns) {

    }

    @Override
    public int getMonsterSpawnLimit() {
        return 0;
    }

    @Override
    public void setMonsterSpawnLimit(int limit) {

    }

    @Override
    public int getAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public void setAnimalSpawnLimit(int limit) {

    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        return 0;
    }

    @Override
    public void setWaterAnimalSpawnLimit(int limit) {

    }

    @Override
    public int getAmbientSpawnLimit() {
        return 0;
    }

    @Override
    public void setAmbientSpawnLimit(int limit) {

    }

    @Override
    public void playSound(Location location, Sound sound, float volume, float pitch) {

    }

    @Override
    public void playSound(Location location, String sound, float volume, float pitch) {

    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch) {

    }

    @Override
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch) {

    }

    @Override
    public String[] getGameRules() {
        return spongeWorld.getGameRules().keySet().toArray(new String[0]);
    }

    @Override
    public String getGameRuleValue(String rule) {
        return spongeWorld.getGameRule(rule).orElse(null);
    }

    @Override
    public boolean setGameRuleValue(String rule, String value) {
        spongeWorld.getProperties().setGameRule(rule, value);
        Dirtball.platform.getLogger().warn("Gamerule {} has been tried to update to {}; while Dirtball cannot determine whether it's successful or not. Assuming successful.", rule, value);
        return true;
    }

    @Override
    public boolean isGameRule(String rule) {
        return spongeWorld.getGameRule(rule).isPresent();
    }

    @Override
    public WorldBorder getWorldBorder() {
        return null;
    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count) {
        spongeWorld.spawnParticles(ParticleEffect.builder()
                .type(DirtballObjectsConverter.fromBukkit(particle))
                .quantity(count)
                .build(), DirtballMathUtil.vector3dOf(location));
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count) {
        spongeWorld.spawnParticles(ParticleEffect.builder()
                .type(DirtballObjectsConverter.fromBukkit(particle))
                .quantity(count)
                .build(), Vector3d.from(x, y, z));
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, T data) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        spongeWorld.spawnParticles(ParticleEffect.builder()
                .type(DirtballObjectsConverter.fromBukkit(particle))
                .quantity(count)
                .offset(Vector3d.from(offsetX, offsetY, offsetZ))
                .build(), DirtballMathUtil.vector3dOf(location));
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        spongeWorld.spawnParticles(ParticleEffect.builder()
                .type(DirtballObjectsConverter.fromBukkit(particle))
                .quantity(count)
                .offset(Vector3d.from(offsetX, offsetY, offsetZ))
                .build(), Vector3d.from(x, y, z));
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {

    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        Dirtball.platform.getLogger().warn("spawnParticle(Particle, Location, int, double, double, double, double) is invoked, assuming last double is velocity vector.");
        spongeWorld.spawnParticles(ParticleEffect.builder()
                .type(DirtballObjectsConverter.fromBukkit(particle))
                .quantity(count)
                .offset(Vector3d.from(offsetX, offsetY, offsetZ))
                .velocity(Vector3d.from(extra))
                .build(), DirtballMathUtil.vector3dOf(location));
    }

    @Override
    public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        Dirtball.platform.getLogger().warn("spawnParticle(Particle, double, double, double, int, double, double, double, double) is invoked, assuming last double is velocity vector.");
        spongeWorld.spawnParticles(ParticleEffect.builder()
                .type(DirtballObjectsConverter.fromBukkit(particle))
                .quantity(count)
                .offset(Vector3d.from(offsetX, offsetY, offsetZ))
                .velocity(Vector3d.from(extra))
                .build(), Vector3d.from(x, y, z));
    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        ParticleType spongeParticle = DirtballObjectsConverter.fromBukkit(particle);
        spongeWorld.spawnParticles(ParticleEffect.builder()
                .type(DirtballObjectsConverter.fromBukkit(particle))
                .quantity(count)
                .offset(Vector3d.from(offsetX, offsetY, offsetZ))
                .velocity(Vector3d.from(extra))
                .build(), DirtballMathUtil.vector3dOf(location));
    }

    @Override
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {

    }

    @Override
    public Spigot spigot() {
        return DirtballWorldSpigot.INSTANCE;
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {

    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return null;
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return false;
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {

    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return null;
    }

    private static final class DirtballWorldSpigot extends org.bukkit.World.Spigot {
        public static final DirtballWorldSpigot INSTANCE = new DirtballWorldSpigot();
    }
}
