package fr.aallouv.avajlauncher;

import fr.aallouv.avajlauncher.manager.AircraftFactory;
import fr.aallouv.avajlauncher.manager.flyable.Flyable;
import fr.aallouv.avajlauncher.manager.weather.WeatherProvider;
import fr.aallouv.avajlauncher.manager.weather.tower.WeatherTower;
import fr.aallouv.avajlauncher.parser.Scenario;
import fr.aallouv.avajlauncher.parser.ScenarioFormatException;
import fr.aallouv.avajlauncher.parser.ScenarioParser;
import fr.aallouv.avajlauncher.simulation.SimulationLogs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class AvajLauncher {

	private static AvajLauncher instance;
	private final AircraftFactory aircraftFactory;
	private final WeatherProvider weatherProvider;


	public AvajLauncher(String filename) {
		instance = this;
		this.weatherProvider = new WeatherProvider();
		this.aircraftFactory = new AircraftFactory();
		try {
			Scenario scenario = ScenarioParser.parse(Path.of(filename));
			System.out.println("✅ Scenario : " + scenario.getCycles() + " cycles, " +
					scenario.getAircraft().size() + " Aircraft.");

			WeatherTower weatherTower = new WeatherTower();
			ArrayList<Flyable> flyables = new ArrayList<>();
			for (var spec : scenario.getAircraft()) {
				Flyable aircraft = getInstance().getAircraftFactory().newAircraft(
						spec.getType(),
						spec.getName(),
						spec.getLongitude(),
						spec.getLatitude(),
						spec.getHeight()
				);
				aircraft.registerTower(weatherTower);
				flyables.add(aircraft);
			}
			for (int i = 0; i < scenario.getCycles(); i++) {
				weatherTower.changeWeather();
			}

			for (String logs : SimulationLogs.getLogs()) {
				System.out.println(logs);
				try {
					Files.writeString(Path.of("simulation.txt"), logs + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				} catch (Exception e) {
					System.err.println("Error: Could not write to logs file.");
				}
			}

			System.out.println("✅ Simulation completed successfully.");
		} catch (ScenarioFormatException e) {
			System.out.println("❌ Scenario error : " + e.getMessage());
			System.exit(2);
		} catch (Exception e) {
			System.out.println("❌ Error: " + e.getMessage());
			System.exit(3);
		}

	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Error: Invalid number of arguments.");
			System.err.println("Usage: java -jar avaj-launcher.jar <scenario file>");
			System.exit(1);
		}
		new AvajLauncher(args[0]);
	}

	public static AvajLauncher getInstance() {
		return instance;
	}

	public AircraftFactory getAircraftFactory() {
		return aircraftFactory;
	}

	public WeatherProvider getWeatherProvider() {
		return weatherProvider;
	}

}
