package dev.theatricalmod.theatrical.block.rigging;

import dev.theatricalmod.theatrical.api.ISupport;
import dev.theatricalmod.theatrical.block.TheatricalBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockTruss extends RotatedPillarBlock implements IWaterLoggable, ISupport {

    private static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 16, 16);

    public BlockTruss() {
        super(TheatricalBlocks.BASE_PROPERTIES.notSolid());
        setDefaultState(getStateContainer().getBaseState().with(AXIS, Axis.X).with(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState iFluidState = context.getWorld().getFluidState(context.getPos());
        return getDefaultState().with(AXIS, context.getFace().getAxis()).with(BlockStateProperties.WATERLOGGED, iFluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos pos, BlockPos facingPos) {
        if(state.get(BlockStateProperties.WATERLOGGED)){
            world.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.updatePostPlacement(state, facing, facingState, world, pos, facingPos);
    }

    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder) {
        builder.add(AXIS, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public float[] getLightTransforms(World world, BlockPos pos, Direction facing) {
        return new float[]{0, 0F, 0};
    }
}
