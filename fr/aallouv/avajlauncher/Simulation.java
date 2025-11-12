package fr.aallouv.avajlauncher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Simulation {

	private final ArrayList<String> logs;
	private Path logsFile;
	private int cycles;

	public Simulation() {
		this.logs = new ArrayList<>();
		addLog("Simulation started.");
		addLog("-------------------");
		this.logsFile = Paths.get("simulation.txt");
	}

	public ArrayList<String> getLogs() {
		return logs;
	}

	public void addLog(String log) {
		logs.add(log);
	}

	public void clearLogs() {
		logs.clear();
	}

	public void resetLogsFile() {
		if (logsFile.toFile().exists()) {
			boolean result = logsFile.toFile().delete();
			if (!result) {
				System.err.println("Error: Could not reset logs file.");
			}
		}
	}

	public void finishSimulation() {
		for (String log : logs) {
			System.out.println(log);
		}
	}

}
