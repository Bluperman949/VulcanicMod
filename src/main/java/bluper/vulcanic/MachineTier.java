package bluper.vulcanic;

public class MachineTier {

	public static final MachineTier TUFFCRETE = new MachineTier(1f, 9000f);

	public final float specHeat;
	public final float explodePoint;

	private MachineTier(float specHeat, float explodePoint) {
		this.specHeat = specHeat;
		this.explodePoint = explodePoint;
	}
}
