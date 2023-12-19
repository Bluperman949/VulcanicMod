package bluper.vulcanic.capability;

public interface IHeatHandler extends IHeatHandlerReadonly {
	public void setTemp(float temp);

	/**
	 * Should be called every tick.
	 */
	public default float share(IHeatHandler other) {
		float tempDiff = other.getTemp() - this.getTemp();
		float specHeatRatio = this.getSpecHeatVolume() / other.getSpecHeatVolume();
		return 0;
	}
}
