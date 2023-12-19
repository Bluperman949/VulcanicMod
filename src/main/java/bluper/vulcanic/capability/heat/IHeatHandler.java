package bluper.vulcanic.capability.heat;

public interface IHeatHandler {
	public float getJoules();

	public float getSpecHeat();

	public float getTemp();

	public void setJoules(float joules);

	public void addJoules(float joules);

	public void setDegrees(float degrees);

	public void addDegrees(float degrees);
}
