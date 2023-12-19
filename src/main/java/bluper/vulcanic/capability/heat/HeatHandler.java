package bluper.vulcanic.capability.heat;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class HeatHandler implements IHeatHandler, INBTSerializable<CompoundTag> {

	protected float joules;
	protected float specHeat;

	@Override
	public float getJoules() {
		return joules;
	}

	@Override
	public float getSpecHeat() {
		return specHeat;
	}

	@Override
	public float getTemp() {
		return joules / specHeat;
	}

	@Override
	public void setJoules(float joules) {
		this.joules = joules;
	}

	@Override
	public void addJoules(float joules) {
		this.joules += joules;
	}

	@Override
	public void setDegrees(float degrees) {
		this.joules = degrees * specHeat;
	}

	@Override
	public void addDegrees(float degrees) {
		this.joules += degrees * specHeat;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("Heat", getJoules());
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		float nbtJoules = nbt.getFloat("Heat");
		if (nbtJoules != 0) joules = nbtJoules;
	}
}
