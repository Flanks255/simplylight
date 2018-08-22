package com.flanks255.simplylight.blocks

import com.flanks255.simplylight.simplylight
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

@Suppress("OverridingDeprecatedMember")

open class LampBase(val name: String): Block(Material.REDSTONE_LIGHT){
    init {
        super.setLightLevel(1.0f)
        super.setLightOpacity(0)
        super.setHardness(0.5f)
        super.setUnlocalizedName(simplylight.MODID+"."+name)
        super.setHarvestLevel("pickaxe", 0)

        setRegistryName(name)
    }

    fun createItemBlock(): Item {
        return ItemBlock(this).setRegistryName(registryName)
    }

    override fun setCreativeTab(tab: CreativeTabs): LampBase {
        super.setCreativeTab(tab)
        return this
    }

    override fun getLightValue(state: IBlockState?, world: IBlockAccess?, pos: BlockPos?): Int = 15
}