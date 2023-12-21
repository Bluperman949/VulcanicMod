package bluper.vulcanic.capability.heat;

public class ImmutableHeatSource implements IHeatHandler {

	private final float degrees;

	public ImmutableHeatSource(float degrees) {
		this.degrees = degrees;
	}

	@Override
	public float getJoules() {
		return degrees;
	}

	@Override
	public float getSpecHeat() {
		return 1;
	}

	@Override
	public float getDegrees() {
		return degrees;
	}

	@Override
	public void setJoules(float joules) {
	}

	@Override
	public void addJoules(float joules) {
	}

	@Override
	public void setDegrees(float degrees) {
	}

	@Override
	public void addDegrees(float degrees) {
	}
}
