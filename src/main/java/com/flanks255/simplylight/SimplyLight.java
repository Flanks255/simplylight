package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.*;
import com.flanks255.simplylight.data.Generator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(SimplyLight.MODID)
public class SimplyLight
{
    public static final String MODID = "simplylight";
    public static final Logger LOGGER = LogManager.getLogger("Simply Light");

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    private static final Item.Properties ITEMPROPERTIES = new Item.Properties().tab(new ItemGroup(MODID) {
        @Nonnull
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ILLUMINANTBLOCK_ON.get());
        }
    });

    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANTBLOCK = new SLBlockReg<>("illuminant_block", () -> new LampBlock(false), (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANTBLOCK_ON = new SLBlockReg<>("illuminant_block_on", () -> new LampBlock(true), (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANTSLAB = new SLBlockReg<>("illuminant_slab",() -> new ThinLamp(8), (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANTPANEL = new SLBlockReg<>("illuminant_panel",() -> new ThinLamp(4), (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<WallLamp, BaseBlockItem> WALL_LAMP = new SLBlockReg<>("wall_lamp", WallLamp::new, (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB = new SLBlockReg<>("lightbulb", LightBulb::new, (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP = new SLBlockReg<>("rodlamp", RodLamp::new, (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<EdgeLight, BaseBlockItem> EDGELAMP = new SLBlockReg<>("edge_light", () -> new EdgeLight(false), (b) -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<EdgeLight, BaseBlockItem> EDGELAMP_TOP = new SLBlockReg<>("edge_light_top", () -> new EdgeLight(true), (b) -> new BaseBlockItem(b, ITEMPROPERTIES));

    public SimplyLight() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(bus);
        BLOCKS.register(bus);

        bus.addListener(Generator::gatherData);
        bus.addListener(this::ClientSetup);

        RecipeUnlocker.register(SimplyLight.MODID, MinecraftForge.EVENT_BUS, 1);
    }

    private void ClientSetup(final FMLClientSetupEvent clientSetupEvent) {
        RenderTypeLookup.setRenderLayer(LIGHTBULB.get(), renderType -> renderType == RenderType.solid() || renderType == RenderType.translucent());
    }
}
