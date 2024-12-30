package com.flanks255.simplylight.blocks;

import com.flanks255.simplylight.network.OpenEdgeEditorPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class EdgeLight extends LampBase implements SimpleWaterloggedBlock {

    public final Boolean top;
    public final DyeColor color;

    public EdgeLight(Boolean top, DyeColor color) {
        super(Block.Properties.of()
            .strength(1.0f)
            .noCollission()
            .lightLevel($ -> 14)
        );
        this.color = color;
        this.top = top;
        if (top) {
            VS_WEST = Shapes.box(0.0, 0.9375, 0.0, 0.0625, 1.0, 1.0);
            VS_EAST = Shapes.box(0.9375, 0.9375, 0.0, 1.0, 1.0, 1.0);
            VS_SOUTH = Shapes.box(0.0, 0.9375, 0.9375, 1.0, 1.0, 1.0);
            VS_NORTH = Shapes.box(0.0, 0.9375, 0.0, 1.0, 1.0, 0.0625);
            VS_ALL = Shapes.or(VS_WEST, VS_EAST, VS_NORTH, VS_SOUTH);
        }
    }

    public VoxelShape VS_WEST = Shapes.box(0.0, 0.0, 0.0, 0.0625, 0.0625, 1.0);
    public VoxelShape VS_EAST = Shapes.box(1.0-0.0625, 0.0, 0.0, 1.0, 0.0625, 1.0);
    public VoxelShape VS_SOUTH = Shapes.box(0.0, 0.0, 1.0 - 0.0625, 1.0, 0.0625, 1.0);
    public VoxelShape VS_NORTH = Shapes.box(0.0, 0.0, 0.0, 1.0, 0.0625, 0.0625);
    public VoxelShape VS_ALL = Shapes.or(VS_WEST, VS_EAST, VS_NORTH, VS_SOUTH);

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
        VoxelShape shape = Shapes.empty();
        if (pState.getValue(NORTH))
            shape = Shapes.or(shape, VS_NORTH);
        if (pState.getValue(SOUTH))
            shape = Shapes.or(shape, VS_SOUTH);
        if (pState.getValue(EAST))
            shape = Shapes.or(shape, VS_EAST);
        if (pState.getValue(WEST))
            shape = Shapes.or(shape, VS_WEST);

        if (shape.isEmpty())
            return VS_ALL;

        return shape;
    }

    @Nonnull
    @Override
    protected InteractionResult useWithoutItem(@Nonnull BlockState state, @Nonnull Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull BlockHitResult hitResult) {
        if (!level.isClientSide && player.isCrouching() && player instanceof ServerPlayer serverPlayer) {
            byte stateByte = 0;
            stateByte += (byte) (state.getValue(NORTH)?1:0);
            stateByte += (byte) (state.getValue(EAST)?2:0);
            stateByte += (byte) (state.getValue(SOUTH)?4:0);
            stateByte += (byte) (state.getValue(WEST)?8:0);

            PacketDistributor.sendToPlayer(serverPlayer, new OpenEdgeEditorPacket(pos, stateByte));
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    public static void updateShape(Level level, BlockPos pos, byte state) {
        if (level.isClientSide)
            return;
        if (level.getBlockState(pos).getBlock() instanceof EdgeLight) {
            BlockState blockState = level.getBlockState(pos);
            blockState = blockState.setValue(NORTH, (state & 1) != 0);
            blockState = blockState.setValue(EAST, (state & 2) != 0);
            blockState = blockState.setValue(SOUTH, (state & 4) != 0);
            blockState = blockState.setValue(WEST, (state & 8) != 0);
            level.setBlockAndUpdate(pos, blockState);
        }
    }

    public boolean checkSide(BlockPlaceContext context, Direction direction) {
        BlockPos pos = context.getClickedPos().relative(direction);

        //return hasSolidSide(context.getWorld().getBlockState(pos), context.getWorld(), pos, direction.getOpposite());
        return canSupportCenter(context.getLevel(),pos,direction.getOpposite());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, waterlogged)
            .setValue(NORTH, checkSide(context, Direction.NORTH))
            .setValue(SOUTH, checkSide(context, Direction.SOUTH))
            .setValue(EAST, checkSide(context, Direction.EAST))
            .setValue(WEST, checkSide(context, Direction.WEST));
    }

    @Override
    public boolean canPlaceLiquid(Player pPlayer, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull BlockState pState, @Nonnull Fluid pFluid) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState pState) {
        return pState.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(NORTH, SOUTH, EAST, WEST, BlockStateProperties.WATERLOGGED);
    }
    @Override
    public void addLang(BiConsumer<String, String> consumer) {
        String base = getDescriptionId();
        DyeColor color = this.color;

        String colorname = switch (color) {
            case RED -> "Red";
            case BLUE -> "Blue";
            case CYAN -> "Cyan";
            case GRAY -> "Gray";
            case LIME -> "Lime";
            case MAGENTA -> "Magenta";
            case PINK -> "Pink";
            case BLACK -> "Black";
            case BROWN -> "Brown";
            case GREEN -> "Green";
            case ORANGE -> "Orange";
            case PURPLE -> "Purple";
            case YELLOW -> "Yellow";
            case LIGHT_BLUE -> "Light Blue";
            case LIGHT_GRAY -> "Light Gray";
            default -> "";
        };

        if (color == DyeColor.WHITE)
            consumer.accept(base, "Dynamic Edge Light ("+ (top?"top":"bottom") +")");
        else
            consumer.accept(base, "Dynamic " + colorname + " Edge Light ("+ (top?"top":"bottom") +")");

        if (color != DyeColor.WHITE)
            return;
        consumer.accept(base + ".info", "Follows walls around itself,");
        consumer.accept(base + ".info2", "perfect for hallways.");
        consumer.accept(base + ".jei.info", "Will morph depending on the blocks present around itself on placement.\nShape will persist afterward, letting you make shapes using temporary blocks.");
    }

    @Nonnull
    @Override
    public BlockState rotate(@Nonnull BlockState pState, @Nonnull Rotation pRotation) {
        if (pRotation != Rotation.NONE){
            boolean oldNorth = pState.getValue(NORTH);
            boolean oldSouth = pState.getValue(SOUTH);
            boolean oldWest = pState.getValue(WEST);
            boolean oldEast = pState.getValue(EAST);

            if (pRotation == Rotation.CLOCKWISE_90)
                return pState
                        .setValue(NORTH, oldWest)
                        .setValue(EAST, oldNorth)
                        .setValue(SOUTH, oldEast)
                        .setValue(WEST, oldSouth);
            else if (pRotation == Rotation.CLOCKWISE_180) {
                return pState
                    .setValue(NORTH, oldSouth)
                    .setValue(EAST, oldWest)
                    .setValue(SOUTH, oldNorth)
                    .setValue(WEST, oldEast);
            } else if (pRotation == Rotation.COUNTERCLOCKWISE_90) {
                return pState
                    .setValue(NORTH, oldEast)
                    .setValue(EAST, oldSouth)
                    .setValue(SOUTH, oldWest)
                    .setValue(WEST, oldNorth);
            }
        }
        return pState;
    }

    @Nonnull
    @Override
    public BlockState mirror(@Nonnull BlockState pState, @Nonnull Mirror pMirror) {
        if (pMirror != Mirror.NONE) {
            boolean oldNorth = pState.getValue(NORTH);
            boolean oldSouth = pState.getValue(SOUTH);
            boolean oldWest = pState.getValue(WEST);
            boolean oldEast = pState.getValue(EAST);

            if (pMirror == Mirror.FRONT_BACK) {
                return pState
                        .setValue(WEST, oldEast)
                        .setValue(EAST, oldWest);
            } else if (pMirror == Mirror.LEFT_RIGHT) {
                return pState
                        .setValue(NORTH, oldSouth)
                        .setValue(SOUTH, oldNorth);
            }
        }
        return pState;
    }
}
