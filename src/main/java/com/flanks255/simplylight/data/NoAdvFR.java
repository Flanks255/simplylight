package com.flanks255.simplylight.data;

import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class NoAdvFR implements FinishedRecipe {
    private final FinishedRecipe inner;

    public NoAdvFR(FinishedRecipe inner) {
        this.inner = inner;
    }

    public static Consumer<FinishedRecipe> Inject(Consumer<FinishedRecipe> consumer) {
        return iFinishedRecipe -> consumer.accept(new NoAdvFR(iFinishedRecipe));
    }

    @Override
    public void serializeRecipeData(JsonObject json) {
        inner.serializeRecipeData(json);
    }

    @Override
    public JsonObject serializeRecipe() {
        return inner.serializeRecipe();
    }

    @Override
    public ResourceLocation getId() {
        return inner.getId();
    }

    @Override
    public RecipeSerializer<?> getType() {
        return inner.getType();
    }

    @Nullable
    @Override
    public JsonObject serializeAdvancement() {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getAdvancementId() {
        return null;
    }
}
