package com.flanks255.simplylight.util;

import com.google.gson.JsonElement;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

public class NoAdvRecipeOutput implements RecipeOutput {
    private final RecipeOutput inner;
    public NoAdvRecipeOutput(RecipeOutput output) {
        inner = output;
    }

    @NotNull
    @Override
    public Advancement.Builder advancement() {
        return inner.advancement();
    }

    @Override
    public void accept(@NotNull ResourceLocation resourceLocation, @NotNull Recipe<?> recipe, ResourceLocation advancementId, JsonElement advancement) {
        inner.accept(resourceLocation, recipe, advancementId, null);
    }

    @Override
    public HolderLookup.@NotNull Provider registry() {
        return inner.registry();
    }
}
