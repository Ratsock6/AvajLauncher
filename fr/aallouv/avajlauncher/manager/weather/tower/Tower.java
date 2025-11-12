package fr.aallouv.avajlauncher.manager.weather.tower;

import fr.aallouv.avajlauncher.manager.flyable.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {

	List<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable) {
		if (!observers.contains(flyable)) {
			observers.add(flyable);
		}
	}

	public void unregister(Flyable flyable) {
		observers.remove(flyable);
	}

	protected void conditionsChanged() {
		for (Flyable flyable : observers) {
			flyable.updateConditions();
		}
	}

}
