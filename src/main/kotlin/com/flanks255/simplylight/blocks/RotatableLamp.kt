package com.flanks255.simplylight.blocks

import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("OverridingDeprecatedMember")

abstract class RotatableLamp(name: String): LampBase(name) {


    abstract val AABB_UP : AxisAlignedBB
    abstract val AABB_DOWN : AxisAlignedBB
    abstract val AABB_EAST : AxisAlignedBB
    abstract val AABB_WEST : AxisAlignedBB
    abstract val AABB_NORTH : AxisAlignedBB
    abstract val AABB_SOUTH : AxisAlignedBB

    companion object {
        val FACING : PropertyDirection = PropertyDirection.create("facing")
    }

    override fun getBlockFaceShape(worldIn: IBlockAccess?, state: IBlockState?, pos: BlockPos?, face: EnumFacing?): BlockFaceShape {
        return BlockFaceShape.UNDEFINED
    }

    private fun getMyBoundingBox(state: IBlockState?): AxisAlignedBB {
        return when(state?.getValue(FACING)){
            EnumFacing.DOWN-> AABB_DOWN
            EnumFacing.UP-> AABB_UP
            EnumFacing.EAST-> AABB_EAST
            EnumFacing.WEST-> AABB_WEST
            EnumFacing.NORTH-> AABB_NORTH
            EnumFacing.SOUTH-> AABB_SOUTH
            null->AABB_UP
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

    override fun getStateForPlacement(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState {
        //button placement: defaultState.withProperty(FACING, facing)
        //piston placement: defaultState.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer))
        return defaultState.withProperty(FACING, facing)
    }

    override fun canPlaceBlockOnSide(worldIn: World, pos: BlockPos, side: EnumFacing): Boolean {
        val shape = worldIn.getBlockState(pos.offset(side.opposite)).getBlockFaceShape(worldIn,pos,side)
        return shape == BlockFaceShape.SOLID || shape == BlockFaceShape.CENTER
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    override fun getMetaFromState(state: IBlockState?): Int {
        return when(state?.getValue(FACING)){
            EnumFacing.UP-> 0
            EnumFacing.DOWN-> 1
            EnumFacing.EAST-> 2
            EnumFacing.WEST-> 3
            EnumFacing.NORTH-> 4
            EnumFacing.SOUTH-> 5
            null->0
        }
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, when(meta){
            0->EnumFacing.UP
            1->EnumFacing.DOWN
            2->EnumFacing.EAST
            3->EnumFacing.WEST
            4->EnumFacing.NORTH
            5->EnumFacing.SOUTH
            else->EnumFacing.UP
        })
    }

    override fun withRotation(state: IBlockState, rot: Rotation): IBlockState {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)))
    }

    override fun withMirror(state: IBlockState, mirrorIn: Mirror): IBlockState {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)))
    }

    override fun setCreativeTab(tab: CreativeTabs): LampBase {
        super.setCreativeTab(tab)
        return this
    }

}