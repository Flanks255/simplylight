package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.EdgeLight;
import com.flanks255.simplylight.network.OpenEdgeEditorPacket;
import com.flanks255.simplylight.network.UpdateEdgeLightPacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class SimplyLightFabric implements ModInitializer {
    public static final List<Pair<String, ? extends Block>> BLOCKS = new ArrayList<>();
    public static final List<Pair<String, ? extends Item>> ITEMS = new ArrayList<>();

    public static final CreativeModeTab ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(SLBlocks.ILLUMINANTBLOCK_ON.getItem()))
            .title(Component.translatable("itemGroup.simplylight"))
            .displayItems(SimplyLightCommon.TAB_ITEMS)
            .build();

    public SimplyLightFabric() {
        SLBlocks.load();
    }

    @Override
    public void onInitialize() {
        registerBlocksAndItems();
        SimplyLightCommon.init();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SimplyLightCommon.SLRes("simplylight"), ITEM_GROUP);

        PayloadTypeRegistry.playS2C().register(OpenEdgeEditorPacket.TYPE, OpenEdgeEditorPacket.STREAM_CODEC);
        PayloadTypeRegistry.playC2S().register(UpdateEdgeLightPacket.TYPE, UpdateEdgeLightPacket.STREAM_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(UpdateEdgeLightPacket.TYPE,  (packet, context) -> EdgeLight.updateShape(context.player().level(), packet.pos(), packet.state()));
    }

    public static void registerBlocksAndItems() {
        BLOCKS.forEach(reg -> {
            Registry.register(BuiltInRegistries.BLOCK, SimplyLightCommon.SLRes(reg.getA()), reg.getB());
        });
        ITEMS.forEach(reg -> {
            Registry.register(BuiltInRegistries.ITEM, SimplyLightCommon.SLRes(reg.getA()), reg.getB());
        });
    }
}
