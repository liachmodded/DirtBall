package info.tritusk.dirtball.util.function;

import java.util.function.Function;

public final class BukkitToSpongeItemStackMapper
        implements Function<org.bukkit.inventory.ItemStack, org.spongepowered.api.item.inventory.ItemStack> {

    public static final BukkitToSpongeItemStackMapper INSTANCE = new BukkitToSpongeItemStackMapper();

    private BukkitToSpongeItemStackMapper() {}

    @Override
    public org.spongepowered.api.item.inventory.ItemStack apply(org.bukkit.inventory.ItemStack bukkitItemStack) {
        return org.spongepowered.api.item.inventory.ItemStack.empty();
    }
}
