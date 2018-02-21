package info.tritusk.dirtball.impl.bukkit;

import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.spongepowered.api.world.Chunk;

public class DirtballChunk implements org.bukkit.Chunk {

    private final org.spongepowered.api.world.Chunk spongeChunk;

    public DirtballChunk(org.spongepowered.api.world.Chunk chunk) {
        this.spongeChunk = chunk;
    }

    public Chunk getSpongeChunk() {
        return spongeChunk;
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated Need clarification on {@link org.spongepowered.api.world.Chunk#getPosition}.
     */
    @Deprecated
    @Override
    public int getX() {
        return spongeChunk.getPosition().getX();
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated Need clarification on {@link org.spongepowered.api.world.Chunk#getPosition}.
     */
    @Deprecated
    @Override
    public int getZ() {
        return spongeChunk.getPosition().getZ();
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public Block getBlock(int x, int y, int z) {
        return null;
    }

    @Override
    public ChunkSnapshot getChunkSnapshot() {
        return null;
    }

    @Override
    public ChunkSnapshot getChunkSnapshot(boolean includeMaxblocky, boolean includeBiome, boolean includeBiomeTempRain) {
        return null;
    }

    @Override
    public Entity[] getEntities() {
        return new Entity[0];
    }

    @Override
    public BlockState[] getTileEntities() {
        return new BlockState[0];
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public boolean load(boolean generate) {
        return false;
    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean unload(boolean save, boolean safe) {
        return false;
    }

    @Override
    public boolean unload(boolean save) {
        return false;
    }

    @Override
    public boolean unload() {
        return false;
    }

    @Override
    public boolean isSlimeChunk() {
        return false;
    }
}
