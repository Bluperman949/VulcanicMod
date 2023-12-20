package bluper.vulcanic.capability.heat;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class HeatStorage implements IHeatHandler, INBTSerializable<CompoundTag> {
	protected float joules;
	protected final float specHeat;

	public HeatStorage(float specHeat) {
		this.specHeat = specHeat;
	}

	@Override
	public float getJoules() {
		return joules;
	}

	@Override
	public float getSpecHeat() {
		return specHeat;
	}

	@Override
	public float getDegrees() {
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
		nbt.putFloat("Joules", getJoules());
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		float nbtJoules = nbt.getFloat("Joules");
		if (nbtJoules != 0) joules = nbtJoules;
	}
}
