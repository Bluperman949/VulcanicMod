package bluper.vulcanic.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

import bluper.vulcanic.Vulcanic;

public class VDamageSources {
	private static ResourceKey<DamageType> register(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, Vulcanic.createRL(name));
	}

	public static final ResourceKey<DamageType> MACHINE_EXPLOSION = register("machine_explosion");
}
