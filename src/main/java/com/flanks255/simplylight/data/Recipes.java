package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.BaseBlockItem;
import com.flanks255.simplylight.blocks.LampBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Recipes extends FabricRecipeProvider {
    public Recipes(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> thingIDontUse) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Illuminant Block (Off)
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTBLOCK.getItem(), 4)
            .pattern("aba")
            .pattern("bcb")
            .pattern("aba")
            .define('a', SimplyLight.ANY_STONE)
            .define('b', Items.GLOWSTONE)
            .define('c', Items.REDSTONE)
            .save(consumer, SL_loc("illuminant_block"));

        // Illuminant Block (On)
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTBLOCK_ON.getItem(), 4)
            .pattern("aba")
            .pattern("bcb")
            .pattern("aba")
            .define('a', SimplyLight.ANY_STONE)
            .define('b', Items.GLOWSTONE)
            .define('c', Items.REDSTONE_TORCH)
            .save(consumer, SL_loc("illuminant_block_on"));

        // Bulbs
        ShapedBuilder.shaped(SLBlocks.LIGHTBULB.getItem(), 8)
            .pattern(" b ")
            .pattern("aaa")
            .define('a', SimplyLight.ANY_STONE)
            .define('b', Items.GLOWSTONE)
            .save(consumer, SL_loc("bulb"));

        // Edge light
        ShapedBuilder.shaped(SLBlocks.EDGELAMP.getItem(), 6)
            .pattern("b b")
            .pattern("aaa")
            .pattern("b b")
            .define('a', Items.GLOWSTONE)
            .define('b', SimplyLight.ANY_STONE)
            .save(consumer, SL_loc("edge_light"));

        // Top Edge light from bottom
        ShapelessBuilder.shapeless(SLBlocks.EDGELAMP_TOP.getItem())
            .requires(SLBlocks.EDGELAMP.getItem())
            .save(consumer, SL_loc("edge_light_top"));

        // Bottom Edge light from top
        ShapelessBuilder.shapeless(SLBlocks.EDGELAMP.getItem())
            .requires(SLBlocks.EDGELAMP_TOP.getItem())
            .save(consumer, SL_loc("edge_light_bottom_from_top"));

        // Slabs
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTSLAB.getItem(), 6)
            .pattern("bbb")
            .pattern("aaa")
            .define('a', SimplyLight.ANY_STONE)
            .define('b', Items.GLOWSTONE)
            .save(consumer, SL_loc("illuminant_slab"));

        // Slab from panel
        ShapelessBuilder.shapeless(SLBlocks.ILLUMINANTSLAB.getItem())
            .requires(SLBlocks.ILLUMINANTPANEL.getItem())
            .requires(SLBlocks.ILLUMINANTPANEL.getItem())
            .save(consumer, SL_loc("illuminant_slab_from_panel"));

        // Panels
        ShapedBuilder.shaped(SLBlocks.ILLUMINANTPANEL.getItem(), 6)
            .pattern("aaa")
            .define('a', SLBlocks.ILLUMINANTSLAB.getItem())
            .save(consumer, SL_loc("illuminant_panel"));

        // Rod Lamp
        ShapedBuilder.shaped(SLBlocks.RODLAMP.getItem(), 8)
            .pattern("bab")
            .pattern("bab")
            .pattern("bab")
            .define('a', SimplyLight.ANY_STONE)
            .define('b', Items.GLOWSTONE_DUST)
            .save(consumer, SL_loc("rodlamp"));

        // Wall Lamp
        ShapedBuilder.shaped(SLBlocks.WALL_LAMP.getItem(), 6)
            .pattern("aa")
            .pattern("ab")
            .pattern("ab")
            .define('a', SimplyLight.ANY_STONE)
            .define('b', Items.GLOWSTONE)
            .save(consumer, SL_loc("walllamp"));

        //Lamp Post
        ShapedBuilder.shaped(SLBlocks.LAMP_POST.getItem(), 2)
            .pattern(" L ")
            .pattern(" W ")
            .pattern("SSS")
            .define('L', SimplyLight.ANY_ON_LAMP)
            .define('W', ItemTags.WALLS)
            .define('S', SimplyLight.ANY_STONE)
            .save(consumer, SL_loc("lamp_post"));


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

        ShapelessBuilder.shapeless(block.getItem())
            .requires(item)
            .save(consumer, SL_loc(block.getItem().getRegistryName().getPath()+"_toggle"));
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

        ShapelessBuilder.shapeless(block.getItem())
            .requires(item)
            .save(consumer, SL_loc(block.getItem().getRegistryName().getPath()+"_toggle"));
    }

    private void dyeRecipeOff(BaseBlockItem item, Item dyeItem, Consumer<FinishedRecipe> consumer) {
        ShapedBuilder.shaped(item, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('B', dyeItem)
            .define('A', SimplyLight.ANY_OFF_LAMP)
            .save(consumer, SL_loc(item.getRegistryName().getPath()+"_dyed"));
    }
    private void dyeRecipeOn(BaseBlockItem item, Item dyeItem, Consumer<FinishedRecipe> consumer) {
        ShapedBuilder.shaped(item, 8)
            .pattern("AAA")
            .pattern("ABA")
            .pattern("AAA")
            .define('B', dyeItem)
            .define('A', SimplyLight.ANY_ON_LAMP)
            .save(consumer, SL_loc(item.getRegistryName().getPath()+"_dyed"));
    }


    private ResourceLocation SL_loc(String name) {
        return new ResourceLocation(SimplyLight.MODID, name);
    }

    private static class ShapedBuilder extends ShapedRecipeBuilder {
        public static final InventoryChangeTrigger.TriggerInstance TRIGGER = has(Items.AIR);

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
        public void save(@Nonnull Consumer<FinishedRecipe> pFinishedRecipeConsumer, @Nonnull ResourceLocation pRecipeId) {
            unlockedBy("", TRIGGER); //Nope
            super.save(NoAdvFR.Inject(pFinishedRecipeConsumer), pRecipeId);
        }

        @Override
        public void save(@Nonnull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
            unlockedBy("", TRIGGER); //Nope
            super.save(NoAdvFR.Inject(pFinishedRecipeConsumer));
        }
    }

    private static class ShapelessBuilder extends ShapelessRecipeBuilder {
        public static final InventoryChangeTrigger.TriggerInstance TRIGGER = has(Items.AIR);

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
        public void save(@Nonnull Consumer<FinishedRecipe> pFinishedRecipeConsumer, @Nonnull ResourceLocation pRecipeId) {
            unlockedBy("", TRIGGER); //Nope
            super.save(NoAdvFR.Inject(pFinishedRecipeConsumer), pRecipeId);
        }

        @Override
        public void save(@Nonnull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
            unlockedBy("", TRIGGER); //Nope
            super.save(NoAdvFR.Inject(pFinishedRecipeConsumer));
        }
    }
}
