package bluper.vulcanic.world;

public class MachineTier {

	public static final MachineTier STONE = new MachineTier(1.1f, 1900f / 1.1f);
	public static final MachineTier TUFFCRETE = new MachineTier(1f, 2000f);

	public final float specHeat;
	public final float explodePoint;

	private MachineTier(float specHeat, float explodePoint) {
		this.specHeat = specHeat;
		this.explodePoint = explodePoint;
	}
}
