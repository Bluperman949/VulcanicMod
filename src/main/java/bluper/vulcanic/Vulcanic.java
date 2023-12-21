package bluper.vulcanic;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.Mod;

import bluper.vulcanic.network.VPacketHandler;
import bluper.vulcanic.registry.registries.VBlockEntityTypes;
import bluper.vulcanic.registry.registries.VBlocks;
import bluper.vulcanic.registry.registries.VEntityTypes;
import bluper.vulcanic.registry.registries.VItems;

@Mod(Vulcanic.MODID)
public class Vulcanic {
	public static final String MODID = "vulcanic";

	public Vulcanic() {
		net.neoforged.bus.api.IEventBus bus =
			net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();
		VBlocks.registerAll(bus);
		VItems.registerAll(bus);
		VBlockEntityTypes.registerAll(bus);
		VEntityTypes.registerAll(bus);
		VPacketHandler.init();
	}

	public static ResourceLocation createRL(String path) {
		return new ResourceLocation(MODID, path);
	}
}
