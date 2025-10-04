package com.flanks255.simplylight;

import com.flanks255.simplylight.data.Generator;
import com.flanks255.simplylight.network.SLNetwork;
import com.flanks255.simplylight.util.RecipeUnlocker;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod(SimplyLightCommon.MODID)
public class SimplyLightNeoForge
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimplyLightCommon.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimplyLightCommon.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimplyLightCommon.MODID);

    public static final Supplier<CreativeModeTab> TAB = TABS.register("lights", () ->
        CreativeModeTab.builder().icon(() -> new ItemStack(SLBlocks.ILLUMINANTBLOCK_ON.getItem()))
                .title(Component.literal("Simply Light"))
                .displayItems(SimplyLightCommon.TAB_ITEMS)
                .build());

    public SimplyLightNeoForge(IEventBus bus, ModContainer container, Dist dist) {
        SimplyLightCommon.init();

        BLOCKS.register(bus);
        ITEMS.register(bus);
        TABS.register(bus);
        SLBlocks.load();

        bus.addListener(Generator::gatherData);
        bus.addListener(SLNetwork::register);

        RecipeUnlocker.register(SimplyLightCommon.MODID, NeoForge.EVENT_BUS, 4);

        if (dist.isClient()) {
            bus.addListener(SimplyLightNeoForge::PackFinders);
        }
    }

    public static void PackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addPackFinders(
                    SimplyLightCommon.SLRes("optional_fullblock_ctm"),
                    PackType.CLIENT_RESOURCES, Component.translatable("simplylight.pack.fullblock_ctm"),
                    PackSource.BUILT_IN,
                    false,
                    Pack.Position.TOP);
        }
    }
}
