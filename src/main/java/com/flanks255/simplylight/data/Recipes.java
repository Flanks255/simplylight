package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.BaseBlockItem;
import com.flanks255.simplylight.blocks.LampBlock;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
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
        ShapedRecipeBuilder.shaped(SLBlocks.ILLUMINANTBLOCK.getItem(), 4)
            .pattern("aba")
            .pattern("bcb")
            .pattern("aba")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .define('c', Tags.Items.DUSTS_REDSTONE)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block"));

        // Illuminant Block (On)
        ShapedRecipeBuilder.shaped(SLBlocks.ILLUMINANTBLOCK_ON.getItem(), 4)
            .pattern("aba")
            .pattern("bcb")
            .pattern("aba")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .define('c', Items.REDSTONE_TORCH)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on"));

        // Bulbs
        ShapedRecipeBuilder.shaped(SLBlocks.LIGHTBULB.getItem(), 8)
            .pattern(" b ")
            .pattern("aaa")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "bulb"));

        // Edge light
        ShapedRecipeBuilder.shaped(SLBlocks.EDGELAMP.getItem(), 6)
            .pattern("b b")
            .pattern("aaa")
            .pattern("b b")
            .define('a', Items.GLOWSTONE)
            .define('b', Tags.Items.STONE)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation( SimplyLight.MODID, "edge_light"));

        // Top Edge light from bottom
        ShapelessRecipeBuilder.shapeless(SLBlocks.EDGELAMP_TOP.getItem(), 1)
            .requires(SLBlocks.EDGELAMP.getItem())
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_top"));

        // Bottom Edge light from top
        ShapelessRecipeBuilder.shapeless(SLBlocks.EDGELAMP.getItem(), 1)
            .requires(SLBlocks.EDGELAMP_TOP.getItem())
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_bottom_from_top"));

        // Slabs
        ShapedRecipeBuilder.shaped(SLBlocks.ILLUMINANTSLAB.getItem(), 6)
            .pattern("bbb")
            .pattern("aaa")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab"));

        // Slab from panel
        ShapelessRecipeBuilder.shapeless(SLBlocks.ILLUMINANTSLAB.getItem())
            .requires(SLBlocks.ILLUMINANTPANEL.getItem())
            .requires(SLBlocks.ILLUMINANTPANEL.getItem())
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab_from_panel"));

        // Panels
        ShapedRecipeBuilder.shaped(SLBlocks.ILLUMINANTPANEL.getItem(), 6)
            .pattern("aaa")
            .define('a', SLBlocks.ILLUMINANTSLAB.getItem())
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_panel"));

        // Rod Lamp
        ShapedRecipeBuilder.shaped(SLBlocks.RODLAMP.getItem(), 8)
            .pattern("bab")
            .pattern("bab")
            .pattern("bab")
            .define('a', Tags.Items.STONE)
            .define('b', Tags.Items.DUSTS_GLOWSTONE)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "rodlamp"));

        // Wall Lamp
        ShapedRecipeBuilder.shaped(SLBlocks.WALL_LAMP.getItem(), 6)
            .pattern("aa")
            .pattern("ab")
            .pattern("ab")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "walllamp"));

        //Lamp Post
        ShapedRecipeBuilder.shaped(SLBlocks.LAMP_POST.getItem(), 2)
            .pattern(" L ")
            .pattern(" W ")
            .pattern("SSS")
            .define('L', SimplyLight.ANY_ON_LAMP)
            .define('W', ItemTags.WALLS)
            .define('S', Tags.Items.STONE)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "lamp_post"));


        SLBlocks.LAMPBLOCKS_ON.forEach( lamp -> {
            dyeRecipeOn(lamp.getItem(), DyeItem.byColor(lamp.getBlock().color), consumer);
            toggleOn(lamp, consumer);
        });
        SLBlocks.LAMPBLOCKS_OFF.forEach( lamp -> {
            dyeRecipeOff(lamp.getItem(), DyeItem.byColor(lamp.getBlock().color), consumer);
            toggleOff(lamp, consumer);
        });
    }

    private void toggleOn(SLBlockReg<LampBlock, BaseBlockItem> block, Consumer<FinishedRecipe> consumer) {
        Item item = null;
        for (SLBlockReg<LampBlock, BaseBlockItem> reg : SLBlocks.LAMPBLOCKS_OFF) {
            if (reg.getBlock().color == block.getBlock().color) {
                item = reg.getItem();
                break;
            }
        }
        if (item == null)
            return;

        ShapelessRecipeBuilder.shapeless(block.getItem())
            .requires(item)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, block.getItem().getRegistryName().getPath()+"_toggle"));
    }
    private void toggleOff(SLBlockReg<LampBlock, BaseBlockItem> block, Consumer<FinishedRecipe> consumer) {
        Item item = null;
        for (SLBlockReg<LampBlock, BaseBlockItem> reg : SLBlocks.LAMPBLOCKS_ON) {
            if (reg.getBlock().color == block.getBlock().color) {
                item = reg.getItem();
                break;
            }
        }
        if (item == null)
            return;

        ShapelessRecipeBuilder.shapeless(block.getItem())
            .requires(item)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, block.getItem().getRegistryName().getPath()+"_toggle"));
    }

    private void dyeRecipeOff(Item item, Item dyeItem, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(item, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('B', dyeItem)
            .define('A', SimplyLight.ANY_OFF_LAMP)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, item.getRegistryName().getPath()+"_dyed"));
    }
    private void dyeRecipeOn(Item item, Item dyeItem, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(item, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('B', dyeItem)
            .define('A', SimplyLight.ANY_ON_LAMP)
            .unlockedBy("", has(Items.AIR))
            .save(consumer, new ResourceLocation(SimplyLight.MODID, item.getRegistryName().getPath()+"_dyed"));
    }

    @Override
    protected void saveAdvancement(@Nonnull HashCache cache, @Nonnull JsonObject cache2, @Nonnull Path advancementJson) {
        // Nope, dont want none of this...
    }
}
