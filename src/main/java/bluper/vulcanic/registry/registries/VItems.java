package bluper.vulcanic.registry.registries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import org.apache.commons.compress.utils.Lists;

import com.google.common.collect.Maps;

import bluper.vulcanic.Vulcanic;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class VItems {
	static final DeferredRegister<Item> R = DeferredRegister.create(ForgeRegistries.ITEMS, Vulcanic.MODID);

	public static final HashMap<ResourceKey<CreativeModeTab>, ArrayList<RegistryObject<? extends Item>>> TAB_MAP = Maps.newHashMap();

	static <T extends Item> RegistryObject<T> r(final String name, final Supplier<? extends T> sup, ResourceKey<CreativeModeTab> tab) {
		RegistryObject<T> ret = R.register(name, sup);
		if (!TAB_MAP.containsKey(tab)) TAB_MAP.put(tab, Lists.newArrayList());
		TAB_MAP.get(tab).add(ret);
		return ret;
	}

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}
}
