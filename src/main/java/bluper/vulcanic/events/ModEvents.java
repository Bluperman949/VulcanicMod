package bluper.vulcanic.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.capability.VCapabilities;
import bluper.vulcanic.registry.registries.VBlockEntityTypes;

@Mod.EventBusSubscriber(modid = Vulcanic.MODID, bus = Bus.MOD)
public class ModEvents {
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(VCapabilities.HEAT_HANDLER_BLOCK,
			VBlockEntityTypes.HEATSTONE.get(), (be, side) -> {
				return be.getHeatStorage();
			});
	}

}
