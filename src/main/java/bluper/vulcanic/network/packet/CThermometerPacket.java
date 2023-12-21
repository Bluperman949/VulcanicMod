package bluper.vulcanic.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.network.NetworkEvent;

import bluper.vulcanic.client.overlay.VOverlays;

public class CThermometerPacket {

	final float temp;

	public CThermometerPacket(float temp) {
		this.temp = temp;
	}

	public static void encode(CThermometerPacket packet, FriendlyByteBuf buf) {
		buf.writeFloat(packet.temp);
	}

	public static CThermometerPacket decode(FriendlyByteBuf buf) {
		return new CThermometerPacket(buf.readFloat());
	}

	public static void handle(CThermometerPacket packet, NetworkEvent.Context ctx) {
		ctx.enqueueWork(() -> {
			VOverlays.ThermometerOverlay.temp = packet.temp;
		});
		ctx.setPacketHandled(true);
	}
}
