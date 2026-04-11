package com.flanks255.simplylight;

import com.flanks255.simplylight.data.Generator;
import com.flanks255.simplylight.network.SLNetwork;
import com.flanks255.simplylight.util.RecipeUnlocker;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod(SimplyLightCommon.MODID)
public class SimplyLightForge {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimplyLightCommon.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimplyLightCommon.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SimplyLightCommon.MODID);

    public static SimpleChannel NETWORK_CHANNEL;

    public static final Supplier<CreativeModeTab> TAB = TABS.register("lights", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(SLBlocks.ILLUMINANTBLOCK_ON.getItem()))
                    .title(Component.literal("Simply Light"))
                    .displayItems(SimplyLightCommon.TAB_ITEMS)
                    .build());

    public SimplyLightForge(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();
        SimplyLightCommon.init();

        NETWORK_CHANNEL = SLNetwork.register();

        BLOCKS.register(bus);
        ITEMS.register(bus);
        TABS.register(bus);
        SLBlocks.load();

        bus.addListener(Generator::gatherData);
        //bus.addListener(SLNetwork::register);

        RecipeUnlocker.register(SimplyLightCommon.MODID, MinecraftForge.EVENT_BUS, 4);
    }
}