package com.flanks255.simplylight.datagen;

import com.flanks255.simplylight.util.NoAdvRecipeOutput;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;


import java.util.concurrent.CompletableFuture;

public class SLRecipes extends FabricRecipeProvider {
    public SLRecipes(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput theirOutput) {
        NoAdvRecipeOutput output = new NoAdvRecipeOutput(theirOutput);

        CommonRecipes.buildRecipes(output, ConventionalItemTags.STONES, ConventionalItemTags.GLOWSTONE_DUSTS, ConventionalItemTags.REDSTONE_DUSTS);
    }
}
