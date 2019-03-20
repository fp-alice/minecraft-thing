package net.fabricmc.example.events;

import com.google.common.eventbus.EventBus;
import lombok.Getter;
import net.fabricmc.example.events.impl.Event;

public class EventManager {

	@Getter
	private static EventManager instance = new EventManager();
	private EventBus eventBus = new EventBus();

	private EventManager() {
	}

	public void post(Event event) {
		eventBus.post(event);
	}

	public void register(Object object) {
		eventBus.register(object);
	}

	public void unregister(Object object) {
		eventBus.unregister(object);
	}
}
