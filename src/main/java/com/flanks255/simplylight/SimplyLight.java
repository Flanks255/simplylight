package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(SimplyLight.MODID)
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class SimplyLight
{
    public static final String MODID = "simplylight";

    public static final LampBlock illuminantBlock = new LampBlock("illuminant_block", false);
    public static final LampBlock illuminantBlock2 = new LampBlock("illuminant_block_on", true);
    public static final ThinLamp illuminantSlab = new ThinLamp("illuminant_slab",8);
    public static final ThinLamp illuminantPanel = new ThinLamp("illuminant_panel",4);
    public static final WallLamp wallLamp = new WallLamp("wall_lamp");
    public static final LightBulb lightBulb = new LightBulb("lightbulb");
    public static final RodLamp rodLamp = new RodLamp("rodlamp");


    private static ItemGroup itemGroup = new ItemGroup(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(illuminantBlock2);
        }
    };

    public SimplyLight() {

    }

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        IForgeRegistry<Block> blockRegistry = blockRegistryEvent.getRegistry();

        blockRegistry.register(SimplyLight.illuminantBlock);
        blockRegistry.register(SimplyLight.illuminantBlock2);
        blockRegistry.register(SimplyLight.illuminantSlab);
        blockRegistry.register(SimplyLight.illuminantPanel);
        blockRegistry.register(SimplyLight.wallLamp);
        blockRegistry.register(SimplyLight.lightBulb);
        blockRegistry.register(SimplyLight.rodLamp);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        Item.Properties properties = new Item.Properties().group(itemGroup);

        IForgeRegistry<Item> itemRegistry = itemRegistryEvent.getRegistry();

        itemRegistry.register(new BaseBlockItem(SimplyLight.illuminantBlock, properties).setRegistryName("illuminant_block"));
        itemRegistry.register(new BaseBlockItem(SimplyLight.illuminantBlock2, properties).setRegistryName("illuminant_block_on"));
        itemRegistry.register(new BaseBlockItem(SimplyLight.illuminantSlab, properties).setRegistryName("illuminant_slab"));
        itemRegistry.register(new BaseBlockItem(SimplyLight.illuminantPanel, properties).setRegistryName("illuminant_panel"));
        itemRegistry.register(new BaseBlockItem(SimplyLight.wallLamp, properties).setRegistryName("wall_lamp"));
        itemRegistry.register(new BaseBlockItem(SimplyLight.lightBulb, properties).setRegistryName("lightbulb"));
        itemRegistry.register(new BaseBlockItem(SimplyLight.rodLamp, properties).setRegistryName("rodlamp"));
    }
}
