package bluper.vulcanic.capability;

public class Temperature {

	public static final float K_FREEZING = 273.15f;

	public static float CtoK(float c) {
		return c - K_FREEZING;
	}

	public static float CtoF(float c) {
		return c * 1.8f + 32;
	}
}
