package net.fabricmc.example.client.graphics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import net.minecraft.entity.Entity;

@AllArgsConstructor
public class RenderPosition {

	@Getter double x;
	@Getter double y;
	@Getter double z;

	public RenderPosition(Entity entity, double partialTickTime) {
		val entityRenderPosX = entity.prevX + (entity.x - entity.prevX) * partialTickTime;
		val entityRenderPosY = entity.prevY + (entity.y - entity.prevRenderY) * partialTickTime;
		val entityRenderPosZ = entity.prevZ + (entity.z - entity.prevZ) * partialTickTime;

		this.x = entityRenderPosX;
		this.y = entityRenderPosY;
		this.z = entityRenderPosZ;
	}

	public RenderPosition add(RenderPosition position) {
		return new RenderPosition(position.x + this.x, position.y + this.y, position.z + this.z);
	}

	public RenderPosition add(double x, double y, double z) {
		return new RenderPosition(this.x + x, this.y + y, this.z + z);
	}

	public RenderPosition sub(RenderPosition position) {
		return new RenderPosition(position.x - this.x, position.y - this.y, position.z - this.z);
	}

	public RenderPosition sub(double x, double y, double z) {
		return new RenderPosition(this.x - x, this.y - y, this.z - z);
	}
}
