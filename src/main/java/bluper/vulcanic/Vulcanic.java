package bluper.vulcanic;

import net.minecraft.resources.ResourceLocation;

import bluper.vulcanic.registry.registries.VBlocks;
import bluper.vulcanic.registry.registries.VItems;

@net.neoforged.fml.common.Mod(Vulcanic.MODID)
public class Vulcanic {
	public static final String MODID = "vulcanic";

	public Vulcanic() {
		net.neoforged.bus.api.IEventBus bus =
			net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();
		VBlocks.registerAll(bus);
		VItems.registerAll(bus);
	}

	public static ResourceLocation createRL(String path) {
		return new ResourceLocation(MODID, path);
	}
}
