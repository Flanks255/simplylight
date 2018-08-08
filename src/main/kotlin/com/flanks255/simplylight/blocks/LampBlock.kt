package com.flanks255.simplylight.blocks

import net.minecraft.block.Block
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

@Suppress("OverridingDeprecatedMember")

class LampBlock(name :String,private val default :Boolean) :LampBase(name) {
    init {
        setLightLevel(if(default) 1.0f else 0.0f)

        defaultState = blockState.baseState
    }
    companion object {
        val ON:PropertyBool = PropertyBool.create("on")
    }


    override fun createBlockState(): BlockStateContainer = BlockStateContainer(this, ON)

    override fun setCreativeTab(tab: CreativeTabs): LampBlock {
        super.setCreativeTab(tab)
        return this
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        val powered = worldIn.isBlockIndirectlyGettingPowered(pos) > 0
        worldIn.setBlockState(pos, state.withProperty(ON, default != powered))
    }

    override fun onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) {
        val powered = worldIn.isBlockIndirectlyGettingPowered(pos) > 0
        worldIn.setBlockState(pos, state.withProperty(ON, default != powered))
    }

    override fun getMetaFromState(state: IBlockState?): Int {
        return if (state?.getValue(ON) == true) 1 else 0
    }

    override fun getStateFromMeta(meta: Int): IBlockState = defaultState.withProperty(ON, meta > 0)

    override fun getLightValue(state: IBlockState?, world: IBlockAccess?, pos: BlockPos?): Int = if(state?.getValue(ON) == true) 15 else 0
}