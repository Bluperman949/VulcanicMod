package bluper.vulcanic.registry.registries;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.registry.BlockDeferredRegister;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

public class VBlocks {
	static final BlockDeferredRegister R = new BlockDeferredRegister(DeferredRegister.create(ForgeRegistries.BLOCKS, Vulcanic.MODID), VItems.R);

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}

	public static final RegistryObject<Block> TUFFCRETE = R.register("tuffcrete", () -> new Block(Properties.copy(Blocks.TUFF).strength(3.0F, 7.0F)), CreativeModeTabs.BUILDING_BLOCKS);
}
