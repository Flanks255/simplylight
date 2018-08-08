package com.flanks255.simplylight.blocks

import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

@Suppress("OverridingDeprecatedMember")

class ThinLamp(name: String, thickness: Double): RotatableLamp(name){

    override val AABB_UP = AxisAlignedBB(0.0,0.0, 0.0,1.0, thickness,1.0)
    override val AABB_DOWN = AxisAlignedBB(0.0, 1.0 - thickness, 0.0, 1.0, 1.0, 1.0)
    override val AABB_EAST = AxisAlignedBB(0.0, 0.0, 0.0, thickness, 1.0, 1.0)
    override val AABB_WEST = AxisAlignedBB(1.0 - thickness, 0.0, 0.0, 1.0, 1.0, 1.0)
    override val AABB_NORTH = AxisAlignedBB(0.0, 0.0, 1.0 - thickness, 1.0, 1.0, 1.0)
    override val AABB_SOUTH = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, thickness)

    override fun getBlockFaceShape(worldIn: IBlockAccess?, state: IBlockState?, pos: BlockPos?, face: EnumFacing?): BlockFaceShape {
        return if (face?.opposite == state?.getValue(FACING)) BlockFaceShape.SOLID else BlockFaceShape.UNDEFINED
    }

    override fun getStateForPlacement(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState {
        //button placement: defaultState.withProperty(FACING, facing)
        //piston placement: defaultState.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer))
        return if (worldIn.getBlockState(pos.offset(facing.opposite)).block is ThinLamp && !placer.isSneaking)
            defaultState.withProperty(FACING, worldIn.getBlockState(pos.add(facing.opposite.directionVec)).getValue(FACING))
        else
            defaultState.withProperty(FACING, facing)
    }

    override fun canPlaceBlockOnSide(worldIn: World, pos: BlockPos, side: EnumFacing): Boolean {
        return this.canPlaceBlockAt(worldIn, pos)
    }

    override fun setCreativeTab(tab: CreativeTabs): ThinLamp {
        super.setCreativeTab(tab)
        return this
    }
}
