package info.tritusk.dirtball.util;

import info.tritusk.dirtball.Dirtball;
import info.tritusk.dirtball.util.function.BukkitToSpongeItemStackMapper;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.meta.ItemMeta;
import org.spongepowered.api.effect.particle.ParticleOption;
import org.spongepowered.api.effect.particle.ParticleOptions;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.Tuple;

import java.util.Optional;

public class DirtballObjectsConverter {

    public static org.spongepowered.api.entity.living.player.gamemode.GameMode fromBukkit(org.bukkit.GameMode bukkitGameMode) {
        if (bukkitGameMode == null) {
            return GameModes.NOT_SET;
        }
        switch (bukkitGameMode) {
            case SURVIVAL: return GameModes.SURVIVAL;
            case CREATIVE: return GameModes.CREATIVE;
            case ADVENTURE: return GameModes.ADVENTURE;
            case SPECTATOR: return GameModes.SPECTATOR;
            default: return GameModes.NOT_SET; // Catch all
        }
    }

    public static org.bukkit.GameMode fromSponge(org.spongepowered.api.entity.living.player.gamemode.GameMode spongeGameMode) {
        if (spongeGameMode == GameModes.SURVIVAL) {
            return GameMode.SURVIVAL;
        } else if (spongeGameMode == GameModes.CREATIVE) {
            return GameMode.CREATIVE;
        } else if (spongeGameMode == GameModes.ADVENTURE) {
            return GameMode.ADVENTURE;
        } else if (spongeGameMode == GameModes.SPECTATOR) {
            return GameMode.SPECTATOR;
        } else {
            return null; // Hope this won't cause trouble.
        }
    }

    public static org.spongepowered.api.item.inventory.ItemStack fromBukkit(org.bukkit.inventory.ItemStack bukkitItemStack) {
        return BukkitToSpongeItemStackMapper.INSTANCE.apply(bukkitItemStack);
    }

    public static org.bukkit.inventory.ItemStack fromSponge(org.spongepowered.api.item.inventory.ItemStack spongeItemStack) {
        return null;
        //return new org.bukkit.inventory.ItemStack(spongeItemStack.getType(), spongeItemStack.getQuantity());
    }

    public static org.spongepowered.api.effect.particle.ParticleType fromBukkit(org.bukkit.Particle bukkitParticle) {
        switch (bukkitParticle) {
            case EXPLOSION_NORMAL: return ParticleTypes.EXPLOSION;
            case EXPLOSION_LARGE: return ParticleTypes.LARGE_EXPLOSION;
            case EXPLOSION_HUGE: return ParticleTypes.HUGE_EXPLOSION;
            case FIREWORKS_SPARK: return ParticleTypes.FIREWORKS_SPARK;
            case WATER_BUBBLE: return ParticleTypes.WATER_BUBBLE;
            case WATER_SPLASH: return ParticleTypes.WATER_SPLASH;
            case WATER_WAKE: return ParticleTypes.WATER_WAKE;
            case SUSPENDED: return ParticleTypes.SUSPENDED;
            case SUSPENDED_DEPTH: return ParticleTypes.SUSPENDED_DEPTH;
            case CRIT: return ParticleTypes.CRITICAL_HIT;
            case CRIT_MAGIC: return ParticleTypes.MAGIC_CRITICAL_HIT;
            case SMOKE_NORMAL: return ParticleTypes.SMOKE;
            case SMOKE_LARGE: return ParticleTypes.LARGE_SMOKE;
            case SPELL: return ParticleTypes.SPELL;
            case SPELL_INSTANT: return ParticleTypes.INSTANT_SPELL;
            case SPELL_MOB: return ParticleTypes.MOB_SPELL;
            case SPELL_MOB_AMBIENT: return ParticleTypes.AMBIENT_MOB_SPELL;
            case SPELL_WITCH: return ParticleTypes.WITCH_SPELL;
            case DRIP_WATER: return ParticleTypes.DRIP_WATER;
            case DRIP_LAVA: return ParticleTypes.DRIP_LAVA;
            case VILLAGER_ANGRY: return ParticleTypes.ANGRY_VILLAGER;
            case VILLAGER_HAPPY: return ParticleTypes.HAPPY_VILLAGER;
            case TOWN_AURA: return ParticleTypes.TOWN_AURA;
            case NOTE: return ParticleTypes.NOTE;
            case PORTAL: return ParticleTypes.PORTAL;
            case ENCHANTMENT_TABLE: return ParticleTypes.ENCHANTING_GLYPHS;
            case FLAME: return ParticleTypes.FLAME;
            case LAVA: return ParticleTypes.LAVA;
            case FOOTSTEP: return ParticleTypes.FOOTSTEP;
            case CLOUD: return ParticleTypes.CLOUD;
            case REDSTONE: return ParticleTypes.REDSTONE_DUST;
            case SNOWBALL: return ParticleTypes.SNOWBALL;
            case SNOW_SHOVEL: return ParticleTypes.SNOW_SHOVEL;
            case SLIME: return ParticleTypes.SLIME;
            case HEART: return ParticleTypes.HEART;
            case BARRIER: return ParticleTypes.BARRIER;
            case ITEM_CRACK: return ParticleTypes.ITEM_CRACK;
            case BLOCK_CRACK: return ParticleTypes.BLOCK_CRACK;
            case BLOCK_DUST: return ParticleTypes.BLOCK_DUST;
            case WATER_DROP: return ParticleTypes.WATER_DROP;
            case ITEM_TAKE: throw new UnsupportedOperationException("Particle.ITEM_TAKE temporarily unsupported");
            case MOB_APPEARANCE: return ParticleTypes.MOBSPAWNER_FLAMES;
            case DRAGON_BREATH: return ParticleTypes.DRAGON_BREATH;
            case END_ROD: return ParticleTypes.END_ROD;
            case DAMAGE_INDICATOR: return ParticleTypes.DAMAGE_INDICATOR;
            case SWEEP_ATTACK: return ParticleTypes.SWEEP_ATTACK;
            case FALLING_DUST: return ParticleTypes.FALLING_DUST;
            case TOTEM: throw new UnsupportedOperationException("Resurrection-free Implementation");
            case SPIT: throw new UnsupportedOperationException("Llama-free Implementation");
            default: throw new NullPointerException("Null value not permitted");
        }
    }

