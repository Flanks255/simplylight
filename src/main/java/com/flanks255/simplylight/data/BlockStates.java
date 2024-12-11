package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.*;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates  extends BlockStateProvider {
    ExistingFileHelper existingFileHelper;
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen.getPackOutput(), SimplyLight.MODID, exFileHelper);
        existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        generateLampBlock();

        generateColorModels();

        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_RED_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_RED);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_GREEN_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_GREEN);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_BLUE_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_BLUE);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_YELLOW_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_YELLOW);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_ORANGE_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_ORANGE);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_PINK_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_PINK);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_LIME_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_LIME);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_CYAN_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_CYAN);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_PURPLE_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_PURPLE);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_MAGENTA_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_MAGENTA);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_LIGHT_GRAY_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_LIGHT_GRAY);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_GRAY_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_GRAY);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_LIGHT_BLUE_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_LIGHT_BLUE);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_BROWN_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_BROWN);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_BLACK_ON);
        generateLampBlock(SLBlocks.ILLUMINANT_BLOCK_BLACK);


        generateThinLamps();

        generateRodLamps();

        generateBulbs();

        generateWallLamp();

        generateLampPost();
    }

    private void generateLightBulb(SLBlockReg<LightBulb, BaseBlockItem> block) {
        boolean isWhite = block.getBlock().color == DyeColor.WHITE;
        ModelFile base;
        ModelFile glow;
        if (isWhite) {
            base = models().getExistingFile(modLoc("block/lightbulb_base"));
            glow = models().getExistingFile(modLoc("block/lightbulb_glow"));
        }
        else {
            base = models().getExistingFile(modLoc("block/lightbulb_base_" + block.getBlock().color.getName()));
            glow = models().getExistingFile(modLoc("block/lightbulb_glow_" + block.getBlock().color.getName()));
        }

        var model = models().getBuilder(isWhite? "block/lightbulb" : "block/lightbulb_" + block.getBlock().color.getName()).customLoader(CompositeModelBuilder::begin)
                .child("Solid", models().nested().renderType("minecraft:solid").parent(base))
                .child("Translucent", models().nested().renderType("minecraft:translucent").parent(glow))
                .end();

        myDirectionalBlock(block.get(), $ -> model, 180);
    }

    private void generateBulbs() {
        generateLightBulb(SLBlocks.LIGHTBULB);
        generateLightBulb(SLBlocks.LIGHTBULB_RED);
        generateLightBulb(SLBlocks.LIGHTBULB_LIME);
        generateLightBulb(SLBlocks.LIGHTBULB_LIGHT_BLUE);
        generateLightBulb(SLBlocks.LIGHTBULB_MAGENTA);
        generateLightBulb(SLBlocks.LIGHTBULB_YELLOW);
        generateLightBulb(SLBlocks.LIGHTBULB_ORANGE);
        generateLightBulb(SLBlocks.LIGHTBULB_LIGHT_GRAY);
    }

    private void generateRodLamps() {
        generateRodLamp(SLBlocks.RODLAMP);
        generateRodLamp(SLBlocks.RODLAMP_RED);
        generateRodLamp(SLBlocks.RODLAMP_LIME);
        generateRodLamp(SLBlocks.RODLAMP_LIGHT_BLUE);
        generateRodLamp(SLBlocks.RODLAMP_MAGENTA);
        generateRodLamp(SLBlocks.RODLAMP_YELLOW);
        generateRodLamp(SLBlocks.RODLAMP_ORANGE);
        generateRodLamp(SLBlocks.RODLAMP_LIGHT_GRAY);
    }
