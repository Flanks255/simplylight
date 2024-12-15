package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;


public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), SimplyLight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        SLBlocks.BLOCKS.getEntries().forEach((block) -> registerBlockItem(block.get()));
    }

    private void registerBlockItem(Block blockIn) {
        if (blockIn instanceof LampBase block) {
            String path = block.getRegistryName().getPath();
            switch (block) {
                case EdgeLight edge when !edge.top -> registerEdgeBlockBottom(edge);
                case EdgeLight edge -> registerEdgeBlockTop(edge);
                case LampPost post -> generateLampPost(post);
                case LampBlock lampBlock ->
                        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
                case Fixture fixture ->
                        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)))
                                .transforms().transform(ItemDisplayContext.HEAD)
                                .scale(1f, 1f, 1f)
                                .translation(0, 4.5f, 14).end()
                                .transform(ItemDisplayContext.FIXED)
                                .rotation(0, -180, 0)
                                .scale(1f, 1f, 1f)
                                .translation(0, 0, -8).end();
                case LightBulb lightBulb ->
                        getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)))
                                .transforms().transform(ItemDisplayContext.HEAD)
                                .scale(1f, 1f, 1f)
                                .translation(0, 14, 0).end()
                                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                                .translation(0, 8, 0).end()
                                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                                .translation(0, 8, 0).end()
                                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                                .translation(0, 8, 0).end()
                                .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
                                .translation(0, 8, 0).end();
                default -> getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)))
                        .transforms().transform(ItemDisplayContext.HEAD)
                        .scale(1f, 1f, 1f)
                        .translation(0, 14, 0).end();
            }
        }
    }

    private void generateLampPost(LampPost block) {
        String path = block.getRegistryName().getPath();
        ModelFile model;
        DyeColor color = block.color;

        if (color == DyeColor.WHITE) {
            model = new ModelFile.UncheckedModelFile(modLoc("block/lamp_post_item"));
        } else {
            model = new ModelFile.UncheckedModelFile(modLoc("block/lamp_post_item_" + color.getName()));
        }

        getBuilder(path).parent(model)
            .transforms().transform(ItemDisplayContext.GUI)
            .rotation(45,45,-45)
            .scale(0.33f,0.33f,0.33f)
            .end()
            .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
            .scale(0.5f, 0.5f, 0.5f)
            .end()
            .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            .scale(0.5f, 0.5f, 0.5f)
            .end()
            .transform(ItemDisplayContext.GROUND)
            .translation(0.0f, 8.0f, 0.0f)
            .scale(0.33f, 0.33f, 0.33f)
            .end()
            .transform(ItemDisplayContext.HEAD)
            .scale(0.5f, 0.5f, 0.5f)
            .translation(0.0f, 18.5f, 0.0f)
            .end()
            .transform(ItemDisplayContext.FIXED)
            .scale(0.33f, 0.33f, 0.33f)
            .end();
    }



    private void registerEdgeBlockTop(EdgeLight block) {
        ModelFile model;
        DyeColor color = block.color;
        String path = block.getRegistryName().getPath();

        if (color == DyeColor.WHITE) {
            model = new ModelFile.UncheckedModelFile(modLoc("block/edge_light"));
        } else {
            model = new ModelFile.UncheckedModelFile(modLoc("block/edge_light_" + color.getName()));
        }

        getBuilder(path).parent(model)
            .transforms().transform(ItemDisplayContext.GUI)
            .rotation(30, -135, 180)
            .scale(0.625f, 0.625f, 0.625f)
            .end()
            .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ItemDisplayContext.FIXED)
            .rotation(0,-180,0)
            .translation(0f, 7.5f, -8f)
            .end()
            .transform(ItemDisplayContext.HEAD)
            .scale(1f,1f,1f)
            .translation(0f,14f,1.5f)
            .end();
    }

    private void registerEdgeBlockBottom(EdgeLight block) {
        ModelFile model;
        DyeColor color = block.color;
        String path = block.getRegistryName().getPath();

        if (color == DyeColor.WHITE) {
            model = new ModelFile.UncheckedModelFile(modLoc("block/edge_light"));
        } else {
            model = new ModelFile.UncheckedModelFile(modLoc("block/edge_light_" + color.getName()));
        }

        getBuilder(path).parent(model)
            .transforms().transform(ItemDisplayContext.GUI)
            .rotation(30, -135, 180)
            .translation(0,-6,0)
            .scale(0.625f, 0.625f, 0.625f)
            .end()
            .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
            .rotation(-45,0,90)
            .translation(-7.5f, 6.5f, 4.5f)
            .scale(1f,1f,1f)
            .end()
            .transform(ItemDisplayContext.FIXED)
            .rotation(0,-180,0)
            .scale(1f,1f,1f)
            .translation(0f, 7.5f, -8f)
            .end()
            .transform(ItemDisplayContext.HEAD)
            .scale(1f,1f,1f)
            .translation(0f,14f,1.5f)
            .end();
    }
}

