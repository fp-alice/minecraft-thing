package net.fabricmc.example.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class WorldRenderEvent extends Event {
	@Getter private float partialTickTime;
}
