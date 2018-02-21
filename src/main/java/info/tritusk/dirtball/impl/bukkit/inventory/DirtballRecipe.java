package info.tritusk.dirtball.impl.bukkit.inventory;

import info.tritusk.dirtball.util.DirtballObjectsConverter;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.api.item.recipe.Recipe;

public class DirtballRecipe implements org.bukkit.inventory.Recipe {

    private final Recipe spongeRecipe;

    public DirtballRecipe(Recipe spongeRecipe) {
        this.spongeRecipe = spongeRecipe;
    }

    @Override
    public ItemStack getResult() {
        return null;
        //return DirtballObjectsConverter.fromSponge(spongeRecipe.getExemplaryResult());
    }
}
