package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.*;
import com.flanks255.simplylight.data.Generator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SimplyLight.MODID)
public class SimplyLight
{
    public static final String MODID = "simplylight";
    public static final Logger LOGGER = LogManager.getLogger("Simply Light");

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> ILLUMINANTBLOCK = BLOCKS.register("illuminant_block", () -> new LampBlock( false));
    public static final RegistryObject<Block> ILLUMINANTBLOCK_ON = BLOCKS.register("illuminant_block_on", () -> new LampBlock(true));
    public static final RegistryObject<Block> ILLUMINANTSLAB = BLOCKS.register("illuminant_slab", () -> new ThinLamp(8));
    public static final RegistryObject<Block> ILLUMINANTPANEL = BLOCKS.register("illuminant_panel", () -> new ThinLamp(4));
    public static final RegistryObject<Block> WALL_LAMP = BLOCKS.register("wall_lamp", WallLamp::new);
    public static final RegistryObject<Block> LIGHTBULB = BLOCKS.register("lightbulb", LightBulb::new);
    public static final RegistryObject<Block> RODLAMP = BLOCKS.register("rodlamp", RodLamp::new);
    public static final RegistryObject<Block> EDGELAMP = BLOCKS.register("edge_light", () -> new EdgeLight(false));
    public static final RegistryObject<Block> EDGELAMP_TOP = BLOCKS.register("edge_light_top", () -> new EdgeLight(true));

    private static final ItemGroup itemGroup = new ItemGroup(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ILLUMINANTBLOCK_ON.get());
        }
    };
    private static final Item.Properties ITEMPROPERTIES = new Item.Properties().group(itemGroup);

    public static final RegistryObject<Item> ITEM_ILLUMINANTBLOCK = ITEMS.register("illuminant_block", () -> new BaseBlockItem(ILLUMINANTBLOCK.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_ILLUMINANTBLOCK_ON = ITEMS.register("illuminant_block_on", () -> new BaseBlockItem(ILLUMINANTBLOCK_ON.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_ILLUMINANTSLAB = ITEMS.register("illuminant_slab", () -> new BaseBlockItem(ILLUMINANTSLAB.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_ILLUMINANTPANEL = ITEMS.register("illuminant_panel", () -> new BaseBlockItem(ILLUMINANTPANEL.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_WALL_LAMP = ITEMS.register("wall_lamp", () -> new BaseBlockItem(WALL_LAMP.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_LIGHTBULB = ITEMS.register("lightbulb", () -> new BaseBlockItem(LIGHTBULB.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_RODLAMP = ITEMS.register("rodlamp", () -> new BaseBlockItem(RODLAMP.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_EDGELAMP = ITEMS.register("edge_light", () -> new BaseBlockItem(EDGELAMP.get(), ITEMPROPERTIES));
    public static final RegistryObject<Item> ITEM_EDGELAMP_TOP = ITEMS.register("edge_light_top", () -> new BaseBlockItem(EDGELAMP_TOP.get(), ITEMPROPERTIES));

    public SimplyLight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(Generator::gatherData);
    }
}
