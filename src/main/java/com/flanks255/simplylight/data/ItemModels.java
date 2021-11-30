package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.LampBlock;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SimplyLight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        SLBlocks.BLOCKS.getEntries().forEach((block) -> registerBlockItem(block.get()));
    }

    private void registerBlockItem(Block block) {
        String path = block.getRegistryName().getPath();
        // Edge lights, rotate them so you can see them.
        if (block == SLBlocks.EDGELAMP.get()){
            registerEdgeBlockBottom(path);
        } else if (block == SLBlocks.EDGELAMP_TOP.get()) {
            registerEdgeBlockTop(path);
        } else if (block == SLBlocks.LAMP_POST.get()) {
            generateLampPost(path);
        } else if (block instanceof LampBlock) {
            getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
        } else if (block == SLBlocks.WALL_LAMP.get()) {
            getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)))
                .transforms().transform(ModelBuilder.Perspective.HEAD)
                .scale(1f,1f,1f)
                .translation(0,4.5f, 14).end()
                .transform(ModelBuilder.Perspective.FIXED)
                .rotation(0,-180,0)
                .scale(1f,1f,1f)
                .translation(0,0,-8).end();
        }
        else {
            getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)))
                .transforms().transform(ModelBuilder.Perspective.HEAD)
                .scale(1f,1f,1f)
                .translation(0,14,0).end();
        }
    }

    private void generateLampPost(String path) {
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/lamp_post")))
            .transforms().transform(ModelBuilder.Perspective.GUI)
            .rotation(45,45,-45)
            .scale(0.33f,0.33f,0.33f)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
            .scale(0.5f, 0.5f, 0.5f)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
            .scale(0.5f, 0.5f, 0.5f)
            .end()
            .transform(ModelBuilder.Perspective.GROUND)
            .translation(0.0f, 8.0f, 0.0f)
            .scale(0.33f, 0.33f, 0.33f)
            .end()
            .transform(ModelBuilder.Perspective.HEAD)
            .scale(0.5f, 0.5f, 0.5f)
            .translation(0.0f, 18.5f, 0.0f)
            .end()
            .transform(ModelBuilder.Perspective.FIXED)
            .scale(0.33f, 0.33f, 0.33f)
            .end();
    }



    private void registerEdgeBlockTop(String path) {
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/edge_light")))
            .transforms().transform(ModelBuilder.Perspective.GUI)
            .rotation(30, -135, 180)
            .scale(0.625f, 0.625f, 0.625f)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ModelBuilder.Perspective.FIXED)
            .rotation(0,-180,0)
            .translation(0f, 7.5f, -8f)
            .end()
            .transform(ModelBuilder.Perspective.HEAD)
            .scale(1f,1f,1f)
            .translation(0f,14f,1.5f)
            .end();
    }

    private void registerEdgeBlockBottom(String path) {
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/edge_light")))
            .transforms().transform(ModelBuilder.Perspective.GUI)
            .rotation(30, -135, 180)
            .translation(0,-6,0)
            .scale(0.625f, 0.625f, 0.625f)
            .end()
            .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ModelBuilder.Perspective.FIXED)
            .rotation(0,-180,0)
            .scale(1f,1f,1f)
            .translation(0f, 7.5f, -8f)
            .end()
            .transform(ModelBuilder.Perspective.HEAD)
            .scale(1f,1f,1f)
            .translation(0f,14f,1.5f)
            .end();
    }
}

