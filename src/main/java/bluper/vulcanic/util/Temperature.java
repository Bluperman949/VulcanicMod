package bluper.vulcanic.util;

public class Temperature {

	public static final float FREEZING = 273.15f;

	public static float CtoK(float c) {
		return c + FREEZING;
	}

	public static float KtoC(float k) {
		return k - FREEZING;
	}

	public static float CtoF(float c) {
		return c * 1.8f + 32;
	}

}
