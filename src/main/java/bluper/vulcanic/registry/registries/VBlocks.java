package bluper.vulcanic.registry.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.registry.BlockDeferredRegister;
import bluper.vulcanic.world.MachineTier;
import bluper.vulcanic.world.block.HeatstoneBlock;

public class VBlocks {
	static final BlockDeferredRegister R = new BlockDeferredRegister(
		DeferredRegister.create(Registries.BLOCK, Vulcanic.MODID), VItems.R);

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}

	// Simple blocks
	public static final DeferredHolder<Block,
		Block> TUFFCRETE = R.register("tuffcrete",
			() -> new Block(Properties.ofFullCopy(Blocks.TUFF).strength(3.0F, 7.0F)),
			CreativeModeTabs.BUILDING_BLOCKS);

	// BlockEntity blocks
	public static final DeferredHolder<Block, HeatstoneBlock> TUFFCRETE_HEATSTONE =
		R.register("tuffcrete_heatstone", () -> new HeatstoneBlock(MachineTier.TUFFCRETE),
			CreativeModeTabs.FUNCTIONAL_BLOCKS);
}
