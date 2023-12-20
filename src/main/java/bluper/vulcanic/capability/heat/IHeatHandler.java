package bluper.vulcanic.capability.heat;

public interface IHeatHandler {
	public float getJoules();

	public float getSpecHeat();

	public float getDegrees();

	public void setJoules(float joules);

	public void addJoules(float joules);

	public void setDegrees(float degrees);

	public void addDegrees(float degrees);

	public static void conduct(IHeatHandler a, IHeatHandler b) {
		float transfer = (a.getJoules() - b.getJoules()) * 0.001f;
		a.addJoules(-transfer);
		b.addJoules(transfer);
	}
}
