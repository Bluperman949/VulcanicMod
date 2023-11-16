package bluper.vulcanic;

@net.neoforged.fml.common.Mod(Vulcanic.MODID)
public class Vulcanic {
	public static final String MODID = "vulcanic";

	public Vulcanic() {
		net.neoforged.bus.api.IEventBus bus = net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();
	}
}
