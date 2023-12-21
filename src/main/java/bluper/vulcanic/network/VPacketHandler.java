package bluper.vulcanic.network;

import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.simple.SimpleChannel;

import bluper.vulcanic.Vulcanic;
import bluper.vulcanic.network.packet.CThermometerPacket;
import bluper.vulcanic.network.packet.SThermometerPacket;

public class VPacketHandler {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE =
		NetworkRegistry.newSimpleChannel(Vulcanic.createRL("main"), () -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

	public static void init() {
		INSTANCE.registerMessage(0, SThermometerPacket.class, SThermometerPacket::encode,
			SThermometerPacket::decode, SThermometerPacket::handle);
		INSTANCE.registerMessage(1, CThermometerPacket.class, CThermometerPacket::encode,
			CThermometerPacket::decode, CThermometerPacket::handle);
	}
}
