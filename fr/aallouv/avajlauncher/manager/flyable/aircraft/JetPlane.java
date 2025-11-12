package fr.aallouv.avajlauncher.manager.flyable.aircraft;

import fr.aallouv.avajlauncher.AvajLauncher;
import fr.aallouv.avajlauncher.manager.coordinate.Coordinates;
import fr.aallouv.avajlauncher.manager.flyable.Aircraft;
import fr.aallouv.avajlauncher.manager.weather.Weather;
import fr.aallouv.avajlauncher.manager.weather.tower.WeatherTower;
import fr.aallouv.avajlauncher.simulation.SimulationLogs;

public class JetPlane extends Aircraft {

	public JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		Weather weather = AvajLauncher.getInstance().getWeatherProvider().getCurrentWeather(this.getCoordinates());
		switch (weather) {
			case SUN:
				addHeight(2);
				addLatitude(10);
				SimulationLogs.addLog("JetPlane#" + this.getName() + "(" + this.getId() + "): It's a sunny day. Perfect for a flight.");
				break;
			case RAIN:
				addLatitude(5);
				SimulationLogs.addLog("JetPlane#" + this.getName() + "(" + this.getId() + "): It's raining. Watch out for lightnings.");
				break;
			case FOG:
				addLatitude(1);
				SimulationLogs.addLog("JetPlane#" + this.getName() + "(" + this.getId() + "): Can't see anything in this fog.");
				break;
			case SNOW:
				removeHeight(7);
				SimulationLogs.addLog("JetPlane#" + this.getName() + "(" + this.getId() + "): OMG! Winter is coming!");
				break;
		}
	}

	@Override
	public void registerTower(WeatherTower weatherTower) {
		System.out.println("CALLING REGISTER TOWER IN JETPLANE");
		weatherTower.register(this);
		SimulationLogs.addLog("Tower says: JetPlane#" + this.getName() + "(" + this.getId() + ") registered to weather tower.");
	}
}
