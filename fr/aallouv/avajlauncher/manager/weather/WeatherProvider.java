package fr.aallouv.avajlauncher.manager.weather;

import fr.aallouv.avajlauncher.manager.coordinate.Coordinates;

public class WeatherProvider {

	public WeatherProvider() {
	}

	public Weather getCurrentWeather(Coordinates coordinates) {
		int seed = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
		int weatherIndex = seed % 4;
		switch (weatherIndex) {
			case 1:
				return Weather.RAIN;
			case 2:
				return Weather.FOG;
			case 3:
				return Weather.SNOW;
			default:
				return Weather.SUN;
		}
	}

}
