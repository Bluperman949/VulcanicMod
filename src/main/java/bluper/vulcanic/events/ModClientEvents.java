package bluper.vulcanic.events;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.registry.registries.VItems;

@EventBusSubscriber(modid = Vulcanic.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
	@SubscribeEvent
	public static void buildCreativeModeTabContents(
		final net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent event) {
		ResourceKey<CreativeModeTab> tab = event.getTabKey();
		if (VItems.TAB_MAP.containsKey(tab))
			VItems.TAB_MAP.get(tab).forEach(i -> event.accept(i.get()));
	}

	@SubscribeEvent
	public static void registerGuiOverlays(
		final net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent event) {
		
	}
}
