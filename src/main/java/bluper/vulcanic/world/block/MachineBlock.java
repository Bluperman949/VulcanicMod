package bluper.vulcanic.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;

import bluper.vulcanic.world.blockentity.MachineBlockEntity;

public abstract class MachineBlock extends Block implements EntityBlock {
	public MachineBlock(Properties prop) {
		super(prop);
	}

	@Override
	public BlockState updateShape(BlockState blockState, Direction dir, BlockState neighborState,
		LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (!level.isClientSide()) ((MachineBlockEntity) level.getBlockEntity(pos)).pollNeighbors();
		return blockState;
	}
}
