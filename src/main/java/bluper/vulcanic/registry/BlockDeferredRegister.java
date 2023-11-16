package bluper.vulcanic.registry;

import java.util.function.Supplier;

import org.apache.commons.compress.utils.Lists;

import bluper.vulcanic.registry.registries.VItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryObject;

public class BlockDeferredRegister {
	protected DeferredRegister<Block> blocks;
	protected DeferredRegister<Item> items;

	public BlockDeferredRegister(DeferredRegister<Block> blocks, DeferredRegister<Item> items) {
		this.blocks = blocks;
		this.items = items;
	}

	public <B extends Block> RegistryObject<B> register(String name, Supplier<B> sup, ResourceKey<CreativeModeTab> tab) {
		RegistryObject<B> ret = register(name, sup);
		if (!VItems.TAB_MAP.containsKey(tab)) VItems.TAB_MAP.put(tab, Lists.newArrayList());
		VItems.TAB_MAP.get(tab).add(items.register(name, () -> new BlockItem(ret.get(), new Item.Properties())));
		return ret;
	}

	public <B extends Block> RegistryObject<B> register(String name, Supplier<B> sup) {
		return blocks.register(name, sup);
	}

	public void register(IEventBus bus) {
		blocks.register(bus);
	}
}