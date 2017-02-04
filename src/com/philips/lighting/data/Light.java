package com.philips.lighting.data;

import java.util.List;

import com.philips.lighting.model.PHLight;

public class Light {

	public PHLight phlight;
	public int cacheId;
	public int lightId;
	public Boolean lightOn;

	public Light(List<PHLight> phlights, int cachId, int lightId) {
		this.phlight = phlights.get(cachId);
		this.cacheId = cachId;
		this.lightId = lightId;
		this.lightOn = phlight.getLastKnownLightState().isOn();
	}
}
