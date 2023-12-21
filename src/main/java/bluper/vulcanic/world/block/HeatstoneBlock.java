package bluper.vulcanic.world.block;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import bluper.vulcanic.registry.registries.VBlockEntityTypes;
import bluper.vulcanic.registry.registries.VBlocks;
import bluper.vulcanic.util.UtilCopies;
import bluper.vulcanic.world.MachineTier;
import bluper.vulcanic.world.blockentity.HeatstoneBlockEntity;
import bluper.vulcanic.world.blockentity.MachineBlockEntity;

public class HeatstoneBlock extends MachineBlock {

	public final MachineTier tier;

	public HeatstoneBlock(MachineTier tier) {
		super(Properties.ofFullCopy(VBlocks.TUFFCRETE.get()));
		this.tier = tier;
	}

	@Nullable @Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level,
		BlockState blockState, BlockEntityType<T> type) {
		return type == VBlockEntityTypes.HEATSTONE.get() && !level.isClientSide
			? UtilCopies.createTickerHelper(type, VBlockEntityTypes.HEATSTONE.get(),
				MachineBlockEntity::serverTick)
			: null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
		return new HeatstoneBlockEntity(pos, blockState, tier);
	}
}
