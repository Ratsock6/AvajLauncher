package fr.aallouv.avajlauncher.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public final class ScenarioParser {

	private static final Set<String> ALLOWED_TYPES =
			Set.of("Baloon", "Helicopter", "JetPlane");

	private ScenarioParser() {}

	public static Scenario parse(Path path) throws IOException, ScenarioFormatException {
		List<String> raw = Files.readAllLines(path);

		if (raw.isEmpty())
			throw new ScenarioFormatException("Empty scenario file: " + path.toString());

		int lineNo = 0;

		Integer cycles = null;
		int index = 0;
		while (index < raw.size()) {
			lineNo = index + 1;
			String line = normalize(raw.get(index));
			if (!line.isEmpty() && !isComment(line)) {
				cycles = parseCycles(line, lineNo);
				index++;
				break;
			}
			index++;
		}
		if (cycles == null)
			throw new ScenarioFormatException("Cycles not found in scenario file: " + path.toString());

		// --- 2) Lire les spécifications d’aéronefs ---
		List<AircraftSpec> specs = new ArrayList<>();
		for (; index < raw.size(); index++) {
			lineNo = index + 1;
			String line = normalize(raw.get(index));
			if (line.isEmpty() || isComment(line)) continue;

			String[] parts = line.split("\\s+");
			if (parts.length != 5) {
				throw new ScenarioFormatException(
						err(lineNo, "Invalid format, expected 5 tokens but found " + parts.length + ".")
				);
			}

			String type = parts[0];
			if (!ALLOWED_TYPES.contains(type)) {
				throw new ScenarioFormatException(
						err(lineNo, "Unknow type '" + type + "'. Valid types : " + ALLOWED_TYPES + ".")
				);
			}

			String name = parts[1];
			if (!name.matches("[A-Za-z0-9_-]+")) {
				throw new ScenarioFormatException(
						err(lineNo, "Invalid name" + name + "'. Allowed characters: letters, digits, '_', '-'.")
				);
			}

			int lon = parsePositiveInt(parts[2], "LONGITUDE", lineNo);
			int lat = parsePositiveInt(parts[3], "LATITUDE", lineNo);
			int hgt = parseIntInRange(parts[4], 0, 100, "HEIGHT", lineNo);

			specs.add(new AircraftSpec(type, name, lon, lat, hgt));
		}

		if (specs.isEmpty())
			throw new ScenarioFormatException("Aucun aéronef valide trouvé après la ligne des cycles.");

		return new Scenario(cycles, specs);
	}


	private static String normalize(String s) {
		return s == null ? "" : s.trim();
	}

	private static boolean isComment(String s) {
		return s.startsWith("#");
	}

	private static Integer parseCycles(String line, int lineNo) throws ScenarioFormatException {
		try {
			int cycles = Integer.parseInt(line);
			if (cycles <= 0) {
				throw new ScenarioFormatException(err(lineNo, "Invalid number of cycles, must be positive. Found: " + cycles + "."));
			}
			return cycles;
		} catch (NumberFormatException e) {
			throw new ScenarioFormatException(err(lineNo, "Cycles must be an integer. Found: '" + line + "'."), e);
		}
	}

	private static int parsePositiveInt(String token, String label, int lineNo)
			throws ScenarioFormatException {
		try {
			int v = Integer.parseInt(token);
			if (v < 0) {
				throw new ScenarioFormatException(
						err(lineNo, label + " needs to be a positive integer. Found: " + v + ".")
				);
			}
			return v;
		} catch (NumberFormatException e) {
			throw new ScenarioFormatException(
					err(lineNo, label + " needs to be an integer, found: '" + token + "'."),
					e
			);
		}
	}

	private static int parseIntInRange(String token, int min, int max, String label, int lineNo)
			throws ScenarioFormatException {
		try {
			int v = Integer.parseInt(token);
			if (v < min || v > max) {
				throw new ScenarioFormatException(
						err(lineNo, label + " needs to be between " + min + " and " + max + ", found: " + v + ".")
				);
			}
			return v;
		} catch (NumberFormatException e) {
			throw new ScenarioFormatException(
					err(lineNo, label + " needs to be an integer, found: '" + token + "'."),
					e
			);
		}
	}

	private static String err(int lineNo, String msg) {
		return "Line " + lineNo + " : " + msg;
	}
}