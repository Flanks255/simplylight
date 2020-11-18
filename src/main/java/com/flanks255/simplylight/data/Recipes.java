package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLight;
import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.ImpossibleTrigger;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import java.nio.file.Path;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        // Illuminant Block (Off)
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ITEM_ILLUMINANTBLOCK.get(), 4)
                .patternLine("aba")
                .patternLine("bcb")
                .patternLine("aba")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .key('c', Tags.Items.DUSTS_REDSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block"));

        // Illuminant Block (On)
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ITEM_ILLUMINANTBLOCK_ON.get(), 4)
                .patternLine("aba")
                .patternLine("bcb")
                .patternLine("aba")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .key('c', Items.REDSTONE_TORCH)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on"));

        // Illuminant Block (On) from (Off)
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ITEM_ILLUMINANTBLOCK_ON.get(), 1)
                .addIngredient(SimplyLight.ITEM_ILLUMINANTBLOCK.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on_from_off"));

        // Illuminant Block (Off) from (On)
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ITEM_ILLUMINANTBLOCK.get(), 1)
                .addIngredient(SimplyLight.ITEM_ILLUMINANTBLOCK_ON.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_off_from_on"));

        // Bulbs
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ITEM_LIGHTBULB.get(), 8)
                .patternLine(" b ")
                .patternLine("aaa")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "bulb"));

        // Edge light
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ITEM_EDGELAMP.get(), 6)
                .patternLine("b b")
                .patternLine("aaa")
                .patternLine("b b")
                .key('a', Items.GLOWSTONE)
                .key('b', Tags.Items.STONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation( SimplyLight.MODID, "edge_light"));

        // Top Edge light from bottom
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ITEM_EDGELAMP_TOP.get(), 1)
                .addIngredient(SimplyLight.ITEM_EDGELAMP.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_top"));

        // Bottom Edge light from top
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ITEM_EDGELAMP.get(), 1)
                .addIngredient(SimplyLight.ITEM_EDGELAMP_TOP.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_bottom_from_top"));

        // Slabs
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ITEM_ILLUMINANTSLAB.get(), 6)
                .patternLine("bbb")
                .patternLine("aaa")
                .key('a', Tags.Items.STONE)
                .key('b', Items.GLOWSTONE)
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab"));

        // Slab from panel
        ShapelessRecipeBuilder.shapelessRecipe(SimplyLight.ITEM_ILLUMINANTSLAB.get())
                .addIngredient(SimplyLight.ITEM_ILLUMINANTPANEL.get())
                .addIngredient(SimplyLight.ITEM_ILLUMINANTPANEL.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab_from_panel"));

        // Panels
        ShapedRecipeBuilder.shapedRecipe(SimplyLight.ITEM_ILLUMINANTPANEL.get(), 6)
                .patternLine("aaa")
                .key('a', SimplyLight.ITEM_ILLUMINANTSLAB.get())
                .addCriterion("", hasItem(Items.AIR))
                .build(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_panel"));
    }

    @Override
    protected void saveRecipeAdvancement(DirectoryCache cache, JsonObject cache2, Path advancementJson) {
        // Nope, dont want none of this...
    }
}
