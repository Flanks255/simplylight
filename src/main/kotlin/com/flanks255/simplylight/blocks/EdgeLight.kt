package com.flanks255.simplylight.blocks

import net.minecraft.block.Block
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockFaceShape
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.RayTraceResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@Suppress("OverridingDeprecatedMember")

open class EdgeLight(name: String): LampBase(name) {
    init {
        super.setLightOpacity(0)
    }

    open val AABB_ALL = AxisAlignedBB(0.0,0.0,0.0,1.0,0.0625,1.0)

    open val AABB_WEST = AxisAlignedBB(0.0, 0.0, 0.0, 0.0625, 0.0625, 1.0)
    open val AABB_EAST = AxisAlignedBB(1.0, 0.0, 0.0, 1.0-0.0625, 0.0625, 1.0)
    open val AABB_SOUTH = AxisAlignedBB(0.0, 0.0, 1.0 - 0.0625, 1.0, 0.0625, 1.0)
    open val AABB_NORTH = AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0625, 0.0625)

    companion object {
        val NORTH: PropertyBool = PropertyBool.create("north")
        val SOUTH: PropertyBool = PropertyBool.create("south")
        val WEST: PropertyBool = PropertyBool.create("west")
        val EAST: PropertyBool = PropertyBool.create("east")
    }


    override fun setCreativeTab(tab: CreativeTabs): EdgeLight {
        super.setCreativeTab(tab)
        return this
    }

    private fun touches(world: IBlockAccess, pos: BlockPos, facing: EnumFacing): Boolean{
        val other = pos.offset(facing)
        return (world.getBlockState(other).getBlockFaceShape(world,pos,facing.opposite) == BlockFaceShape.SOLID)
    }
    private fun checkSides(world: IBlockAccess, pos: BlockPos): IBlockState {
        return defaultState.withProperty(NORTH, touches(world ,pos, EnumFacing.NORTH))
                .withProperty(SOUTH, touches(world, pos, EnumFacing.SOUTH))
                .withProperty(WEST, touches(world, pos, EnumFacing.WEST))
                .withProperty(EAST, touches(world, pos, EnumFacing.EAST))
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand): IBlockState {
        return checkSides(world, pos)
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        worldIn.setBlockState(pos, checkSides(worldIn, pos))
    }

    override fun getActualState(state: IBlockState, worldIn: IBlockAccess, pos: BlockPos): IBlockState {
        return state.withProperty(NORTH, touches(worldIn ,pos, EnumFacing.NORTH))
                .withProperty(SOUTH, touches(worldIn, pos, EnumFacing.SOUTH))
                .withProperty(WEST, touches(worldIn, pos, EnumFacing.WEST))
                .withProperty(EAST, touches(worldIn, pos, EnumFacing.EAST))
    }

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?): AxisAlignedBB {
        return AABB_ALL
    }

    override fun isPassable(worldIn: IBlockAccess?, pos: BlockPos?): Boolean {
        return true
    }

    override fun getCollisionBoundingBox(blockState: IBlockState?, worldIn: IBlockAccess?, pos: BlockPos?): AxisAlignedBB? {
        return null
    }

    override fun collisionRayTrace(blockState: IBlockState, worldIn: World, pos: BlockPos, start: Vec3d, end: Vec3d): RayTraceResult? {
        val state = getActualState(blockState, worldIn as IBlockAccess, pos)

        if (state.getValue(NORTH) && rayTrace(pos, start, end, AABB_NORTH) != null) return super.collisionRayTrace(blockState, worldIn, pos, start, end)
        if (state.getValue(SOUTH) && rayTrace(pos, start, end, AABB_SOUTH) != null) return super.collisionRayTrace(blockState, worldIn, pos, start, end)
        if (state.getValue(EAST) && rayTrace(pos, start, end, AABB_EAST) != null) return super.collisionRayTrace(blockState, worldIn, pos, start, end)
        if (state.getValue(WEST) && rayTrace(pos, start, end, AABB_WEST) != null) return super.collisionRayTrace(blockState, worldIn, pos, start, end)
        if (!state.getValue(NORTH) && !state.getValue(SOUTH) && !state.getValue(WEST) && !state.getValue(EAST))
            return super.collisionRayTrace(blockState, worldIn, pos, start, end)
        return null
    }

    @SideOnly(Side.CLIENT)
    override fun isOpaqueCube(state: IBlockState?): Boolean = false
    override fun isFullCube(state: IBlockState?): Boolean = false
    override fun isFullBlock(state: IBlockState?): Boolean = false
    override fun getMetaFromState(state: IBlockState?): Int {
        if (state == null) return 0
        var meta = 0
        meta += if (state.getValue(NORTH)) 1 else 0
        meta += if (state.getValue(SOUTH)) 2 else 0
        meta += if (state.getValue(EAST)) 4 else 0
        meta += if (state.getValue(WEST)) 8 else 0

        return meta
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(NORTH, (meta and 1 > 0))
                .withProperty(SOUTH, (meta and 2 > 0))
                .withProperty(EAST, (meta and 4 > 0))
                .withProperty(WEST, (meta and 8 > 0))
    }

    override fun getBlockFaceShape(worldIn: IBlockAccess?, state: IBlockState?, pos: BlockPos?, face: EnumFacing?): BlockFaceShape = BlockFaceShape.UNDEFINED
    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, NORTH, SOUTH, WEST, EAST )
    }
}