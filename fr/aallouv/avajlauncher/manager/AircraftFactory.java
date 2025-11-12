package fr.aallouv.avajlauncher.manager;

import fr.aallouv.avajlauncher.manager.coordinate.Coordinates;
import fr.aallouv.avajlauncher.manager.flyable.Flyable;
import fr.aallouv.avajlauncher.manager.flyable.aircraft.Baloon;
import fr.aallouv.avajlauncher.manager.flyable.aircraft.Helicopter;
import fr.aallouv.avajlauncher.manager.flyable.aircraft.JetPlane;

public class AircraftFactory {

	public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
		switch (type) {
			case "Baloon":
				return new Baloon(name, new Coordinates(longitude, latitude, height));
			case "Helicopter":
				return new Helicopter(name, new Coordinates(longitude, latitude, height));
			case "JetPlane":
				return new JetPlane(name, new Coordinates(longitude, latitude, height));
			default:
				return null;
		}
	}

}
