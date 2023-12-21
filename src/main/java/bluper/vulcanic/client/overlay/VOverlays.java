package bluper.vulcanic.client.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.client.gui.overlay.ExtendedGui;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

import bluper.vulcanic.capability.VCapabilities;
import bluper.vulcanic.network.VPacketHandler;
import bluper.vulcanic.network.packet.SThermometerPacket;
import bluper.vulcanic.util.Temperature;

public class VOverlays {
	private static final Minecraft MC = Minecraft.getInstance();

	public static class ThermometerOverlay implements IGuiOverlay {
		public static float temp = 0f;
		private static boolean shouldRender = false;

		private ThermometerOverlay() {
		}

		@Override
		public void render(ExtendedGui gui, GuiGraphics guiGraphics, float partialTick,
			int screenWidth, int screenHeight) {
			if (shouldRender) guiGraphics.drawString(MC.font,
				String.format("%.2f\u00B0C", temp - Temperature.FREEZING), screenWidth / 2 + 10,
				screenHeight / 2, 0xffffffff);
		}

		public static void updateRenderInfo() {
			shouldRender = false;
			if (MC.hitResult instanceof BlockHitResult blockHitResult) {
				if (MC.level == null) return;
				if (MC.level.getCapability(VCapabilities.HEAT_HANDLER_BLOCK,
					blockHitResult.getBlockPos(), blockHitResult.getDirection()) == null) return;
				shouldRender =
					!(MC.options.hideGui || MC.gameMode.getPlayerMode() == GameType.SPECTATOR);
				VPacketHandler.INSTANCE.sendToServer(new SThermometerPacket(
					blockHitResult.getBlockPos(), blockHitResult.getDirection()));
			}
		}
	}

	public static final IGuiOverlay THERMOMETER = new ThermometerOverlay();
}
