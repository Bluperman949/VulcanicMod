package bluper.vulcanic.world.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import bluper.vulcanic.registry.registries.VBlockEntityTypes;
import bluper.vulcanic.world.MachineTier;

public class HeatstoneBlockEntity extends MachineBlockEntity {
	public HeatstoneBlockEntity(BlockPos pos, BlockState blockState, MachineTier tier) {
		super(VBlockEntityTypes.HEATSTONE.get(), pos, blockState, tier);
	}
}
