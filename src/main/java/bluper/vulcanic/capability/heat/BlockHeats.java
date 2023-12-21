package bluper.vulcanic.capability.heat;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import bluper.vulcanic.util.Temperature;

import it.unimi.dsi.fastutil.floats.Float2ObjectArrayMap;
import it.unimi.dsi.fastutil.floats.Float2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;

public class BlockHeats {
	private static final Object2ObjectMap<Block, IHeatHandler> HEATS =
		new Object2ObjectArrayMap<>();
	private static final Float2ObjectMap<IHeatHandler> BIOME_HANDLER_CACHE =
		new Float2ObjectArrayMap<>();

	static {
		HEATS.put(Blocks.PACKED_ICE, new ImmutableHeatSource(Temperature.FREEZING));
		HEATS.put(Blocks.LAVA, new ImmutableHeatSource(Temperature.FREEZING + 1000));

		// TEMPORARY, FOR TESTING!!
		HEATS.put(Blocks.ORANGE_GLAZED_TERRACOTTA,
			new ImmutableHeatSource(9000000));
	}

	public static IHeatHandler get(Level level, BlockPos pos) {
		return HEATS.getOrDefault(level.getBlockState(pos).getBlock(),
			BIOME_HANDLER_CACHE.computeIfAbsent(
				Temperature.fromBiome(level.getBiome(pos).value().getBaseTemperature()),
				ImmutableHeatSource::new));
	}
}