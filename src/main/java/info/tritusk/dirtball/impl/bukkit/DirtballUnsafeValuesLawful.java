package info.tritusk.dirtball.impl.bukkit;

import org.bukkit.Achievement;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.UnsafeValues;
import org.bukkit.advancement.Advancement;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * An implementation of {@link UnsafeValues} that always throws exceptions on any of its
 * methods being invoked.
 * <p>
 * The name of this class is a reference to <a href="https://github.com/asiekierka/FoamFix">FoamFix</a>,
 * a Minecraft mod authored by <a href="https://github.com/asiekierka">asie (Adrain Siekieria)</a>.
 * </p>
 */
public class DirtballUnsafeValuesLawful implements UnsafeValues {
    @Override
    public Material getMaterialFromInternalName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> tabCompleteInternalMaterialName(String token, List<String> completions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemStack modifyItemStack(ItemStack stack, String arguments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Statistic getStatisticFromInternalName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Achievement getAchievementFromInternalName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> tabCompleteInternalStatisticOrAchievementName(String token, List<String> completions) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Advancement loadAdvancement(NamespacedKey key, String advancement) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAdvancement(NamespacedKey key) {
        throw new UnsupportedOperationException();
    }
}
