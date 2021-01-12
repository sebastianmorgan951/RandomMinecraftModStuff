package com.seb.mymod.block;

import com.seb.mymod.tileentity.ModTileEntities;
import com.seb.mymod.tileentity.MoundTile;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.SwordItem;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.stream.Stream;

public class EmptyMound extends ContainerBlock {

    public static final VoxelShape SHAPE_N = Stream.of(
        Block.makeCuboidShape(1.5502525316941655, 1, 11.257359312880714, 4.550252531694166, 4, 14.257359312880716),
        Block.makeCuboidShape(0, 0, 0, 16, 3, 16),
        Block.makeCuboidShape(10, 2, 1, 12, 4, 4),
        Block.makeCuboidShape(9, 0, 7, 11, 2, 10),
        Block.makeCuboidShape(11, 0, 4, 13, 2, 7),
        Block.makeCuboidShape(10, 0, 9, 15, 5, 13),
        Block.makeCuboidShape(10, 0, 1, 15, 4, 5),
        Block.makeCuboidShape(1.9343419031971258, 1, 3.615235472713387, 6.934341903197126, 6, 7.615235472713387),
        Block.makeCuboidShape(2.934341903197125, 1.0000000000000009, 7.615235472713387, 6.934341903197125, 4.000000000000001, 11.615235472713387),
        Block.makeCuboidShape(3.934341903197126, 1.5, 1.1152354727133869, 8.934341903197126, 3.5, 6.115235472713387),
        Block.makeCuboidShape(9, 2, 5, 11, 4, 6),
        Block.makeCuboidShape(4.5502525316941655, 2, 12.257359312880714, 8.550252531694166, 4, 14.257359312880716),
        Block.makeCuboidShape(6.5502525316941655, 1.9999999999999982, 11.257359312880716, 7.550252531694166, 5, 12.257359312880716),
        Block.makeCuboidShape(3, 0, 0, 8, 4, 4),
        Block.makeCuboidShape(9.050252531694166, 2.5, 7.257359312880716, 12.050252531694168, 3.5, 8.257359312880716),
        Block.makeCuboidShape(10.050252531694165, 2.5, 7.257359312880716, 13.050252531694166, 3.5, 9.257359312880716),
        Block.makeCuboidShape(6.050252531694165, 2.5, 7.257359312880716, 10.050252531694166, 4.5, 11.257359312880716),
        Block.makeCuboidShape(2.5502525316941655, 2, 11.257359312880714, 4.550252531694166, 7, 13.257359312880716),
        Block.makeCuboidShape(4.5502525316941655, 2, 11.257359312880714, 6.550252531694166, 4, 13.257359312880716),
        Block.makeCuboidShape(1.5502525316941655, 2, 8.257359312880714, 3.5502525316941664, 4, 10.257359312880716),
        Block.makeCuboidShape(10.550252531694166, 2, 2.2573593128807143, 15.550252531694166, 4, 4.257359312880716)
        ).reduce((v1,v2) -> {return VoxelShapes.combineAndSimplify
            (v1,v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(1.7426406871192839, 1, 1.5502525316941655, 4.742640687119286, 4, 4.550252531694166),
            Block.makeCuboidShape(0, 0, 0, 16, 3, 16),
            Block.makeCuboidShape(12, 2, 10, 15, 4, 12),
            Block.makeCuboidShape(6, 0, 9, 9, 2, 11),
            Block.makeCuboidShape(9, 0, 11, 12, 2, 13),
            Block.makeCuboidShape(3, 0, 10, 7, 5, 15),
            Block.makeCuboidShape(11, 0, 10, 15, 4, 15),
            Block.makeCuboidShape(8.384764527286613, 1, 1.9343419031971258, 12.384764527286613, 6, 6.934341903197126),
            Block.makeCuboidShape(4.384764527286613, 1.0000000000000009, 2.934341903197125, 8.384764527286613, 4.000000000000001, 6.934341903197125),
            Block.makeCuboidShape(9.884764527286613, 1.5, 3.934341903197126, 14.884764527286613, 3.5, 8.934341903197126),
            Block.makeCuboidShape(10, 2, 9, 11, 4, 11),
            Block.makeCuboidShape(1.7426406871192839, 2, 4.5502525316941655, 3.7426406871192857, 4, 8.550252531694166),
            Block.makeCuboidShape(3.742640687119284, 1.9999999999999982, 6.5502525316941655, 4.742640687119284, 5, 7.550252531694166),
            Block.makeCuboidShape(12, 0, 3, 16, 4, 8),
            Block.makeCuboidShape(7.742640687119284, 2.5, 9.050252531694166, 8.742640687119284, 3.5, 12.050252531694168),
            Block.makeCuboidShape(6.742640687119284, 2.5, 10.050252531694165, 8.742640687119284, 3.5, 13.050252531694166),
            Block.makeCuboidShape(4.742640687119284, 2.5, 6.050252531694165, 8.742640687119284, 4.5, 10.050252531694166),
            Block.makeCuboidShape(2.742640687119284, 2, 2.5502525316941655, 4.742640687119286, 7, 4.550252531694166),
            Block.makeCuboidShape(2.742640687119284, 2, 4.5502525316941655, 4.742640687119286, 4, 6.550252531694166),
            Block.makeCuboidShape(5.742640687119284, 2, 1.5502525316941655, 7.742640687119286, 4, 3.5502525316941664),
            Block.makeCuboidShape(11.742640687119284, 2, 10.550252531694166, 13.742640687119286, 4, 15.550252531694166)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(11.449747468305834, 1, 1.7426406871192839, 14.449747468305834, 4, 4.742640687119286),
            Block.makeCuboidShape(0, 0, 0, 16, 3, 16),
            Block.makeCuboidShape(4, 2, 12, 6, 4, 15),
            Block.makeCuboidShape(5, 0, 6, 7, 2, 9),
            Block.makeCuboidShape(3, 0, 9, 5, 2, 12),
            Block.makeCuboidShape(1, 0, 3, 6, 5, 7),
            Block.makeCuboidShape(1, 0, 11, 6, 4, 15),
            Block.makeCuboidShape(9.065658096802874, 1, 8.384764527286613, 14.065658096802874, 6, 12.384764527286613),
            Block.makeCuboidShape(9.065658096802874, 1.0000000000000009, 4.384764527286613, 13.065658096802874, 4.000000000000001, 8.384764527286613),
            Block.makeCuboidShape(7.065658096802874, 1.5, 9.884764527286613, 12.065658096802874, 3.5, 14.884764527286613),
            Block.makeCuboidShape(5, 2, 10, 7, 4, 11),
            Block.makeCuboidShape(7.449747468305834, 2, 1.7426406871192839, 11.449747468305834, 4, 3.7426406871192857),
            Block.makeCuboidShape(8.449747468305834, 1.9999999999999982, 3.742640687119284, 9.449747468305834, 5, 4.742640687119284),
            Block.makeCuboidShape(8, 0, 12, 13, 4, 16),
            Block.makeCuboidShape(3.949747468305832, 2.5, 7.742640687119284, 6.949747468305834, 3.5, 8.742640687119284),
            Block.makeCuboidShape(2.9497474683058336, 2.5, 6.742640687119284, 5.949747468305835, 3.5, 8.742640687119284),
            Block.makeCuboidShape(5.949747468305834, 2.5, 4.742640687119284, 9.949747468305835, 4.5, 8.742640687119284),
            Block.makeCuboidShape(11.449747468305834, 2, 2.742640687119284, 13.449747468305834, 7, 4.742640687119286),
            Block.makeCuboidShape(9.449747468305834, 2, 2.742640687119284, 11.449747468305834, 4, 4.742640687119286),
            Block.makeCuboidShape(12.449747468305834, 2, 5.742640687119284, 14.449747468305834, 4, 7.742640687119286),
            Block.makeCuboidShape(0.44974746830583356, 2, 11.742640687119284, 5.449747468305834, 4, 13.742640687119286)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(11.257359312880714, 1, 11.449747468305834, 14.257359312880716, 4, 14.449747468305834),
            Block.makeCuboidShape(0, 0, 0, 16, 3, 16),
            Block.makeCuboidShape(1, 2, 4, 4, 4, 6),
            Block.makeCuboidShape(7, 0, 5, 10, 2, 7),
            Block.makeCuboidShape(4, 0, 3, 7, 2, 5),
            Block.makeCuboidShape(9, 0, 1, 13, 5, 6),
            Block.makeCuboidShape(1, 0, 1, 5, 4, 6),
            Block.makeCuboidShape(3.615235472713387, 1, 9.065658096802874, 7.615235472713387, 6, 14.065658096802874),
            Block.makeCuboidShape(7.615235472713387, 1.0000000000000009, 9.065658096802874, 11.615235472713387, 4.000000000000001, 13.065658096802874),
            Block.makeCuboidShape(1.1152354727133869, 1.5, 7.065658096802874, 6.115235472713387, 3.5, 12.065658096802874),
            Block.makeCuboidShape(5, 2, 5, 6, 4, 7),
            Block.makeCuboidShape(12.257359312880714, 2, 7.449747468305834, 14.257359312880716, 4, 11.449747468305834),
            Block.makeCuboidShape(11.257359312880716, 1.9999999999999982, 8.449747468305834, 12.257359312880716, 5, 9.449747468305834),
            Block.makeCuboidShape(0, 0, 8, 4, 4, 13),
            Block.makeCuboidShape(7.257359312880716, 2.5, 3.949747468305832, 8.257359312880716, 3.5, 6.949747468305834),
            Block.makeCuboidShape(7.257359312880716, 2.5, 2.9497474683058336, 9.257359312880716, 3.5, 5.949747468305835),
            Block.makeCuboidShape(7.257359312880716, 2.5, 5.949747468305834, 11.257359312880716, 4.5, 9.949747468305835),
            Block.makeCuboidShape(11.257359312880714, 2, 11.449747468305834, 13.257359312880716, 7, 13.449747468305834),
            Block.makeCuboidShape(11.257359312880714, 2, 9.449747468305834, 13.257359312880716, 4, 11.449747468305834),
            Block.makeCuboidShape(8.257359312880714, 2, 12.449747468305834, 10.257359312880716, 4, 14.449747468305834),
            Block.makeCuboidShape(2.2573593128807143, 2, 0.44974746830583356, 4.257359312880716, 4, 5.449747468305834)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public EmptyMound(Properties properties){
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world){
        return ModTileEntities.MOUND_TILE_ENTITY.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state){
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn,
                               BlockPos pos, ISelectionContext context){
        switch(state.get(FACING)){
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context){
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot){
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn){
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new MoundTile();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn,
                                             BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.getTileEntity(pos) instanceof MoundTile) {
            if(player.getHeldItemMainhand().getItem() instanceof SwordItem) {
                Direction oldDirection = worldIn.getBlockState(pos)
                        .get(HorizontalBlock.HORIZONTAL_FACING);
                FullMound.SwordMound swordMound =
                        (FullMound.SwordMound)ModBlocks.WOODEN_SWORD_MOUND.get().getBlock();;
                switch(oldDirection){
                    case EAST:
                        worldIn.setBlockState(pos,
                                swordMound.rotate(swordMound.getDefaultState(),
                                        Rotation.CLOCKWISE_90));
                        break;
                    case SOUTH:
                        worldIn.setBlockState(pos,
                                swordMound.rotate(swordMound.getDefaultState(),
                                        Rotation.CLOCKWISE_180));
                        break;
                    case WEST:
                        worldIn.setBlockState(pos,
                                swordMound.rotate(swordMound.getDefaultState(),
                                        Rotation.COUNTERCLOCKWISE_90));
                        break;
                    default:
                        worldIn.setBlockState(pos,
                                swordMound.getDefaultState());
                        break;
                }
                return worldIn.getBlockState(pos).getBlock().onBlockActivated(state,
                        worldIn,pos,player,handIn,hit);
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
