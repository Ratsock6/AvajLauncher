package fr.aallouv.avajlauncher.manager.flyable;

import fr.aallouv.avajlauncher.manager.weather.tower.WeatherTower;

public interface Flyable {

	public void updateConditions();

	public void registerTower(WeatherTower weatherTower);

}
