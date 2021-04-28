package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLight;
import com.google.gson.JsonObject;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        // Illuminant Block (Off)
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ILLUMINANTBLOCK.getItem(), 4)
                .patternLine("aba")
                .patternLine("bcb")
                .patternLine("aba")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .key('c', Tags.Items.DUSTS_REDSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block"));

        // Illuminant Block (On)
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ILLUMINANTBLOCK_ON.getItem(), 4)
                .patternLine("aba")
                .patternLine("bcb")
                .patternLine("aba")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .key('c', Items.REDSTONE_TORCH)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on"));

        // Illuminant Block (On) from (Off)
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ILLUMINANTBLOCK_ON.getItem(), 1)
                .addIngredient(SimplyLight.ILLUMINANTBLOCK.getItem())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on_from_off"));

        // Illuminant Block (Off) from (On)
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ILLUMINANTBLOCK.getItem(), 1)
                .addIngredient(SimplyLight.ILLUMINANTBLOCK_ON.getItem())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_off_from_on"));

        // Bulbs
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.LIGHTBULB.getItem(), 8)
                .patternLine(" b ")
                .patternLine("aaa")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "bulb"));

        // Edge light
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.EDGELAMP.getItem(), 6)
                .patternLine("b b")
                .patternLine("aaa")
                .patternLine("b b")
                .key('a', Items.GLOWSTONE)
                .key('b', Tags.Items.STONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation( SimplyLight.MODID, "edge_light"));

        // Top Edge light from bottom
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.EDGELAMP_TOP.getItem(), 1)
                .addIngredient(SimplyLight.EDGELAMP.getItem())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_top"));

        // Bottom Edge light from top
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.EDGELAMP.getItem(), 1)
                .addIngredient(SimplyLight.EDGELAMP_TOP.getItem())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_bottom_from_top"));

        // Slabs
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ILLUMINANTSLAB.getItem(), 6)
                .patternLine("bbb")
                .patternLine("aaa")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab"));

        // Slab from panel
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ILLUMINANTSLAB.getItem())
                .addIngredient(SimplyLight.ILLUMINANTPANEL.getItem())
                .addIngredient(SimplyLight.ILLUMINANTPANEL.getItem())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab_from_panel"));

        // Panels
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ILLUMINANTPANEL.getItem(), 6)
                .patternLine("aaa")
                .key('a', SimplyLight.ILLUMINANTSLAB.getItem())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_panel"));

        // Rod Lamp
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.RODLAMP.getItem(), 8)
                .patternLine("bab")
                .patternLine("bab")
                .patternLine("bab")
                .key('a', Tags.Items.STONE)
                .key('b', Tags.Items.DUSTS_GLOWSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "rodlamp"));

        // Wall Lamp
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.WALL_LAMP.getItem(), 6)
                .patternLine("aa")
                .patternLine("ab")
                .patternLine("ab")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "walllamp"));
    }

    @Override
    protected void saveRecipeAdvancement(@Nonnull DirectoryCache cache, @Nonnull JsonObject cache2, @Nonnull Path advancementJson) {
        // Nope, dont want none of this...
    }
}
