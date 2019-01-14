package org.san.geospatial.utils;

public enum ChannelMap {
	VISIBLE("visible"), VEGETATION("vegetation"), WATERVAPOR("waterVapor");
	private String value;

	private ChannelMap(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ChannelMap getEnum(String value) {
		for (ChannelMap channelMap : values()) {
			if (channelMap.getValue().equalsIgnoreCase(value))
				return channelMap;
		}
		return null;
	}
	@Override
	public String toString() {
		return value;
	}
}
