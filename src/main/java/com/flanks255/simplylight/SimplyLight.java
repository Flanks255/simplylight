package com.flanks255.simplylight;

import com.flanks255.simplylight.data.Generator;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SimplyLight.MODID)
public class SimplyLight
{
    public static final String MODID = "simplylight";
    public static final Logger LOGGER = LogManager.getLogger("Simply Light");

    public static final Tag.Named<Item> ANY_ON_LAMP = ItemTags.bind(new ResourceLocation(MODID, "any_lamp_on").toString());
    public static final Tag.Named<Item> ANY_OFF_LAMP = ItemTags.bind(new ResourceLocation(MODID, "any_lamp_off").toString());

    public SimplyLight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        SLBlocks.init(bus);

        bus.addListener(Generator::gatherData);
        bus.addListener(this::ClientSetup);

        RecipeUnlocker.register(SimplyLight.MODID, MinecraftForge.EVENT_BUS, 2);
    }

    private void ClientSetup(final FMLClientSetupEvent clientSetupEvent) {
        ItemBlockRenderTypes.setRenderLayer(SLBlocks.LIGHTBULB.get(), renderType -> renderType == RenderType.solid() || renderType == RenderType.translucent());
    }
}
