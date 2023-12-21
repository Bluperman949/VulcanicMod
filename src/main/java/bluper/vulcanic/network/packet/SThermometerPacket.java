package bluper.vulcanic.network.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.neoforged.neoforge.network.NetworkEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import bluper.vulcanic.capability.VCapabilities;
import bluper.vulcanic.capability.heat.IHeatHandler;
import bluper.vulcanic.network.VPacketHandler;

public class SThermometerPacket {

	final BlockPos pos;
	final Direction dir;

	public SThermometerPacket(BlockPos pos, Direction dir) {
		this.pos = pos;
		this.dir = dir;
	}

	public static void encode(SThermometerPacket packet, FriendlyByteBuf buf) {
		buf.writeBlockPos(packet.pos);
		buf.writeEnum(packet.dir);
	}

	public static SThermometerPacket decode(FriendlyByteBuf buf) {
		return new SThermometerPacket(buf.readBlockPos(), buf.readEnum(Direction.class));
	}

	public static void handle(SThermometerPacket packet, NetworkEvent.Context ctx) {
		ctx.enqueueWork(() -> {
			IHeatHandler heatHandler =
				ctx.getSender().level().getCapability(VCapabilities.HEAT_HANDLER_BLOCK, packet.pos, packet.dir);
			VPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(ctx::getSender),
				new CThermometerPacket(heatHandler.getDegrees()));
		});
		ctx.setPacketHandled(true);
	}
}
