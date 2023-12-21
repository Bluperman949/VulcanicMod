package bluper.vulcanic.world.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

// This is hacky and disgusting, but I have no clue what else to do.
public final class ExplosionSourceEntity extends Entity {
	public ExplosionSourceEntity(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag nbt) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag nbt) {
	}
}