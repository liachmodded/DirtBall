package info.tritusk.dirtball.impl.bukkit;

import info.tritusk.dirtball.Dirtball;
import org.bukkit.Achievement;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Statistic;
import org.bukkit.UnsafeValues;
import org.bukkit.advancement.Advancement;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

/**
 * An implementation of {@link UnsafeValues} that always try its best to return the correct values.
 *
 * <p>
 * The name of this class is a reference to <a href="https://github.com/asiekierka/FoamFix">FoamFix</a>,
 * a Minecraft mod authored by <a href="https://github.com/asiekieria">asie (Adrain Siekieria)</a>.
 * </p>
 */
public class DirtballUnsafeValuesAnarchy implements UnsafeValues {
    @Override
    public Material getMaterialFromInternalName(String name) {
        return null;
    }

    @Override
    public List<String> tabCompleteInternalMaterialName(String token, List<String> completions) {
        return Collections.emptyList();
    }

    @Override
    public ItemStack modifyItemStack(ItemStack stack, String arguments) {
        return null;
    }

    @Override
    public Statistic getStatisticFromInternalName(String name) {
        return null;
    }

    @Override
    public Achievement getAchievementFromInternalName(String name) {
        Dirtball.platform.getLogger().error("Achievement is no longer existed since 1.12");
        return null;
    }

    @Override
    public List<String> tabCompleteInternalStatisticOrAchievementName(String token, List<String> completions) {
        return Collections.emptyList();
    }

    @Override
    public Advancement loadAdvancement(NamespacedKey key, String advancement) {
        return null;
    }

    @Override
    public boolean removeAdvancement(NamespacedKey key) {
        return false;
    }
}
