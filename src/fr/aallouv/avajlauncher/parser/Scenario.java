package fr.aallouv.avajlauncher.parser;

import java.util.Collections;
import java.util.List;

/** Conteneur immuable du scénario : nb de cycles + liste d'aéronefs. */
public final class Scenario {
	private final int cycles;
	private final List<AircraftSpec> aircraft;

	public Scenario(int cycles, List<AircraftSpec> aircraft) {
		this.cycles = cycles;
		this.aircraft = List.copyOf(aircraft);
	}

	public int getCycles() { return cycles; }
	public List<AircraftSpec> getAircraft() { return Collections.unmodifiableList(aircraft); }
}
