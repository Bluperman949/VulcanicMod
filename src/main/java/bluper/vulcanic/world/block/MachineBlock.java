package bluper.vulcanic.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;

import bluper.vulcanic.util.Temperature;
import bluper.vulcanic.world.VDamageTypes;
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

	@Override
	public void stepOn(Level level, BlockPos pos, BlockState blockState, Entity entity) {
		float degrees =
			((MachineBlockEntity) level.getBlockEntity(pos)).getHeatStorage().getDegrees();
		if (degrees > Temperature.DAMAGING && !entity.hurtMarked) entity.hurt(
			new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
				.getHolderOrThrow(VDamageTypes.MACHINE_HOT_FLOOR)),
			(degrees - Temperature.DAMAGING) / 200f);
	}
}
