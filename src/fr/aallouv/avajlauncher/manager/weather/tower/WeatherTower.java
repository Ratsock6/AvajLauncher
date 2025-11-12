package fr.aallouv.avajlauncher.manager.weather.tower;

import fr.aallouv.avajlauncher.AvajLauncher;
import fr.aallouv.avajlauncher.manager.coordinate.Coordinates;
import fr.aallouv.avajlauncher.manager.weather.Weather;

public class WeatherTower extends Tower {

	public Weather getWeather(Coordinates coordinates) {
		return AvajLauncher.getInstance().getWeatherProvider().getCurrentWeather(coordinates);
	}

	public void changeWeather() {
		this.conditionsChanged();
	}
}
