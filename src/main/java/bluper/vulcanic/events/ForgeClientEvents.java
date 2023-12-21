package bluper.vulcanic.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.client.overlay.VOverlays;

@EventBusSubscriber(modid = Vulcanic.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {
	@SubscribeEvent
	public static void
		clientTick(final net.neoforged.neoforge.event.TickEvent.ClientTickEvent event) {
		VOverlays.ThermometerOverlay.updateRenderInfo();
	}
}