/*
    private void generateEdgeBlocks() {
        ModelFile model = models().getExistingFile(modLoc("block/edge_light"));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(SimplyLight.EDGELAMP.get());

        builder.part().modelFile(model).addModel().useOr()
                .condition(EdgeLight.NORTH, true);


    }
*/

    private void generateLampPost() {
        ModelFile base = models().getExistingFile(modLoc("block/post_base"));
        ModelFile mid = models().getExistingFile(modLoc("block/post_mid"));
        ModelFile top = models().getExistingFile(modLoc("block/post_top"));

        getVariantBuilder(SLBlocks.LAMP_POST.get())
            .partialState().with(LampPost.POSITION, LampPost.Position.BOTTOM).modelForState()
            .modelFile(base).addModel()
            .partialState().with(LampPost.POSITION, LampPost.Position.MIDDLE).modelForState()
            .modelFile(mid).addModel()
            .partialState().with(LampPost.POSITION, LampPost.Position.TOP).modelForState()
            .modelFile(top).addModel();
    }
    private void generateWallLamp() {
        ModelFile wallModel = models().getExistingFile(modLoc("block/wall_lamp"));
        ModelFile floorModel = models().getExistingFile(modLoc("block/floorlamp"));
        getVariantBuilder(SLBlocks.WALL_LAMP.get())
            .partialState().with(BlockStateProperties.FACING, Direction.UP)
            .modelForState().modelFile(floorModel).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.DOWN)
            .modelForState().modelFile(floorModel).rotationX(180).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.EAST)
            .modelForState().modelFile(wallModel).rotationY(270).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.WEST)
            .modelForState().modelFile(wallModel).rotationY(90).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.SOUTH)
            .modelForState().modelFile(wallModel).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.NORTH)
            .modelForState().modelFile(wallModel).rotationY(180).addModel();
    }

    private void generateThinLamps() {
        myDirectionalBlock(SLBlocks.ILLUMINANT_SLAB.get(), $ -> models().getExistingFile(modLoc("block/illuminant_slab")), 180);

        generateSlab(SLBlocks.ILLUMINANT_SLAB_RED);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_GREEN);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_BLUE);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_YELLOW);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_ORANGE);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_PINK);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_LIME);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_CYAN);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_PURPLE);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_MAGENTA);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_LIGHT_GRAY);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_GRAY);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_LIGHT_BLUE);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_BROWN);
        generateSlab(SLBlocks.ILLUMINANT_SLAB_BLACK);

        myDirectionalBlock(SLBlocks.ILLUMINANT_PANEL.get(), $ -> models().getExistingFile(modLoc("block/illuminant_panel")), 180);

        generatePanel(SLBlocks.ILLUMINANT_PANEL_RED);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_GREEN);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_BLUE);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_YELLOW);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_ORANGE);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_PINK);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_LIME);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_CYAN);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_PURPLE);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_MAGENTA);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_LIGHT_GRAY);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_GRAY);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_LIGHT_BLUE);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_BROWN);
        generatePanel(SLBlocks.ILLUMINANT_PANEL_BLACK);
    }

    private void generateSlab(SLBlockReg<ThinLamp, BaseBlockItem> block) {
        myDirectionalBlock(block.get(), $ -> models().getExistingFile(modLoc("block/illuminant_slab_"+ block.getBlock().color.getName())), 180);
    }

    private void generatePanel(SLBlockReg<ThinLamp, BaseBlockItem> block) {
        myDirectionalBlock(block.get(), $ -> models().getExistingFile(modLoc("block/illuminant_panel_"+ block.getBlock().color.getName())), 180);
    }
    void generateRodLamp(SLBlockReg<RodLamp, BaseBlockItem> block) {
        ModelFile model;
        if (block.getBlock().color == DyeColor.WHITE)
            model = models().getExistingFile(modLoc("block/rodlamp"));
        else
            model = models().getExistingFile(modLoc("block/rodlamp_" + block.getBlock().color.getName()));

        getVariantBuilder(block.getBlock())
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel();
    }

    private void generateLampBlock() {
        ResourceLocation offTex = modLoc("block/full_block/illuminant_block");
        ResourceLocation onTex = modLoc("block/full_block/illuminant_block_on");

        var illuminantBlockBuilder = models().withExistingParent(SLBlocks.ILLUMINANTBLOCK.get().getRegistryName().getPath(), "cube")
            .texture("all", offTex).texture("particle", offTex).ao(false).element();
        ModelFile modelIlluminantBlock = illuminantBlockBuilder.cube("#all").shade(false).end();

        var illuminantBlockBuilder_On = models().withExistingParent(SLBlocks.ILLUMINANTBLOCK_ON.get().getRegistryName().getPath(), "cube")
            .texture("all", onTex).texture("particle", onTex).ao(false).element();
        ModelFile modelIlluminantBlock_on = illuminantBlockBuilder_On.cube("#all").shade(false).end();

        //Build non-inverted
        var LampBlockBuilder = getVariantBuilder(SLBlocks.ILLUMINANTBLOCK.get());
        LampBlockBuilder.partialState().with(LampBlock.ON, true).modelForState().modelFile(modelIlluminantBlock_on).addModel();
        LampBlockBuilder.partialState().with(LampBlock.ON, false).modelForState().modelFile(modelIlluminantBlock).addModel();

        //Build inverted
        var LampBlockOnBuilder = getVariantBuilder(SLBlocks.ILLUMINANTBLOCK_ON.get());
        LampBlockOnBuilder.partialState().with(LampBlock.ON, true).modelForState().modelFile(modelIlluminantBlock_on).addModel();
        LampBlockOnBuilder.partialState().with(LampBlock.ON, false).modelForState().modelFile(modelIlluminantBlock).addModel();
    }

    private void generateColorModels() {
        for (DyeColor color: DyeColor.values()) { // Full blocks
            if (color == DyeColor.WHITE) continue;

            ResourceLocation tex = modLoc("block/full_block/illuminant_"+ color.getName() +"_block");
            ResourceLocation tex_on = modLoc("block/full_block/illuminant_"+ color.getName() +"_block_on");

            models().withExistingParent("block/illuminant_" + color.getName() + "_block", modLoc("block/illuminant_block")).texture("all", tex).texture("particle", tex);
            models().withExistingParent("block/illuminant_" + color.getName() + "_block_on", modLoc("block/illuminant_block")).texture("all", tex_on).texture("particle", tex_on);
        }

        for (DyeColor color: DyeColor.values()) { // Slabs blocks
            if (color == DyeColor.WHITE) continue;

            ResourceLocation tex = modLoc("block/slab/illuminant_slab_"+ color.getName());

            models().withExistingParent("block/illuminant_slab_" + color.getName(), modLoc("block/illuminant_slab")).texture("up", tex);
        }

        for (DyeColor color: DyeColor.values()) { // Panels
            if (color == DyeColor.WHITE) continue;

            ResourceLocation tex = modLoc("block/panel/illuminant_panel_"+ color.getName());

            models().withExistingParent("block/illuminant_panel_" + color.getName(), modLoc("block/illuminant_panel")).texture("up", tex);
        }

        for (DyeColor color: SLBlocks.LIMITED_COLORS) { // Rods
            if (color == DyeColor.WHITE) continue;

            ResourceLocation tex = modLoc("block/omni/omni2_"+ color.getName());

            models().withExistingParent("block/rodlamp_" + color.getName(), modLoc("block/rodlamp")).texture("all", tex).texture("particle", tex);
        }

        for (DyeColor color: SLBlocks.LIMITED_COLORS) { // Light Bulbs
            if (color == DyeColor.WHITE) continue;

            ResourceLocation tex = modLoc("block/omni/omni_"+ color.getName());

            models().withExistingParent("block/lightbulb_base_" + color.getName(), modLoc("block/lightbulb_base")).texture("main", tex).texture("particle", tex);
            models().withExistingParent("block/lightbulb_glow_" + color.getName(), modLoc("block/lightbulb_glow")).texture("main", tex).texture("particle", tex);
        }
    }

    private void generateLampBlock(SLBlockReg<LampBlock, BaseBlockItem> block) {
        var LampBlockBuilder = getVariantBuilder(block.get());
        LampBlockBuilder.partialState().with(LampBlock.ON, true).modelForState().modelFile(models().getExistingFile(modLoc("block/illuminant_"+ block.getBlock().color.getName() +"_block_on"))).addModel();
        LampBlockBuilder.partialState().with(LampBlock.ON, false).modelForState().modelFile(models().getExistingFile(modLoc("block/illuminant_"+ block.getBlock().color.getName() +"_block"))).addModel();
    }

    public void myDirectionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset) {
        getVariantBuilder(block)
            .forAllStatesExcept(state -> {
                Direction dir = state.getValue(BlockStateProperties.FACING);
                return ConfiguredModel.builder()
                    .modelFile(modelFunc.apply(state))
                    .rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
                    .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.toYRot()) + angleOffset) % 360)
                    .build();
            }, BlockStateProperties.WATERLOGGED);
    }
}
