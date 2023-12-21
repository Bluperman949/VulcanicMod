package bluper.vulcanic.registry.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.world.entity.ExplosionSourceEntity;

public class VEntityTypes {
	static final DeferredRegister<EntityType<?>> R =
		DeferredRegister.create(Registries.ENTITY_TYPE, Vulcanic.MODID);

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}

	public static final DeferredHolder<EntityType<?>,
		EntityType<ExplosionSourceEntity>> EXPLOSION_SOURCE = R.register("explosion_source",
			() -> EntityType.Builder.of(ExplosionSourceEntity::new, MobCategory.MISC).build(""));
}
