package com.flanks255.simplylight;

import com.flanks255.simplylight.data.Generator;
import com.flanks255.simplylight.util.RecipeUnlocker;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@Mod(SimplyLight.MODID)
public class SimplyLight
{
    public static final String MODID = "simplylight";
    public static final Logger LOGGER = LogManager.getLogger("Simply Light");
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimplyLight.MODID);

    private static final CreativeModeTab.DisplayItemsGenerator TABITEMS = (params, output) -> {
        SLBlocks.TAB_ORDER.forEach(block -> output.accept(block.getItem()));
        SLBlocks.LAMPBLOCKS_ON.forEach(block -> output.accept(block.getItem()));
        SLBlocks.LAMPBLOCKS_OFF.forEach(block -> output.accept(block.getItem()));
        SLBlocks.SLABS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.PANELS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.RODS.forEach(block -> output.accept(block.getItem()));
        SLBlocks.BULBS.forEach(block -> output.accept(block.getItem()));
    };

    public static final Supplier<CreativeModeTab> TAB = TABS.register("lights", () ->
        CreativeModeTab.builder().icon(() -> new ItemStack(SLBlocks.ILLUMINANTBLOCK_ON.getItem()))
                .title(Component.literal("Simply Light"))
                .displayItems(TABITEMS)
                .build());

    public static final TagKey<Item> ANY_ON_LAMP = TagKey.create(Registries.ITEM, SLRes("any_lamp_on"));
    public static final TagKey<Item> ANY_OFF_LAMP = TagKey.create(Registries.ITEM, SLRes("any_lamp_off"));
    public static final TagKey<Item> ANY_SLAB = TagKey.create(Registries.ITEM, SLRes("any_slab"));
    public static final TagKey<Item> ANY_PANEL = TagKey.create(Registries.ITEM, SLRes("any_panel"));
    public static final TagKey<Item> ANY_ROD = TagKey.create(Registries.ITEM, SLRes("any_rod"));
    public static final TagKey<Item> ANY_BULB = TagKey.create(Registries.ITEM, SLRes("any_bulb"));

    public SimplyLight(IEventBus bus) {
        SLBlocks.init(bus);
        TABS.register(bus);

        bus.addListener(Generator::gatherData);

        RecipeUnlocker.register(SimplyLight.MODID, NeoForge.EVENT_BUS, 3);
    }

    public static ResourceLocation SLRes(String path) {
        return ResourceLocation.fromNamespaceAndPath(SimplyLight.MODID, path);
    }
}
