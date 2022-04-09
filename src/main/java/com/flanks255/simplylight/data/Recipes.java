package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.BaseBlockItem;
import com.flanks255.simplylight.blocks.LampBlock;
import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
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
    protected void buildShapelessRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        // Illuminant Block (Off)
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTBLOCK.getItem(), 4)
            .pattern("aba")
            .pattern("bcb")
            .pattern("aba")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .define('c', Tags.Items.DUSTS_REDSTONE)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block"));

        // Illuminant Block (On)
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTBLOCK_ON.getItem(), 4)
            .pattern("aba")
            .pattern("bcb")
            .pattern("aba")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .define('c', Items.REDSTONE_TORCH)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_block_on"));

        // Bulbs
        ShapedBuilder.shaped(SLBlocks.LIGHTBULB.getItem(), 8)
            .pattern(" b ")
            .pattern("aaa")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "bulb"));

        // Edge light
        ShapedBuilder.shaped(SLBlocks.EDGELAMP.getItem(), 6)
            .pattern("b b")
            .pattern("aaa")
            .pattern("b b")
            .define('a', Items.GLOWSTONE)
            .define('b', Tags.Items.STONE)
            .save(consumer, new ResourceLocation( SimplyLight.MODID, "edge_light"));

        // Top Edge light from bottom
        ShapelessBuilder.shapeless(SLBlocks.EDGELAMP_TOP.getItem(), 1)
            .requires(SLBlocks.EDGELAMP.getItem())
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_top"));

        // Bottom Edge light from top
        ShapelessBuilder.shapeless(SLBlocks.EDGELAMP.getItem(), 1)
            .requires(SLBlocks.EDGELAMP_TOP.getItem())
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "edge_light_bottom_from_top"));

        // Slabs
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTSLAB.getItem(), 6)
            .pattern("bbb")
            .pattern("aaa")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab"));

        // Slab from panel
        ShapelessBuilder.shapeless(SLBlocks.ILLUMINANTSLAB.getItem())
            .requires(SLBlocks.ILLUMINANTPANEL.getItem())
            .requires(SLBlocks.ILLUMINANTPANEL.getItem())
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_slab_from_panel"));

        // Panels
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTPANEL.getItem(), 6)
            .pattern("aaa")
            .define('a', SLBlocks.ILLUMINANTSLAB.getItem())
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "illuminant_panel"));

        // Rod Lamp
        ShapedBuilder.shaped(SLBlocks.RODLAMP.getItem(), 8)
            .pattern("bab")
            .pattern("bab")
            .pattern("bab")
            .define('a', Tags.Items.STONE)
            .define('b', Tags.Items.DUSTS_GLOWSTONE)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "rodlamp"));

        // Wall Lamp
        ShapedBuilder.shaped(SLBlocks.WALL_LAMP.getItem(), 6)
            .pattern("aa")
            .pattern("ab")
            .pattern("ab")
            .define('a', Tags.Items.STONE)
            .define('b', Items.GLOWSTONE)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, "walllamp"));

        //Lamp Post
        ShapedBuilder.shaped(SLBlocks.LAMP_POST.getItem(), 2)
            .pattern(" L ")
            .pattern(" W ")
            .pattern("SSS")
            .define('L', SimplyLight.ANY_ON_LAMP)
            .define('W', ItemTags.WALLS)
            .define('S', Tags.Items.STONE)
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

    private void toggleOn(SLBlockReg<LampBlock, BaseBlockItem> block, Consumer<IFinishedRecipe> consumer) {
        Item item = null;
        for (SLBlockReg<LampBlock, BaseBlockItem> reg : SLBlocks.LAMPBLOCKS_OFF) {
            if (reg.getBlock().color == block.getBlock().color) {
                item = reg.getItem();
                break;
            }
        }
        if (item == null)
            return;

        ShapelessBuilder.shapeless(block.getItem())
            .requires(item)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, block.getItem().getRegistryName().getPath()+"_toggle"));
    }
    private void toggleOff(SLBlockReg<LampBlock, BaseBlockItem> block, Consumer<IFinishedRecipe> consumer) {
        Item item = null;
        for (SLBlockReg<LampBlock, BaseBlockItem> reg : SLBlocks.LAMPBLOCKS_ON) {
            if (reg.getBlock().color == block.getBlock().color) {
                item = reg.getItem();
                break;
            }
        }
        if (item == null)
            return;

        ShapelessBuilder.shapeless(block.getItem())
            .requires(item)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, block.getItem().getRegistryName().getPath()+"_toggle"));
    }

    private void dyeRecipeOff(Item item, Item dyeItem, Consumer<IFinishedRecipe> consumer) {
        ShapedBuilder.shaped(item, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('B', dyeItem)
            .define('A', SimplyLight.ANY_OFF_LAMP)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, item.getRegistryName().getPath()+"_dyed"));
    }
    private void dyeRecipeOn(Item item, Item dyeItem, Consumer<IFinishedRecipe> consumer) {
        ShapedBuilder.shaped(item, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('B', dyeItem)
            .define('A', SimplyLight.ANY_ON_LAMP)
            .save(consumer, new ResourceLocation(SimplyLight.MODID, item.getRegistryName().getPath()+"_dyed"));
    }

    @Override
    protected void saveAdvancement(@Nonnull DirectoryCache cache, @Nonnull JsonObject cache2, @Nonnull Path advancementJson) {
        // Nope, dont want none of this...
    }


    private static class ShapedBuilder extends ShapedRecipeBuilder {
        public static final InventoryChangeTrigger.Instance TRIGGER = has(Items.AIR);

        public ShapedBuilder(IItemProvider pResult, int pCount) {
            super(pResult, pCount);
        }

        public static ShapedBuilder shaped(IItemProvider pResult, int pCount) {
            return new ShapedBuilder(pResult, pCount);
        }

        public static ShapedBuilder shaped(IItemProvider pResult) {
            return new ShapedBuilder(pResult, 1);
        }

        @Override
        public void save(@Nonnull Consumer<IFinishedRecipe> pFinishedRecipeConsumer, @Nonnull ResourceLocation pRecipeId) {
            unlockedBy("", TRIGGER); //Nope
            super.save(pFinishedRecipeConsumer, pRecipeId);
        }

        @Override
        public void save(@Nonnull Consumer<IFinishedRecipe> pFinishedRecipeConsumer) {
            unlockedBy("", TRIGGER); //Nope
            super.save(pFinishedRecipeConsumer);
        }
    }

    private static class ShapelessBuilder extends ShapelessRecipeBuilder {
        public static final InventoryChangeTrigger.Instance TRIGGER = has(Items.AIR);

        public ShapelessBuilder(IItemProvider pResult, int pCount) {
            super(pResult, pCount);
        }

        public static ShapelessBuilder shapeless(IItemProvider pResult, int pCount) {
            return new ShapelessBuilder(pResult, pCount);
        }

        public static ShapelessBuilder shapeless(IItemProvider pResult) {
            return new ShapelessBuilder(pResult, 1);
        }

        @Override
        public void save(@Nonnull Consumer<IFinishedRecipe> pFinishedRecipeConsumer, @Nonnull ResourceLocation pRecipeId) {
            unlockedBy("", TRIGGER); //Nope
            super.save(pFinishedRecipeConsumer, pRecipeId);
        }

        @Override
        public void save(@Nonnull Consumer<IFinishedRecipe> pFinishedRecipeConsumer) {
            unlockedBy("", TRIGGER); //Nope
            super.save(pFinishedRecipeConsumer);
        }
    }
}
