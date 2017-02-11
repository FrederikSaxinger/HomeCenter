package com.philips.lighting.data;

import java.util.List;

import com.philips.lighting.model.PHLight;

public class Light {

	public PHLight phlight;
	public int cacheId;
	public int lightId;
	public Boolean isOn;
	public int brightness;
	public int reglerbreite;

	public Light(List<PHLight> phlights, int cachId, int lightId) {
		this.cacheId = cachId;
		this.lightId = lightId;
		this.brightness = 254;
		this.reglerbreite = Constants.REGLER_BREITE_INNEN;
		if (phlights != null) {
			this.phlight = phlights.get(cachId);
			this.isOn = phlight.getLastKnownLightState().isOn();

		} else {
			this.isOn = false;
		}
	}
}
