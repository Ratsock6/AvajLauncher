package fr.aallouv.avajlauncher.manager.flyable.aircraft;

import fr.aallouv.avajlauncher.AvajLauncher;
import fr.aallouv.avajlauncher.manager.coordinate.Coordinates;
import fr.aallouv.avajlauncher.manager.flyable.Aircraft;
import fr.aallouv.avajlauncher.manager.weather.Weather;
import fr.aallouv.avajlauncher.manager.weather.tower.WeatherTower;
import fr.aallouv.avajlauncher.simulation.SimulationLogs;

public class Helicopter extends Aircraft {

	public Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		Weather weather = AvajLauncher.getInstance().getWeatherProvider().getCurrentWeather(this.getCoordinates());
		switch (weather) {
			case SUN:
				addHeight(2);
				addLongitude(10);
				SimulationLogs.addLog("Helicopter#" + this.getName() + "(" + this.getId() + "): This is hot.");
				break;
			case RAIN:
				addLongitude(5);
				SimulationLogs.addLog("Helicopter#" + this.getName() + "(" + this.getId() + "): It's raining. Better watch out for lightning.");
				break;
			case FOG:
				addLongitude(1);
				SimulationLogs.addLog("Helicopter#" + this.getName() + "(" + this.getId() + "): Can't see anything in this fog.");
				break;
			case SNOW:
				removeHeight(12);
				SimulationLogs.addLog("Helicopter#" + this.getName() + "(" + this.getId() + "): My rotor is going to freeze!");
				break;
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		weatherTower.register(this);
		SimulationLogs.addLog("Tower says: Helicopter#" + this.getName() + "(" + this.getId() + ") registered to weather tower.");
	}
}
