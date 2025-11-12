package fr.aallouv.avajlauncher.parser;

public final class AircraftSpec {
	private final String type;
	private final String name;
	private final int longitude;
	private final int latitude;
	private final int height;

	public AircraftSpec(String type, String name, int longitude, int latitude, int height) {
		this.type = type;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}

	public String getType() { return type; }
	public String getName() { return name; }
	public int getLongitude() { return longitude; }
	public int getLatitude() { return latitude; }
	public int getHeight() { return height; }

	@Override
	public String toString() {
		return type + " " + name + " " + longitude + " " + latitude + " " + height;
	}
}
