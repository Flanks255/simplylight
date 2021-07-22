package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLight;
import com.google.gson.JsonObject;
import net.minecraft.data.*;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {
        // Illuminant Block (Off)
        ShapedRecipeBuilder.shaped(SimplyLight.ILLUMINANTBLOCK.getItem(), 4)
                .pattern("aba")
                .pattern("bcb")
                .pattern("aba")
                .define('a', Tags.Items.STONE)
                .define('b', Items.GLOWSTONE)
                .define('c', Tags.Items.DUSTS_REDSTONE)
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block"));

        // Illuminant Block (On)
        ShapedRecipeBuilder.shaped(SimplyLight.ILLUMINANTBLOCK_ON.getItem(), 4)
                .pattern("aba")
                .pattern("bcb")
                .pattern("aba")
                .define('a', Tags.Items.STONE)
                .define('b', Items.GLOWSTONE)
                .define('c', Items.REDSTONE_TORCH)
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on"));

        // Illuminant Block (On) from (Off)
        ShapelessRecipeBuilder.shapeless(SimplyLight.ILLUMINANTBLOCK_ON.getItem(), 1)
                .requires(SimplyLight.ILLUMINANTBLOCK.getItem())
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on_from_off"));

        // Illuminant Block (Off) from (On)
        ShapelessRecipeBuilder.shapeless(SimplyLight.ILLUMINANTBLOCK.getItem(), 1)
                .requires(SimplyLight.ILLUMINANTBLOCK_ON.getItem())
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_off_from_on"));

        // Bulbs
        ShapedRecipeBuilder.shaped(SimplyLight.LIGHTBULB.getItem(), 8)
                .pattern(" b ")
                .pattern("aaa")
                .define('a', Tags.Items.STONE)
                .define('b', Items.GLOWSTONE)
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "bulb"));

        // Edge light
        ShapedRecipeBuilder.shaped(SimplyLight.EDGELAMP.getItem(), 6)
                .pattern("b b")
                .pattern("aaa")
                .pattern("b b")
                .define('a', Items.GLOWSTONE)
                .define('b', Tags.Items.STONE)
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation( SimplyLight.MODID, "edge_light"));

        // Top Edge light from bottom
        ShapelessRecipeBuilder.shapeless(SimplyLight.EDGELAMP_TOP.getItem(), 1)
                .requires(SimplyLight.EDGELAMP.getItem())
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_top"));

        // Bottom Edge light from top
        ShapelessRecipeBuilder.shapeless(SimplyLight.EDGELAMP.getItem(), 1)
                .requires(SimplyLight.EDGELAMP_TOP.getItem())
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_bottom_from_top"));

        // Slabs
        ShapedRecipeBuilder.shaped(SimplyLight.ILLUMINANTSLAB.getItem(), 6)
                .pattern("bbb")
                .pattern("aaa")
                .define('a', Tags.Items.STONE)
                .define('b', Items.GLOWSTONE)
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab"));

        // Slab from panel
        ShapelessRecipeBuilder.shapeless(SimplyLight.ILLUMINANTSLAB.getItem())
                .requires(SimplyLight.ILLUMINANTPANEL.getItem())
                .requires(SimplyLight.ILLUMINANTPANEL.getItem())
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab_from_panel"));

        // Panels
        ShapedRecipeBuilder.shaped(SimplyLight.ILLUMINANTPANEL.getItem(), 6)
                .pattern("aaa")
                .define('a', SimplyLight.ILLUMINANTSLAB.getItem())
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_panel"));

        // Rod Lamp
        ShapedRecipeBuilder.shaped(SimplyLight.RODLAMP.getItem(), 8)
                .pattern("bab")
                .pattern("bab")
                .pattern("bab")
                .define('a', Tags.Items.STONE)
                .define('b', Tags.Items.DUSTS_GLOWSTONE)
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "rodlamp"));

        // Wall Lamp
        ShapedRecipeBuilder.shaped(SimplyLight.WALL_LAMP.getItem(), 6)
                .pattern("aa")
                .pattern("ab")
                .pattern("ab")
                .define('a', Tags.Items.STONE)
                .define('b', Items.GLOWSTONE)
                .unlockedBy("", has(Items.AIR))
                .save(consumer, new ResourceLocation(SimplyLight.MODID, "walllamp"));
    }

    @Override
    protected void saveAdvancement(@Nonnull HashCache cache, @Nonnull JsonObject cache2, @Nonnull Path advancementJson) {
        // Nope, dont want none of this...
    }
}
