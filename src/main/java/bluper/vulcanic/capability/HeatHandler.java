package bluper.vulcanic.capability;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class HeatHandler implements IHeatHandler, INBTSerializable<CompoundTag> {

	protected float temp;
	protected float specHeat;

	@Override
	public float getTemp() {
		return temp;
	}

	@Override
	public float getSpecHeat() {
		return specHeat;
	}

	@Override
	public void setTemp(float temp) {
		this.temp = temp;
	}

	@Override
	public CompoundTag serializeNBT() {
		CompoundTag nbt = new CompoundTag();
		nbt.putFloat("Temp", getTemp());
		return nbt;
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		float nbtTemp = nbt.getFloat("Temp");
		if (nbtTemp != 0) temp = nbtTemp;
	}
}
