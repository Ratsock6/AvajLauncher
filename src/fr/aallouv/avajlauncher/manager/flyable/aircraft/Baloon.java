package fr.aallouv.avajlauncher.manager.flyable.aircraft;

import fr.aallouv.avajlauncher.AvajLauncher;
import fr.aallouv.avajlauncher.manager.coordinate.Coordinates;
import fr.aallouv.avajlauncher.manager.flyable.Aircraft;
import fr.aallouv.avajlauncher.manager.weather.Weather;
import fr.aallouv.avajlauncher.manager.weather.tower.WeatherTower;
import fr.aallouv.avajlauncher.simulation.SimulationLogs;

public class Baloon extends Aircraft {

	public Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		Weather weather = AvajLauncher.getInstance().getWeatherProvider().getCurrentWeather(this.getCoordinates());
		switch (weather) {
			case SUN:
				addLongitude(2);
				addHeight(4);
				SimulationLogs.addLog("Baloon#" + this.getName() + "(" + this.getId() + "):  Let's enjoy the good weather and take some pics.");
				break;
			case RAIN:
				removeHeight(5);
				SimulationLogs.addLog("Baloon#" + this.getName() + "(" + this.getId() + "): Damn you rain! You messed up my baloon.");
				break;
			case FOG:
				removeHeight(3);
				SimulationLogs.addLog("Baloon#" + this.getName() + "(" + this.getId() + "): Can't see anything in this fog.");
				break;
			case SNOW:
				removeHeight(15);
				SimulationLogs.addLog("Baloon#" + this.getName() + "(" + this.getId() + "): It's snowing. We're losing altitude fast!");
				break;
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		weatherTower.register(this);
		SimulationLogs.addLog("Tower says: Baloon#" + this.getName() + "(" + this.getId() + ") registered to weather tower.");
	}
}
