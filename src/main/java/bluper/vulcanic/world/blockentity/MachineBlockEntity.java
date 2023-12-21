package bluper.vulcanic.world.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import bluper.vulcanic.capability.VCapabilities;
import bluper.vulcanic.capability.heat.BlockHeats;
import bluper.vulcanic.capability.heat.HeatStorage;
import bluper.vulcanic.capability.heat.IHeatHandler;
import bluper.vulcanic.registry.registries.VEntityTypes;
import bluper.vulcanic.util.Temperature;
import bluper.vulcanic.world.MachineTier;
import bluper.vulcanic.world.VDamageTypes;
import bluper.vulcanic.world.entity.ExplosionSourceEntity;

public class MachineBlockEntity extends BlockEntity {
	protected final IHeatHandler[] neighborHeatHandlers = new IHeatHandler[6];

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
		if (be.heatStorage.getJoules() > be.machineTier.explodePoint) explode(level, pos);
	}

	protected static void explode(Level level, BlockPos pos) {
		level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NEIGHBORS);
		level.explode(new ExplosionSourceEntity(VEntityTypes.EXPLOSION_SOURCE.get(), level),
			new DamageSource(level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE)
				.getHolderOrThrow(VDamageTypes.MACHINE_EXPLOSION)),
			null, pos.getX(), pos.getY(), pos.getZ(), 2f, true, ExplosionInteraction.BLOCK);
	}

	protected void tickConduction() {
		for (IHeatHandler i : neighborHeatHandlers)
			if (i != null) heatStorage.conductTo(i);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		if (level.isClientSide) return;
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
		for (Direction dir : Direction.values()) {
			int i = dir.get3DDataValue();
			neighborHeatHandlers[i] = null; // yes, this null may remain after the poll is finished
			BlockPos relativePos = worldPosition.relative(dir);
			IHeatHandler heatHandler = level.getCapability(VCapabilities.HEAT_HANDLER_BLOCK,
				relativePos, dir.getOpposite());
			if (heatHandler != null) {
				if (dir.getAxisDirection() == Direction.AxisDirection.POSITIVE)
					neighborHeatHandlers[i] = heatHandler;
				continue;
			}
			neighborHeatHandlers[i] = BlockHeats.get(level, relativePos);
		}
	}

	public HeatStorage getHeatStorage() {
		return heatStorage;
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("Joules", heatStorage.getJoules());
		return nbt;
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void handleUpdateTag(CompoundTag nbt) {
		heatStorage.setJoules(nbt.getFloat("Joules"));
	}
}
