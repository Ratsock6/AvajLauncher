package fr.aallouv.avajlauncher.simulation;

import java.util.ArrayList;

public class SimulationLogs {

	private static final ArrayList<String> logs = new ArrayList<>();

	public static ArrayList<String> getLogs() {
		return logs;
	}

	public static void addLog(String log) {
		logs.add(log);
	}

	public static void clearLogs() {
		logs.clear();
	}
}
