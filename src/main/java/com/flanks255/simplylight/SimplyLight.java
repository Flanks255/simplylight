package com.flanks255.simplylight;

import com.flanks255.simplylight.data.Generator;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SimplyLight.MODID)
public class SimplyLight
{
    public static final String MODID = "simplylight";
    public static final Logger LOGGER = LogManager.getLogger("Simply Light");
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimplyLight.MODID);

    public static final RegistryObject<CreativeModeTab> TAB = TABS.register("lights", () ->
        CreativeModeTab.builder().icon(() -> new ItemStack(SLBlocks.ILLUMINANTBLOCK_ON.getItem()))
                .title(Component.literal("Simply Light"))
                .displayItems((params, output) -> SLBlocks.TAB_ORDER.forEach(block -> output.accept(block.getItem())))
                .build());

    public static final TagKey<Item> ANY_ON_LAMP = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "any_lamp_on"));
    public static final TagKey<Item> ANY_OFF_LAMP = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "any_lamp_off"));

    public SimplyLight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        SLBlocks.init(bus);
        TABS.register(bus);

        bus.addListener(Generator::gatherData);

        RecipeUnlocker.register(SimplyLight.MODID, MinecraftForge.EVENT_BUS, 2);
    }
}
