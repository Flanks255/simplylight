package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("simplylight")
public class simplylight
{
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "simplylight";

    public static final LampBlock illuminantBlock = new LampBlock("illuminant_block", false);
    public static final LampBlock illuminantBlock2 = new LampBlock("illuminant_block_on", true);
    public static final ThinLamp illuminantSlab = new ThinLamp("illuminant_slab",8);
    public static final ThinLamp illuminantPanel = new ThinLamp("illuminant_panel",4);
    public static final WallLamp wallLamp = new WallLamp("wall_lamp");
    public static final LightBulb lightBulb = new LightBulb("lightbulb");


    private static ItemGroup itemGroup = new ItemGroup(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(illuminantBlock2);
        }
    };

    public simplylight() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        //LOGGER.info("HELLO FROM PREINIT");
        //LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            //LOGGER.info("HELLO from Register Block");

            IForgeRegistry<Block> blockRegistry = blockRegistryEvent.getRegistry();

            blockRegistry.register(simplylight.illuminantBlock);
            blockRegistry.register(simplylight.illuminantBlock2);
            blockRegistry.register(simplylight.illuminantSlab);
            blockRegistry.register(simplylight.illuminantPanel);
            blockRegistry.register(simplylight.wallLamp);
            blockRegistry.register(simplylight.lightBulb);
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            Item.Properties properties = new Item.Properties().group(itemGroup);

            IForgeRegistry<Item> itemRegistry = itemRegistryEvent.getRegistry();

            itemRegistry.register(new BaseBlockItem(simplylight.illuminantBlock, properties).setRegistryName("illuminant_block"));
            itemRegistry.register(new BaseBlockItem(simplylight.illuminantBlock2, properties).setRegistryName("illuminant_block_on"));
            itemRegistry.register(new BaseBlockItem(simplylight.illuminantSlab, properties).setRegistryName("illuminant_slab"));
            itemRegistry.register(new BaseBlockItem(simplylight.illuminantPanel, properties).setRegistryName("illuminant_panel"));
            itemRegistry.register(new BaseBlockItem(simplylight.wallLamp, properties).setRegistryName("wall_lamp"));
            itemRegistry.register(new BaseBlockItem(simplylight.lightBulb, properties).setRegistryName("lightbulb"));
        }
    }
}
