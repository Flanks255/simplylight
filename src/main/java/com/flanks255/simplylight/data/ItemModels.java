package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLight;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SimplyLight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        SimplyLight.BLOCKS.getEntries().stream().filter((b) -> b != SimplyLight.EDGELAMP_TOP).forEach((block) -> {
            registerBlockItem(block.get());
        });
        registerTopEdgeBlock();
    }

    private void registerBlockItem(Block block) {
        String path = block.getRegistryName().getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/"+path)));
    }

    private void registerTopEdgeBlock() {
        String path = SimplyLight.EDGELAMP_TOP.get().getRegistryName().getPath();
        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/edge_light")))
                .transforms().transform(ModelBuilder.Perspective.GUI)
                .rotation(30, -135, 180)
                .scale(0.625f, 0.625f, 0.625f)
                .end();
    }
}

