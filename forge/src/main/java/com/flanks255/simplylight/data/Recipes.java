package com.flanks255.simplylight.data;

import com.flanks255.simplylight.datagen.CommonRecipes;
import com.flanks255.simplylight.util.NoAdvRecipeOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn, CompletableFuture<HolderLookup.Provider> thingIDontUse) {
        super(generatorIn.getPackOutput(), thingIDontUse);
    }

    @Override
    protected void buildRecipes(@Nonnull RecipeOutput theirOutput) {
        NoAdvRecipeOutput output = new NoAdvRecipeOutput(theirOutput);

        CommonRecipes.buildRecipes(output, Tags.Items.STONES, Tags.Items.DUSTS_GLOWSTONE, Tags.Items.DUSTS_REDSTONE);
    }
}
