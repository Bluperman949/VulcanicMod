package bluper.vulcanic.capability.heat;

public interface IHeatHandler {
	public float getJoules();

	public float getSpecHeat();

	public float getDegrees();

	public void setJoules(float joules);

	public void addJoules(float joules);

	public void setDegrees(float degrees);

	public void addDegrees(float degrees);

	public default void conductTo(IHeatHandler destination) {
		float transfer =
			(getDegrees() * destination.getSpecHeat() - destination.getDegrees() * getSpecHeat())
				* 0.002f;
		addJoules(-transfer);
		destination.addJoules(transfer);
	}
}
