package bluper.vulcanic.registry.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.world.block.HeatstoneBlock;
import bluper.vulcanic.world.blockentity.HeatstoneBlockEntity;

public class VBlockEntityTypes {
	static final DeferredRegister<BlockEntityType<?>> R =
		DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Vulcanic.MODID);

	public static void registerAll(net.neoforged.bus.api.IEventBus bus) {
		R.register(bus);
	}

	public static final DeferredHolder<BlockEntityType<?>,
		BlockEntityType<HeatstoneBlockEntity>> HEATSTONE = R.register("heatstone",
			() -> BlockEntityType.Builder
				.of((pos, state) -> new HeatstoneBlockEntity(pos, state,
					((HeatstoneBlock) state.getBlock()).tier), VBlocks.TUFFCRETE_HEATSTONE.get())
				.build(null));
}
