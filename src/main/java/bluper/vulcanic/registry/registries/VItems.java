package bluper.vulcanic.registry.registries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.Maps;

import bluper.vulcanic.Vulcanic;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class VItems {
	static final DeferredRegister<Item> R =
		DeferredRegister.create(Registries.ITEM, Vulcanic.MODID);

	public static final HashMap<ResourceKey<CreativeModeTab>, ArrayList<DeferredHolder<Item, ? extends Item>>> TAB_MAP =
		Maps.newHashMap();

	static <T extends Item> DeferredHolder<Item, T> r(final String name,
		final Supplier<? extends T> sup, ResourceKey<CreativeModeTab> tab) {

		DeferredHolder<Item, T> ret = R.register(name, sup);
		if (!TAB_MAP.containsKey(tab)) TAB_MAP.put(tab, Lists.newArrayList());
		TAB_MAP.get(tab).add(ret);

		return ret;
	}

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}
}
