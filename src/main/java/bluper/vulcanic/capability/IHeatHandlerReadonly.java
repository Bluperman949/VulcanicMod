package bluper.vulcanic.capability;

public interface IHeatHandlerReadonly {
	public float getTemp();

	/**
	 * Based on real-life Specific Heat. Should only be used to calculate
	 * Specific Heat Volume.
	 * 
	 * @see IHeatHandlerReadonly#getSpecHeatVolume()
	 */
	public float getSpecHeat();

	/**
	 * Specific Heat Volume: (specific heat) * (volume in blocks).
	 * Realistically, this represents J/°C, but the math for that is overly
	 * complicated for Minecraft purposes.<br>
	 * This is used instead of <code>getSpecHeat()</code> because an N-block
	 * multiblock should take N times as much energy to heat up as a
	 * single-block machine.<br>
	 * By default, it just points to <code>getSpecHeat()</code> because most
	 * machines are single-block.
	 */
	public default float getSpecHeatVolume() {
		return getSpecHeat();
	}
}
