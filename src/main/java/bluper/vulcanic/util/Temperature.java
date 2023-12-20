package bluper.vulcanic.util;

public class Temperature {

	public static final float FREEZING = 273.15f;

	public static enum Unit {
		KELVIN,
		CELSIUS,
		FAHRENHEIT;
	}

	public static float CtoK(float c) {
		return c + FREEZING;
	}

	public static float KtoC(float k) {
		return k - FREEZING;
	}

	public static float CtoF(float c) {
		return c * 1.8f + 32;
	}

	public static float fromBiome(float biomeTemp) {
		return (biomeTemp < 0 ? biomeTemp * 10 : biomeTemp * 20) + FREEZING;
	}

}
