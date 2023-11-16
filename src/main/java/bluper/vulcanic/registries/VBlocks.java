package bluper.vulcanic.registries;

import java.util.function.Supplier;

import bluper.vulcanic.Vulcanic;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class VBlocks {
	static final DeferredRegister<Block> R = DeferredRegister.create(ForgeRegistries.BLOCKS, Vulcanic.MODID);

	<T extends Block> RegistryObject<T> r(final String name, final Supplier<? extends T> sup) {
		RegistryObject<T> b = R.register(name, sup);
		VItems.R.register(name, () -> new net.minecraft.world.item.BlockItem(sup.get(), new net.minecraft.world.item.Item.Properties()));
		return b;
	}

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}
}
