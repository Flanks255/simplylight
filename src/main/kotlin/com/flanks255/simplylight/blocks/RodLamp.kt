package com.flanks255.simplylight.blocks

import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

class RodLamp(name: String) : LampBase(name)  {
    init {
        super.setLightOpacity(0)

    }

    val AABB_UPDOWN: AxisAlignedBB = AxisAlignedBB(0.4375,0.0,0.4375,0.5625,1.0,0.5625)
    val AABB_EASTWEST: AxisAlignedBB = AxisAlignedBB(0.0,0.4375,0.4375,1.0,0.5625,0.5625)
    val AABB_NORTHSOUTH: AxisAlignedBB = AxisAlignedBB(0.4375,0.4375,0.0,0.5625,0.5625,1.0)

companion object {
    val AXIS: PropertyEnum<EnumFacing.Axis> = PropertyEnum.create("axis", EnumFacing.Axis::class.java)
}

    override fun getBlockFaceShape(worldIn: IBlockAccess?, state: IBlockState?, pos: BlockPos?, face: EnumFacing?): BlockFaceShape {
        return if (face?.axis == state?.getValue(AXIS)) BlockFaceShape.CENTER else BlockFaceShape.UNDEFINED
    }


    override fun getStateForPlacement(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState {
        return defaultState.withProperty(AXIS, facing.axis)
    }

    override fun canPlaceBlockOnSide(worldIn: World, pos: BlockPos, side: EnumFacing): Boolean {
        val shape = worldIn.getBlockState(pos.offset(side.opposite)).getBlockFaceShape(worldIn,pos,side)
        return shape == BlockFaceShape.SOLID || shape == BlockFaceShape.CENTER || shape == BlockFaceShape.CENTER_BIG
    }

    private fun getMyBoundingBox(state: IBlockState?): AxisAlignedBB {
        return when(state?.getValue(AXIS)){
            EnumFacing.Axis.X->AABB_EASTWEST
            EnumFacing.Axis.Y->AABB_UPDOWN
            else->AABB_NORTHSOUTH
        }
    }

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?): AxisAlignedBB {
        return getMyBoundingBox(state)
    }

    override fun getCollisionBoundingBox(blockState: IBlockState?, worldIn: IBlockAccess?, pos: BlockPos?): AxisAlignedBB? {
        return getMyBoundingBox(blockState)
    }

    @SideOnly(Side.CLIENT)
    override fun isOpaqueCube(state: IBlockState?): Boolean = false

    override fun isFullCube(state: IBlockState?): Boolean = false

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, AXIS)
    }

    override fun getMetaFromState(state: IBlockState?): Int {
        return when(state?.getValue(AXIS)){
            EnumFacing.Axis.X->0
            EnumFacing.Axis.Y->1
            else->2
        }
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(AXIS, when(meta){
            0->EnumFacing.Axis.X
            1->EnumFacing.Axis.Y
            else-> EnumFacing.Axis.Z
        })
    }
    override fun setCreativeTab(tab: CreativeTabs): RodLamp {
        super.setCreativeTab(tab)
        return this
    }
}