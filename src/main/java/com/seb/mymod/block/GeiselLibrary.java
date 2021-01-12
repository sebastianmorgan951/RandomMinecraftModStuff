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
import java.util.stream.Stream;

public class GeiselLibrary extends Block {

    public static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(11.4, 7.3, 11.4, 13.4, 7.55, 13.4),
            Block.makeCuboidShape(3, 0, 4.8, 3.8, 2, 5.2),
            Block.makeCuboidShape(12.2, 0, 4.8, 13, 2, 5.2),
            Block.makeCuboidShape(11.2, 2, 4.8, 13, 2.5, 5.2),
            Block.makeCuboidShape(12.3, 2.75, 4.8, 17.3, 3.25, 5.2),
            Block.makeCuboidShape(3, 2, 4.8, 4.800000000000001, 2.5, 5.2),
            Block.makeCuboidShape(2.3499999999999996, 1.9000000000000004, 4.8, 2.8499999999999996, 6.9, 5.2),
            Block.makeCuboidShape(11.2, 2, 10.8, 13, 2.5, 11.2),
            Block.makeCuboidShape(12.3, 2.75, 10.8, 17.3, 3.25, 11.2),
            Block.makeCuboidShape(10.8, 2, 8.8, 13, 2.5, 9.2),
            Block.makeCuboidShape(12.3, 2.75, 8.8, 17.3, 3.25, 9.2),
            Block.makeCuboidShape(10.8, 2, 6.800000000000001, 13, 2.5, 7.199999999999999),
            Block.makeCuboidShape(12.3, 2.75, 6.800000000000001, 17.3, 3.25, 7.199999999999999),
            Block.makeCuboidShape(3, 2, 10.8, 4.800000000000001, 2.5, 11.2),
            Block.makeCuboidShape(2.3499999999999996, 1.9000000000000004, 10.8, 2.8499999999999996, 6.9, 11.2),
            Block.makeCuboidShape(3, 2, 8.8, 5.2, 2.5, 9.2),
            Block.makeCuboidShape(2.3499999999999996, 1.9000000000000004, 8.8, 2.8499999999999996, 6.9, 9.2),
            Block.makeCuboidShape(3, 2, 6.800000000000001, 5.2, 2.5, 7.199999999999999),
            Block.makeCuboidShape(2.3499999999999996, 1.9000000000000004, 6.800000000000001, 2.8499999999999996, 6.9, 7.199999999999999),
            Block.makeCuboidShape(3, 0, 6.8, 3.8, 2, 7.2),
            Block.makeCuboidShape(12.2, 0, 6.8, 13, 2, 7.2),
            Block.makeCuboidShape(3, 0, 8.8, 3.8, 2, 9.200000000000001),
            Block.makeCuboidShape(12.2, 0, 8.8, 13, 2, 9.200000000000001),
            Block.makeCuboidShape(3, 0, 10.8, 3.8, 2, 11.200000000000001),
            Block.makeCuboidShape(12.2, 0, 10.8, 13, 2, 11.200000000000001),
            Block.makeCuboidShape(4.8, 0, 3, 5.2, 2, 3.8),
            Block.makeCuboidShape(4.8, 0, 12.2, 5.2, 2, 13),
            Block.makeCuboidShape(6.8, 0, 3, 7.2, 2, 3.8),
            Block.makeCuboidShape(6.8, 0, 12.2, 7.2, 2, 13),
            Block.makeCuboidShape(8.8, 0, 3, 9.2, 2, 3.8),
            Block.makeCuboidShape(8.8, 0, 12.2, 9.2, 2, 13),
            Block.makeCuboidShape(10.8, 0, 3, 11.2, 2, 3.8),
            Block.makeCuboidShape(10.8, 2, 3, 11.2, 2.5, 5.2),
            Block.makeCuboidShape(10.8, 4.75, -0.5, 11.2, 5.25, 4.5),
            Block.makeCuboidShape(8.8, 2, 3, 9.2, 2.5, 5.2),
            Block.makeCuboidShape(8.8, 4.75, -0.5, 9.2, 5.25, 4.5),
            Block.makeCuboidShape(6.800000000000001, 2, 3, 7.199999999999999, 2.5, 5.2),
            Block.makeCuboidShape(6.800000000000001, 4.75, -0.5, 7.199999999999999, 5.25, 4.5),
            Block.makeCuboidShape(4.800000000000001, 2, 3, 5.199999999999999, 2.5, 5.2),
            Block.makeCuboidShape(4.800000000000001, 4.75, -0.5, 5.199999999999999, 5.25, 4.5),
            Block.makeCuboidShape(10.8, 2, 10.8, 11.2, 2.5, 13),
            Block.makeCuboidShape(10.8, 2.75, 12.3, 11.2, 3.25, 17.3),
            Block.makeCuboidShape(8.8, 2, 10.8, 9.2, 2.5, 13),
            Block.makeCuboidShape(8.8, 2.75, 12.3, 9.2, 3.25, 17.3),
            Block.makeCuboidShape(6.800000000000001, 2, 10.8, 7.199999999999999, 2.5, 13),
            Block.makeCuboidShape(6.800000000000001, 2.75, 12.3, 7.199999999999999, 3.25, 17.3),
            Block.makeCuboidShape(4.800000000000001, 2, 10.8, 5.199999999999999, 2.5, 13),
            Block.makeCuboidShape(4.800000000000001, 2.75, 12.3, 5.199999999999999, 3.25, 17.3),
            Block.makeCuboidShape(10.8, 0, 12.2, 11.2, 2, 13),
            Block.makeCuboidShape(5, 0, 5, 7, 2.5, 7),
            Block.makeCuboidShape(7, 0, 6, 9, 2.5, 7),
            Block.makeCuboidShape(7, 0, 9, 9, 2.5, 10),
            Block.makeCuboidShape(5, 0, 9, 7, 2.5, 11),
            Block.makeCuboidShape(9, 0, 9, 11, 2.5, 11),
            Block.makeCuboidShape(9, 0, 5, 11, 2.5, 7),
            Block.makeCuboidShape(6, 0, 7, 10, 2.5, 9),
            Block.makeCuboidShape(3.2, 2.5, 4.6, 12.8, 2.75, 11.399999999999999),
            Block.makeCuboidShape(3.2, 10.5, 4.6, 12.8, 10.75, 11.399999999999999),
            Block.makeCuboidShape(3.4399999999999995, 9.15, 4.77, 12.56, 10.55, 11.229999999999999),
            Block.makeCuboidShape(3.4399999999999995, 2.7, 4.77, 12.56, 4.1000000000000005, 11.229999999999999),
            Block.makeCuboidShape(1.5999999999999996, 4.1, 4.6, 14.399999999999999, 4.35, 11.399999999999999),
            Block.makeCuboidShape(1.5999999999999996, 8.9, 4.6, 14.399999999999999, 9.15, 11.399999999999999),
            Block.makeCuboidShape(1.8239999999999998, 7.549999999999999, 4.718999999999999, 14.175999999999998, 8.95, 11.280999999999999),
            Block.makeCuboidShape(1.8239999999999998, 4.299999999999999, 4.718999999999999, 14.175999999999998, 5.699999999999999, 11.280999999999999),
            Block.makeCuboidShape(0, 5.7, 4.6, 16, 5.95, 11.399999999999999),
            Block.makeCuboidShape(0.2400000000000002, 5.95375, 4.702, 15.76, 7.35375, 11.297999999999998),
            Block.makeCuboidShape(0, 7.3, 4.6, 16, 7.55, 11.399999999999999),
            Block.makeCuboidShape(4.599999999999999, 2.5, 3.2, 11.399999999999999, 2.75, 4.6),
            Block.makeCuboidShape(4.599999999999999, 10.5, 3.2, 11.399999999999999, 10.75, 4.6),
            Block.makeCuboidShape(4.77, 9.15, 3.4400000000000004, 11.229999999999999, 10.55, 4.77),
            Block.makeCuboidShape(4.77, 2.7, 3.4400000000000004, 11.229999999999999, 4.1000000000000005, 4.77),
            Block.makeCuboidShape(4.599999999999999, 4.1, 1.5999999999999996, 11.399999999999999, 4.35, 4.6),
            Block.makeCuboidShape(4.599999999999999, 8.9, 1.5999999999999996, 11.399999999999999, 9.15, 4.6),
            Block.makeCuboidShape(4.718999999999999, 7.549999999999999, 1.8239999999999998, 11.280999999999999, 8.95, 4.718999999999999),
            Block.makeCuboidShape(4.718999999999999, 4.299999999999999, 1.8239999999999998, 11.280999999999999, 5.699999999999999, 4.718999999999999),
            Block.makeCuboidShape(4.599999999999999, 5.7, 0, 11.399999999999999, 5.95, 4.6),
            Block.makeCuboidShape(4.701999999999999, 5.95375, 0.2400000000000002, 11.297999999999998, 7.35375, 4.702),
            Block.makeCuboidShape(4.599999999999999, 7.3, 0, 11.399999999999999, 7.55, 4.6),
            Block.makeCuboidShape(4.599999999999999, 2.5, 11.4, 11.399999999999999, 2.75, 12.8),
            Block.makeCuboidShape(4.599999999999999, 10.5, 11.4, 11.399999999999999, 10.75, 12.8),
            Block.makeCuboidShape(4.77, 9.15, 11.23, 11.229999999999999, 10.55, 12.56),
            Block.makeCuboidShape(4.77, 2.7, 11.23, 11.229999999999999, 4.1000000000000005, 12.56),
            Block.makeCuboidShape(4.599999999999999, 4.1, 11.4, 11.399999999999999, 4.35, 14.4),
            Block.makeCuboidShape(4.599999999999999, 8.9, 11.4, 11.399999999999999, 9.15, 14.4),
            Block.makeCuboidShape(4.718999999999999, 7.549999999999999, 11.281, 11.280999999999999, 8.95, 14.176),
            Block.makeCuboidShape(4.718999999999999, 4.299999999999999, 11.281, 11.280999999999999, 5.699999999999999, 14.176),
            Block.makeCuboidShape(4.599999999999999, 5.7, 11.4, 11.399999999999999, 5.95, 16),
            Block.makeCuboidShape(4.701999999999999, 5.95375, 11.298, 11.297999999999998, 7.35375, 15.76),
            Block.makeCuboidShape(4.599999999999999, 7.3, 11.4, 11.399999999999999, 7.55, 16),
            Block.makeCuboidShape(4.099999999999999, 2.5, 11.4, 4.599999999999999, 2.75, 11.9),
            Block.makeCuboidShape(4.099999999999999, 10.5, 11.4, 4.599999999999999, 10.75, 11.9),
            Block.makeCuboidShape(4.294999999999999, 9.15, 11.23, 4.77, 10.55, 11.705),
            Block.makeCuboidShape(4.294999999999999, 2.7, 11.23, 4.77, 4.1000000000000005, 11.705),
            Block.makeCuboidShape(3.2, 4.1, 11.4, 4.6, 4.35, 12.8),
            Block.makeCuboidShape(3.2, 8.9, 11.4, 4.6, 9.15, 12.8),
            Block.makeCuboidShape(3.3680000000000003, 7.549999999999999, 11.281, 4.718999999999999, 8.95, 12.632000000000001),
            Block.makeCuboidShape(3.3680000000000003, 4.299999999999999, 11.281, 4.718999999999999, 5.699999999999999, 12.632000000000001),
            Block.makeCuboidShape(2.5999999999999988, 5.7, 11.4, 4.599999999999999, 5.95, 13.4),
            Block.makeCuboidShape(2.7619999999999987, 5.95375, 11.298, 4.701999999999999, 7.35375, 13.238),
            Block.makeCuboidShape(2.5999999999999988, 7.3, 11.4, 4.599999999999999, 7.55, 13.4),
            Block.makeCuboidShape(3.3499999999999988, 5.7, 13.4, 4.599999999999999, 5.95, 14.65),
            Block.makeCuboidShape(3.4894999999999987, 5.95375, 13.238, 4.701999999999999, 7.35375, 14.4505),
            Block.makeCuboidShape(3.3499999999999988, 7.3, 13.4, 4.599999999999999, 7.55, 14.65),
            Block.makeCuboidShape(1.3499999999999988, 5.7, 11.4, 2.5999999999999988, 5.95, 12.65),
            Block.makeCuboidShape(1.5494999999999992, 5.95375, 11.298, 2.7619999999999987, 7.35375, 12.5105),
            Block.makeCuboidShape(1.3499999999999988, 7.3, 11.4, 2.5999999999999988, 7.55, 12.65),
            Block.makeCuboidShape(1.3499999999999988, 5.7, 3.3499999999999996, 2.5999999999999988, 5.95, 4.6),
            Block.makeCuboidShape(1.5494999999999992, 5.95375, 3.4894999999999996, 2.7619999999999987, 7.35375, 4.702),
            Block.makeCuboidShape(1.3499999999999988, 7.3, 3.3499999999999996, 2.5999999999999988, 7.55, 4.6),
            Block.makeCuboidShape(3.3499999999999988, 5.7, 1.3499999999999996, 4.599999999999999, 5.95, 2.5999999999999996),
            Block.makeCuboidShape(3.4894999999999987, 5.95375, 1.5495, 4.701999999999999, 7.35375, 2.7619999999999996),
            Block.makeCuboidShape(3.3499999999999988, 7.3, 1.3499999999999996, 4.599999999999999, 7.55, 2.5999999999999996),
            Block.makeCuboidShape(11.4, 5.7, 1.3499999999999996, 12.65, 5.95, 2.5999999999999996),
            Block.makeCuboidShape(11.298, 5.95375, 1.5495, 12.5105, 7.35375, 2.7619999999999996),
            Block.makeCuboidShape(11.4, 7.3, 1.3499999999999996, 12.65, 7.55, 2.5999999999999996),
            Block.makeCuboidShape(13.4, 5.7, 3.3499999999999996, 14.65, 5.95, 4.6),
            Block.makeCuboidShape(13.238, 5.95375, 3.4894999999999996, 14.4505, 7.35375, 4.702),
            Block.makeCuboidShape(13.4, 7.3, 3.3499999999999996, 14.65, 7.55, 4.6),
            Block.makeCuboidShape(13.4, 5.7, 11.4, 14.65, 5.95, 12.65),
            Block.makeCuboidShape(13.238, 5.95375, 11.298, 14.4505, 7.35375, 12.5105),
            Block.makeCuboidShape(13.4, 7.3, 11.4, 14.65, 7.55, 12.65),
            Block.makeCuboidShape(11.4, 5.7, 13.4, 12.65, 5.95, 14.65),
            Block.makeCuboidShape(11.298, 5.95375, 13.238, 12.5105, 7.35375, 14.4505),
            Block.makeCuboidShape(11.4, 7.3, 13.4, 12.65, 7.55, 14.65),
            Block.makeCuboidShape(4.099999999999999, 2.5, 4.1, 4.599999999999999, 2.75, 4.6),
            Block.makeCuboidShape(4.099999999999999, 10.5, 4.1, 4.599999999999999, 10.75, 4.6),
            Block.makeCuboidShape(4.294999999999999, 9.15, 4.295, 4.77, 10.55, 4.77),
            Block.makeCuboidShape(4.294999999999999, 2.7, 4.295, 4.77, 4.1000000000000005, 4.77),
            Block.makeCuboidShape(3.2, 4.1, 3.2, 4.6, 4.35, 4.6000000000000005),
            Block.makeCuboidShape(3.2, 8.9, 3.2, 4.6, 9.15, 4.6000000000000005),
            Block.makeCuboidShape(3.3680000000000003, 7.549999999999999, 3.3680000000000003, 4.718999999999999, 8.95, 4.719000000000001),
            Block.makeCuboidShape(3.3680000000000003, 4.299999999999999, 3.3680000000000003, 4.718999999999999, 5.699999999999999, 4.719000000000001),
            Block.makeCuboidShape(2.5999999999999988, 5.7, 2.5999999999999996, 4.599999999999999, 5.95, 4.6),
            Block.makeCuboidShape(2.7619999999999987, 5.95375, 2.7619999999999996, 4.701999999999999, 7.35375, 4.702),
            Block.makeCuboidShape(2.5999999999999988, 7.3, 2.5999999999999996, 4.599999999999999, 7.55, 4.6),
            Block.makeCuboidShape(11.4, 2.5, 4.1, 11.9, 2.75, 4.6),
            Block.makeCuboidShape(11.4, 10.5, 4.1, 11.9, 10.75, 4.6),
            Block.makeCuboidShape(11.23, 9.15, 4.295, 11.705, 10.55, 4.77),
            Block.makeCuboidShape(11.23, 2.7, 4.295, 11.705, 4.1000000000000005, 4.77),
            Block.makeCuboidShape(11.4, 4.1, 3.2, 12.8, 4.35, 4.6000000000000005),
            Block.makeCuboidShape(11.4, 8.9, 3.2, 12.8, 9.15, 4.6000000000000005),
            Block.makeCuboidShape(11.281, 7.549999999999999, 3.3680000000000003, 12.632000000000001, 8.95, 4.719000000000001),
            Block.makeCuboidShape(11.281, 4.299999999999999, 3.3680000000000003, 12.632000000000001, 5.699999999999999, 4.719000000000001),
            Block.makeCuboidShape(11.4, 5.7, 2.5999999999999996, 13.4, 5.95, 4.6),
            Block.makeCuboidShape(11.298, 5.95375, 2.7619999999999996, 13.238, 7.35375, 4.702),
            Block.makeCuboidShape(11.4, 7.3, 2.5999999999999996, 13.4, 7.55, 4.6),
            Block.makeCuboidShape(11.4, 2.5, 11.4, 11.9, 2.75, 11.9),
            Block.makeCuboidShape(11.4, 10.5, 11.4, 11.9, 10.75, 11.9),
            Block.makeCuboidShape(11.23, 9.15, 11.23, 11.705, 10.55, 11.705),
            Block.makeCuboidShape(11.23, 2.7, 11.23, 11.705, 4.1000000000000005, 11.705),
            Block.makeCuboidShape(11.4, 4.1, 11.4, 12.8, 4.35, 12.8),
            Block.makeCuboidShape(11.4, 8.9, 11.4, 12.8, 9.15, 12.8),
            Block.makeCuboidShape(11.281, 7.549999999999999, 11.281, 12.632000000000001, 8.95, 12.632000000000001),
            Block.makeCuboidShape(11.281, 4.299999999999999, 11.281, 12.632000000000001, 5.699999999999999, 12.632000000000001),
            Block.makeCuboidShape(11.4, 5.7, 11.4, 13.4, 5.95, 13.4),
            Block.makeCuboidShape(11.298, 5.95375, 11.298, 13.238, 7.35375, 13.238)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public GeiselLibrary(Properties properties){
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn,
                               BlockPos pos, ISelectionContext context) {
        return SHAPE;
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

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn,
                                             BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        return ActionResultType.PASS;
    }
}
