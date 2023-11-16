package bluper.vulcanic.registries;

import bluper.vulcanic.Vulcanic;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;

public class VItems {
	static final DeferredRegister<Item> R = DeferredRegister.create(ForgeRegistries.ITEMS, Vulcanic.MODID);

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}
}
