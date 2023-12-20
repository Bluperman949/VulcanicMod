package bluper.vulcanic.capability;

import net.minecraft.core.Direction;
import net.neoforged.neoforge.capabilities.BlockCapability;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.capability.heat.IHeatHandler;

public class VCapabilities {
	public static final BlockCapability<IHeatHandler, Direction> HEAT_HANDLER_BLOCK =
		BlockCapability.createSided(Vulcanic.createRL("heat"), IHeatHandler.class);
}