    public static <BUKKIT, SPONGE> Optional<Tuple<ParticleOption<SPONGE>, SPONGE>> fromBukkit(Particle bukkitParticle, Class<BUKKIT> bukkitDataType, BUKKIT bukkitData) {
        if (bukkitDataType == bukkitParticle.getDataType()) {
            switch (bukkitParticle) {
                case ITEM_CRACK:
                    if (!bukkitParticle.getDataType().isAssignableFrom(bukkitData.getClass())) {
                        IllegalArgumentException e = new IllegalArgumentException("Incompatible extra data detected");
                        Dirtball.platform.getLogger().error("Particle {} expects extra data with type of {}, but found {}", bukkitParticle, bukkitDataType, bukkitData.getClass(), e);
                        throw e;
                    }
                    org.bukkit.inventory.ItemStack bukkitItemStack = (org.bukkit.inventory.ItemStack)bukkitData;
                    org.spongepowered.api.item.inventory.ItemStack spongeItemStack = fromBukkit(bukkitItemStack);
                    return Optional.of(new Tuple<>((ParticleOption<SPONGE>) ParticleOptions.ITEM_STACK_SNAPSHOT, (SPONGE)spongeItemStack.createSnapshot()));
                case BLOCK_CRACK:
                case BLOCK_DUST:
                case FALLING_DUST:
                    if (!bukkitParticle.getDataType().isAssignableFrom(bukkitData.getClass())) {
                        IllegalArgumentException e = new IllegalArgumentException("Incompatible extra data detected");
                        Dirtball.platform.getLogger().error("Particle {} expects extra data with type of {}, but found {}", bukkitParticle, bukkitDataType, bukkitData.getClass(), e);
                        throw e;
                    }
                    return Optional.empty();
                default:
                    return Optional.empty();
            }
        } else {
            // TODO Warn user
            return Optional.empty();
        }
    }

    public static Optional<ParticleOption<?>> fromBukkitParticle(Particle bukkitParticle) {
        switch (bukkitParticle) {
            case ITEM_CRACK:
                return Optional.of(ParticleOptions.ITEM_STACK_SNAPSHOT);
            case BLOCK_CRACK:
            case BLOCK_DUST:
            case FALLING_DUST:
                return Optional.of(ParticleOptions.BLOCK_STATE);
            default:
                return Optional.empty();
        }
    }
}
