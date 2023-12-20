package bluper.vulcanic.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;

import bluper.vulcanic.world.blockentity.MachineBlockEntity;

public abstract class MachineBlock extends Block implements EntityBlock {
	public MachineBlock(Properties prop) {
		super(prop);
	}

	@Override
	public void onNeighborChange(BlockState blockState, LevelReader level, BlockPos myPos,
		BlockPos neighborPos) {
		((MachineBlockEntity) level.getBlockEntity(myPos)).pollNeighbors();
	}
}
