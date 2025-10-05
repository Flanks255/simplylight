package com.flanks255.simplylight.datagen;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLightCommon;
import com.flanks255.simplylight.blocks.BaseBlockItem;
import com.flanks255.simplylight.blocks.LampBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;


public class CommonRecipes {
    public static void buildRecipes(@NotNull RecipeOutput output, @NotNull TagKey<Item> stoneTag, @NotNull TagKey<Item> glowstoneTag, @NotNull TagKey<Item> redstoneTag) {
        // Illuminant Block (Off)
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTBLOCK.getItem(), 4)
                .pattern("aba")
                .pattern("bcb")
                .pattern("aba")
                .define('a', stoneTag)
                .define('b', Items.GLOWSTONE)
                .define('c', redstoneTag)
                .save(output, SimplyLightCommon.SLRes("illuminant_block"));

        // Illuminant Block (On)
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTBLOCK_ON.getItem(), 4)
                .pattern("aba")
                .pattern("bcb")
                .pattern("aba")
                .define('a', stoneTag)
                .define('b', Items.GLOWSTONE)
                .define('c', Items.REDSTONE_TORCH)
                .save(output, SimplyLightCommon.SLRes("illuminant_block_on"));

        // Bulbs
        ShapedBuilder.shaped(SLBlocks.LIGHTBULB.getItem(), 8)
                .pattern(" b ")
                .pattern("aaa")
                .define('a', stoneTag)
                .define('b', Items.GLOWSTONE)
                .save(output, SimplyLightCommon.SLRes("bulb"));

        // Edge light
        ShapedBuilder.shaped(SLBlocks.EDGELAMP.getItem(), 6)
                .pattern("b b")
                .pattern("aaa")
                .pattern("b b")
                .define('a', Items.GLOWSTONE)
                .define('b', stoneTag)
                .save(output, SimplyLightCommon.SLRes("edge_light"));

        // Top Edge light from bottom
        ShapelessBuilder.shapeless(SLBlocks.EDGELAMP_TOP.getItem())
                .requires(SLBlocks.EDGELAMP.getItem())
                .save(output, SimplyLightCommon.SLRes("edge_light_top"));

        // Bottom Edge light from top
        ShapelessBuilder.shapeless(SLBlocks.EDGELAMP.getItem())
                .requires(SLBlocks.EDGELAMP_TOP.getItem())
                .save(output, SimplyLightCommon.SLRes("edge_light_bottom_from_top"));

        // Slabs
        ShapedBuilder.shaped(SLBlocks.ILLUMINANT_SLAB.getItem(), 6)
                .pattern("bbb")
                .pattern("aaa")
                .define('a', stoneTag)
                .define('b', Items.GLOWSTONE)
                .save(output, SimplyLightCommon.SLRes("illuminant_slab"));

        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL.getItem(), SLBlocks.ILLUMINANT_SLAB.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_ORANGE.getItem(), SLBlocks.ILLUMINANT_SLAB_ORANGE.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_MAGENTA.getItem(), SLBlocks.ILLUMINANT_SLAB_MAGENTA.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_LIGHT_BLUE.getItem(), SLBlocks.ILLUMINANT_SLAB_LIGHT_BLUE.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_YELLOW.getItem(), SLBlocks.ILLUMINANT_SLAB_YELLOW.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_LIME.getItem(), SLBlocks.ILLUMINANT_SLAB_LIME.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_PINK.getItem(), SLBlocks.ILLUMINANT_SLAB_PINK.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_GRAY.getItem(), SLBlocks.ILLUMINANT_SLAB_GRAY.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_LIGHT_GRAY.getItem(), SLBlocks.ILLUMINANT_SLAB_LIGHT_GRAY.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_CYAN.getItem(), SLBlocks.ILLUMINANT_SLAB_CYAN.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_PURPLE.getItem(), SLBlocks.ILLUMINANT_SLAB_PURPLE.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_BLUE.getItem(), SLBlocks.ILLUMINANT_SLAB_BLUE.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_BROWN.getItem(), SLBlocks.ILLUMINANT_SLAB_BROWN.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_GREEN.getItem(), SLBlocks.ILLUMINANT_SLAB_GREEN.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_BLACK.getItem(), SLBlocks.ILLUMINANT_SLAB_BLACK.getItem(), output);
        panelSlabRecipe(SLBlocks.ILLUMINANT_PANEL_RED.getItem(), SLBlocks.ILLUMINANT_SLAB_RED.getItem(), output);

        // Rod Lamp
        ShapedBuilder.shaped(SLBlocks.RODLAMP.getItem(), 8)
                .pattern("bab")
                .pattern("bab")
                .pattern("bab")
                .define('a', stoneTag)
                .define('b', glowstoneTag)
                .save(output, SimplyLightCommon.SLRes("rodlamp"));

        // Wall Lamp
        ShapedBuilder.shaped(SLBlocks.FIXTURE.getItem(), 6)
                .pattern("aa")
                .pattern("ab")
                .pattern("ab")
                .define('a', stoneTag)
                .define('b', Items.GLOWSTONE)
                .save(output, SimplyLightCommon.SLRes("walllamp"));

        //Lamp Post
        ShapedBuilder.shaped(SLBlocks.LAMP_POST.getItem(), 2)
                .pattern(" L ")
                .pattern(" W ")
                .pattern("SSS")
                .define('L', SimplyLightCommon.ANY_ON_LAMP)
                .define('W', ItemTags.WALLS)
                .define('S', stoneTag)
                .save(output, SimplyLightCommon.SLRes("lamp_post"));


        SLBlocks.LAMPBLOCKS_ON.forEach( lamp -> {
            dyeRecipe(lamp.getItem(), DyeItem.byColor(lamp.getBlock().color), SimplyLightCommon.ANY_ON_LAMP, output);
            toggleOn(lamp, output);
        });
        SLBlocks.LAMPBLOCKS_OFF.forEach( lamp -> {
            dyeRecipe(lamp.getItem(), DyeItem.byColor(lamp.getBlock().color), SimplyLightCommon.ANY_OFF_LAMP, output);
            toggleOff(lamp, output);
        });

        SLBlocks.SLABS.forEach( slab ->
                dyeRecipe(slab.getItem(), DyeItem.byColor(slab.getBlock().color), SimplyLightCommon.ANY_SLAB, output));

        SLBlocks.PANELS.forEach( panel ->
                dyeRecipe(panel.getItem(), DyeItem.byColor(panel.getBlock().color), SimplyLightCommon.ANY_PANEL, output));

        SLBlocks.RODS.forEach( rod ->
                dyeRecipe(rod.getItem(), DyeItem.byColor(rod.getBlock().color), SimplyLightCommon.ANY_ROD, output));

        SLBlocks.BULBS.forEach( bulb ->
                dyeRecipe(bulb.getItem(), DyeItem.byColor(bulb.getBlock().color), SimplyLightCommon.ANY_BULB, output));

        SLBlocks.FIXTURES.forEach(fixture ->
                dyeRecipe(fixture.getItem(), DyeItem.byColor(fixture.getBlock().color), SimplyLightCommon.ANY_FIXTURE, output));

        SLBlocks.POSTS.forEach(post ->
                dyeRecipe(post.getItem(), DyeItem.byColor(post.getBlock().color), SimplyLightCommon.ANY_POST, output));

        SLBlocks.EDGE_LIGHTS.forEach(edge ->
                dyeRecipe(edge.getItem(), DyeItem.byColor(edge.getBlock().color), SimplyLightCommon.ANY_EDGE_LIGHT, output));

        SLBlocks.EDGE_LIGHTS_TOP.forEach(edge ->
                dyeRecipe(edge.getItem(), DyeItem.byColor(edge.getBlock().color), SimplyLightCommon.ANY_EDGE_LIGHT_TOP, output));
    }

    private static void toggleOn(SLBlockReg<LampBlock, BaseBlockItem> block, RecipeOutput consumer) {
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
                .save(consumer, SimplyLightCommon.SLRes(block.getItem().getRegistryName().getPath()+"_toggle"));
    }
    private static void toggleOff(SLBlockReg<LampBlock, BaseBlockItem> block, RecipeOutput consumer) {
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
                .save(consumer, SimplyLightCommon.SLRes(block.getItem().getRegistryName().getPath()+"_toggle"));
    }

    private static void dyeRecipe(BaseBlockItem result, Item dyeItem, TagKey<Item> inputTag, RecipeOutput consumer) {
        ShapedBuilder.shaped(result, 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('B', dyeItem)
                .define('A', inputTag)
                .save(consumer, SimplyLightCommon.SLRes(result.getRegistryName().getPath() + "_dyed"));
    }

    private static void panelSlabRecipe(BaseBlockItem panel, BaseBlockItem slab, RecipeOutput consumer) {
        ShapedBuilder.shaped(panel, 6)
                .pattern("AAA")
                .define('A', slab)
                .save(consumer, SimplyLightCommon.SLRes(panel.getRegistryName().getPath() + "_split"));

        ShapelessBuilder.shapeless(slab)
                .requires(panel)
                .requires(panel)
                .save(consumer, SimplyLightCommon.SLRes(slab.getRegistryName().getPath() + "_combine"));
    }

    private static class ShapedBuilder extends ShapedRecipeBuilder {
        public static final Criterion<ImpossibleTrigger.TriggerInstance> TRIGGER = CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());
        public ShapedBuilder(ItemLike pResult, int pCount) {
            super(RecipeCategory.MISC, pResult, pCount);
        }

        public static ShapedBuilder shaped(ItemLike pResult, int pCount) {
            return new ShapedBuilder(pResult, pCount);
        }

        public static ShapedBuilder shaped(ItemLike pResult) {
            return new ShapedBuilder(pResult, 1);
        }

        @Override
        public void save(@Nonnull RecipeOutput pFinishedRecipeConsumer, @Nonnull ResourceLocation pRecipeId) {
            unlockedBy("", TRIGGER); //Nope
            showNotification(false);
            super.save(pFinishedRecipeConsumer, pRecipeId);
        }

        @Override
        public void save(@Nonnull RecipeOutput pFinishedRecipeConsumer) {
            unlockedBy("", TRIGGER); //Nope
            showNotification(false);
            super.save(pFinishedRecipeConsumer);
        }
    }

    private static class ShapelessBuilder extends ShapelessRecipeBuilder {
        public static final Criterion<ImpossibleTrigger.TriggerInstance> TRIGGER = CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());

        public ShapelessBuilder(ItemLike pResult, int pCount) {
            super(RecipeCategory.MISC, pResult, pCount);
        }

        public static ShapelessBuilder shapeless(ItemLike pResult, int pCount) {
            return new ShapelessBuilder(pResult, pCount);
        }

        public static ShapelessBuilder shapeless(ItemLike pResult) {
            return new ShapelessBuilder(pResult, 1);
        }

        @Override
        public void save(@Nonnull RecipeOutput output, @Nonnull ResourceLocation pRecipeId) {
            unlockedBy("", TRIGGER); //Nope
            super.save(output, pRecipeId);
        }

        @Override
        public void save(@Nonnull RecipeOutput output) {
            unlockedBy("", TRIGGER); //Nope
            super.save(output);
        }
    }
}
