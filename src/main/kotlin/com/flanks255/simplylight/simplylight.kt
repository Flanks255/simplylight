package com.flanks255.simplylight

import com.flanks255.simplylight.blocks.*
import com.flanks255.simplylight.items.ItemBase
import com.flanks255.simplylight.proxy.IProxy
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Suppress("OverridingDeprecatedMember")

//The blocks
val lampBlock :LampBlock = LampBlock("illuminant_block", false).setCreativeTab(CreativeTabs.DECORATIONS)
val lampBlock2 :LampBlock = LampBlock("illuminant_block_on", true).setCreativeTab(CreativeTabs.DECORATIONS)
val illuminantSlab: ThinLamp = ThinLamp("illuminant_slab", 0.5).setCreativeTab(CreativeTabs.DECORATIONS)
val illuminantPanel: ThinLamp = ThinLamp("illuminant_panel", 0.25).setCreativeTab(CreativeTabs.DECORATIONS)
val edgelight: EdgeLight = EdgeLight("edge_light").setCreativeTab(CreativeTabs.DECORATIONS)

object Lightbulb: RotatableLamp("lightbulb"){
    override val AABB_UP = AxisAlignedBB(0.375,0.0, 0.375,0.625, 0.3125,0.625)
    override val AABB_DOWN = AxisAlignedBB(0.375, 1.0, 0.375, 0.625, 0.6875, 0.625)
    override val AABB_EAST = AxisAlignedBB(0.0, 0.375, 0.375, 0.3125, 0.625, 0.625)
    override val AABB_WEST = AxisAlignedBB(0.6875, 0.375, 0.375, 1.0, 0.625, 0.625)
    override val AABB_NORTH = AxisAlignedBB(0.375, 0.375, 0.6875, 0.625, 0.625, 1.0)
    override val AABB_SOUTH = AxisAlignedBB(0.375, 0.375, 0.325, 0.625, 0.625, 0.0)
    override fun getCollisionBoundingBox(blockState: IBlockState?, worldIn: IBlockAccess?, pos: BlockPos?): AxisAlignedBB? = null
}

object WallLamp: RotatableLamp("wall_lamp"){
    override val AABB_UP = AxisAlignedBB(0.375,0.0, 0.375,0.625, 0.125,0.625)
    override val AABB_DOWN = AxisAlignedBB(0.375, 1.0, 0.375, 0.625, 1.0 - 0.125, 0.625)
    override val AABB_EAST = AxisAlignedBB(0.0, 0.375, 0.25, 0.1875, 0.625, 0.75)
    override val AABB_WEST = AxisAlignedBB(1.0, 0.375, 0.25, 1.0 - 0.1875, 0.625, 0.75)
    override val AABB_NORTH = AxisAlignedBB(0.25, 0.375, 1.0, 0.75, 0.625, 1.0 - 0.1875)
    override val AABB_SOUTH = AxisAlignedBB(0.25, 0.375, 0.0, 0.75, 0.625, 0.1875)
    override fun getCollisionBoundingBox(blockState: IBlockState?, worldIn: IBlockAccess?, pos: BlockPos?): AxisAlignedBB? = null
}


@Mod(modid=simplylight.MODID, name=simplylight.NAME, version=simplylight.VERSION, modLanguageAdapter= "net.shadowfacts.forgelin.KotlinAdapter")
object simplylight{

    const val MODID = "simplylight"
    const val NAME= "Simply Light"
    const val VERSION = "@VERSION@"

    @SidedProxy(serverSide = "com.flanks255.simplylight.proxy.ServerProxy", clientSide = "com.flanks255.simplylight.proxy.ClientProxy")
    var proxy: IProxy? = null

    //preinit
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent){
        WallLamp.setCreativeTab(CreativeTabs.DECORATIONS)
        Lightbulb.setCreativeTab(CreativeTabs.DECORATIONS)
    }

    //init
    @Mod.EventHandler
    fun init(event: FMLInitializationEvent){

    }

    @Mod.EventBusSubscriber(modid=simplylight.MODID)
    object RegistryHandler{
        @JvmStatic
        @SubscribeEvent
        fun registerItems(event: RegistryEvent.Register<Item>){
            event.registry.registerAll(
                    BaseItemBlock(lampBlock),
                    BaseItemBlock(lampBlock2),
                    BaseItemBlock(illuminantSlab),
                    BaseItemBlock(illuminantPanel),
                    BaseItemBlock(WallLamp),
                    BaseItemBlock(edgelight),
                    BaseItemBlock(Lightbulb)
            )
        }
        @JvmStatic
        @SubscribeEvent
        fun registerItems(event: ModelRegistryEvent){
            registerItemModel(lampBlock)
            registerItemModel(lampBlock2)
            registerItemModel(illuminantSlab)
            registerItemModel(illuminantPanel)
            registerItemModel(WallLamp)
            registerItemModel(edgelight)
            registerItemModel(Lightbulb)
        }
        @JvmStatic
        @SubscribeEvent
        fun registerBlocks(event: RegistryEvent.Register<Block>){
            event.registry.registerAll(
                    lampBlock,
                    lampBlock2,
                    illuminantPanel,
                    illuminantSlab,
                    WallLamp,
                    edgelight,
                    Lightbulb
            )
        }

        fun registerItemModel(block: LampBase){
            proxy?.registerItemRenderer(Item.getItemFromBlock(block), 0, block.name)
        }
        fun registerItemModel(item: ItemBase){
            proxy?.registerItemRenderer(item, 0, item.name)
        }
    }
}

