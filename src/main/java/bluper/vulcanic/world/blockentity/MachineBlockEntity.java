package bluper.vulcanic.world.blockentity;

import java.util.HashMap;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import bluper.vulcanic.capability.VCapabilities;
import bluper.vulcanic.capability.heat.HeatStorage;
import bluper.vulcanic.capability.heat.IHeatHandler;
import bluper.vulcanic.util.Temperature;
import bluper.vulcanic.world.MachineTier;

public class MachineBlockEntity extends BlockEntity {
	protected final HashMap<Direction, IHeatHandler> positiveNeighborMachines = new HashMap<>();

	protected HeatStorage heatStorage;
	protected MachineTier machineTier;

	public MachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState,
		MachineTier machineTier) {
		super(type, pos, blockState);
		heatStorage = new HeatStorage(machineTier.specHeat);
		this.machineTier = machineTier;
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state,
		MachineBlockEntity be) {
		be.tickConduction();
	}

	protected void tickConduction() {
		for (IHeatHandler i : positiveNeighborMachines.values())
			IHeatHandler.conduct(heatStorage, i);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		pollNeighbors();
		if (heatStorage.getJoules() > 0) return;
		Biome biome = level.getBiome(worldPosition).value();
		float biomeTemp = Temperature.fromBiome(biome.getBaseTemperature());
		heatStorage.setJoules(biomeTemp * machineTier.specHeat);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		heatStorage.deserializeNBT(nbt.getCompound("Heat"));
	}

	@Override
	public void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.put("Heat", heatStorage.serializeNBT());
	}

	public void pollNeighbors() {
		positiveNeighborMachines.clear();
		for (Direction dir : Direction.values()) {
			if (dir.getAxisDirection() == Direction.AxisDirection.POSITIVE) {
				IHeatHandler heatHandler = level.getCapability(VCapabilities.HEAT_HANDLER_BLOCK,
					worldPosition.relative(dir), dir.getOpposite());
				if (heatHandler != null) positiveNeighborMachines.put(dir, heatHandler);
			}
			// TODO: get heat loss for surrounding blocks
		}
	}

	public HeatStorage getHeatStorage() {
		return heatStorage;
	}
}
