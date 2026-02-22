package com.flanks255.simplylight.util;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

public class NoAdvRecipeOutput implements RecipeOutput {
    private final RecipeOutput inner;
    public NoAdvRecipeOutput(RecipeOutput output) {
        inner = output;
    }

    
    @Override
    public Advancement.@NotNull Builder advancement() {
        return inner.advancement();
    }

    @Override
    public void accept( ResourceLocation resourceLocation,  Recipe<?> recipe,  AdvancementHolder advancementHolder) {
        inner.accept(resourceLocation, recipe, null);
    }
}
