package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.EdgeLight;
import com.flanks255.simplylight.blocks.LampBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates  extends BlockStateProvider {
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SimplyLight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        generateLampBlock();
        generateThinLamps();

        //Light bulbs
        myDirectionalBlock(SimplyLight.LIGHTBULB.get(), $ -> models().getExistingFile(modLoc("block/lightbulb")), 180);

        generateRodLamp();

        generateWallLamp();
    }
/*
    private void generateEdgeBlocks() {
        ModelFile model = models().getExistingFile(modLoc("block/edge_light"));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(SimplyLight.EDGELAMP.get());

        builder.part().modelFile(model).addModel().useOr()
                .condition(EdgeLight.NORTH, true);


    }
*/
    private void generateWallLamp() {
        ModelFile wallModel = models().getExistingFile(modLoc("block/wall_lamp"));
        ModelFile floorModel = models().getExistingFile(modLoc("block/floorlamp"));
        getVariantBuilder(SimplyLight.WALL_LAMP.get())
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
        myDirectionalBlock(SimplyLight.ILLUMINANTSLAB.get(), $ -> models().getExistingFile(modLoc("block/illuminant_slab")), 180);
        myDirectionalBlock(SimplyLight.ILLUMINANTPANEL.get(), $ -> models().getExistingFile(modLoc("block/illuminant_panel")), 180);
    }

    void generateRodLamp() {
        Block block = SimplyLight.RODLAMP.get();
        ModelFile model = models().getExistingFile(modLoc("block/rodlamp"));
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel();
    }

    private void generateLampBlock() {
        ResourceLocation offTex = modLoc("block/illuminant_block");
        ResourceLocation onTex = modLoc("block/illuminant_block_on");

        ModelBuilder.ElementBuilder illuminantBlockBuilder = models().withExistingParent(SimplyLight.ILLUMINANTBLOCK.get().getRegistryName().getPath(), "cube")
                .texture("all", offTex).texture("particle", offTex).ao(false).element();
        ModelFile modelIlluminantBlock = illuminantBlockBuilder.cube("#all").shade(false).end();

        ModelBuilder.ElementBuilder illuminantBlockBuilder_On = models().withExistingParent(SimplyLight.ILLUMINANTBLOCK_ON.get().getRegistryName().getPath(), "cube")
                .texture("all", onTex).texture("particle", onTex).ao(false).element();
        ModelFile modelIlluminantBlock_on = illuminantBlockBuilder_On.cube("#all").shade(false).end();

        //Build non-inverted
        VariantBlockStateBuilder LampBlockBuilder = getVariantBuilder(SimplyLight.ILLUMINANTBLOCK.get());
        LampBlockBuilder.partialState().with(LampBlock.ON, true).modelForState().modelFile(modelIlluminantBlock_on).addModel();
        LampBlockBuilder.partialState().with(LampBlock.ON, false).modelForState().modelFile(modelIlluminantBlock).addModel();

        //Build inverted
        VariantBlockStateBuilder LampBlockOnBuilder = getVariantBuilder(SimplyLight.ILLUMINANTBLOCK_ON.get());
        LampBlockOnBuilder.partialState().with(LampBlock.ON, true).modelForState().modelFile(modelIlluminantBlock_on).addModel();
        LampBlockOnBuilder.partialState().with(LampBlock.ON, false).modelForState().modelFile(modelIlluminantBlock).addModel();
    }

    public void myDirectionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset) {
        getVariantBuilder(block)
                .forAllStatesExcept(state -> {
                    Direction dir = state.get(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
                            .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.getHorizontalAngle()) + angleOffset) % 360)
                            .build();
                }, BlockStateProperties.WATERLOGGED);
    }
}
