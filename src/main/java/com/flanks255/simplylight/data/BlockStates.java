package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.LampBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates  extends BlockStateProvider {
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SimplyLight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        generateLampBlock();
    }

    private void generateLampBlock() {
        ResourceLocation offTex = modLoc("block/illuminant_block");
        ResourceLocation onTex = modLoc("block/illuminant_block_on");

        ModelBuilder.ElementBuilder illuminantBlockBuilder = models().withExistingParent(SimplyLight.ILLUMINANTBLOCK.get().getRegistryName().getPath(), "cube")
                .texture("all", offTex).ao(false).element();
        ModelFile modelIlluminantBlock = illuminantBlockBuilder.cube("#all").shade(false).end();

        ModelBuilder.ElementBuilder illuminantBlockBuilder_On = models().withExistingParent(SimplyLight.ILLUMINANTBLOCK_ON.get().getRegistryName().getPath(), "cube")
                .texture("all", onTex).ao(false).element();
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
}
