package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
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
        }
        // All the rest are fine
        else {
            getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
        }
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
                .end();
    }
}

