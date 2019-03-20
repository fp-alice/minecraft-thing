package net.fabricmc.example.client.mods.impl;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import net.fabricmc.example.client.graphics.RenderPosition;
import net.fabricmc.example.client.graphics.RenderUtils;
import net.fabricmc.example.events.impl.HudDrawEvent;
import net.fabricmc.example.events.impl.WorldRenderEvent;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Xray extends Mod {

	private ArrayList<XrayTarget> xrayTargets =
			Lists.newArrayList(
					new XrayTarget("iron", 0xFFDAB9),
					new XrayTarget("diamond", 0x00CCFF),
					new XrayTarget("gold", 0xFFFF00),
					new XrayTarget("lapis", 0x0000FF)
			);

	private List<XrayMatch> xrayMatches = Lists.newArrayList();

	public Xray() {
		super("xray", GLFW.GLFW_KEY_X, 0xCC00CC, true);
	}

	private void drawBlockEsp(XrayMatch xrayMatch, float partialTickTime) {
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		BlockPos blockPos = xrayMatch.blockPos;

		RenderPosition pos = new RenderPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());

		float boxAlpha = 0.25f;
		float lineAlpha = 1.0f;

		RenderUtils.color(xrayMatch.color, boxAlpha);
		RenderUtils.drawBox(pos.getX(), pos.getY(), pos.getZ(), 1, partialTickTime);

		RenderUtils.color(xrayMatch.color, lineAlpha);
		RenderUtils.drawOutlinedBox(pos.getX(), pos.getY(), pos.getZ(), 1, partialTickTime);
	}

	private void renderAllMatches(float partialTickTime) {
		RenderUtils.start3D();
		RenderUtils.startSmooth();
		xrayMatches.forEach(match -> drawBlockEsp(match, partialTickTime));
		RenderUtils.endSmooth();
		RenderUtils.end3D();
	}

	private XrayThread xrayThread = new XrayThread();

	@Override
	void onEnable() {
		xrayThread.start();
	}

	@Override
	void onDisable() {
		xrayThread.interrupt();
		xrayThread = new XrayThread();
		xrayMatches = Lists.newArrayList();
	}

	@Subscribe
	public void worldRenderEvent(WorldRenderEvent event) {
		if (isEnabled()) {
			xrayMatches = xrayMatches.stream().filter(m -> blockIsTarget(m.blockPos)).collect(Collectors.toList());
			renderAllMatches(event.getPartialTickTime());
		}
	}

	private String blockPosToString(BlockPos blockPos) {
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		return minecraftClient.world.getBlockState(blockPos)
				.getBlock()
				.getTextComponent()
				.getString()
				.toLowerCase();
	}

	private boolean blockIsTarget(BlockPos blockPos) {
		return xrayTargets.stream().anyMatch(target -> blockPosToString(blockPos).contains(target.string));
	}


	@AllArgsConstructor
	private class XrayTarget {
		@Getter private String string;
		@Getter private int color;
	}

	@AllArgsConstructor
	private class XrayMatch {
		@Getter private BlockPos blockPos;
		@Getter private int color;

		@Override
		public String toString() {
			return blockPosToString(blockPos);
		}
	}

	private class XrayThread extends Thread {
		int radius = 16;

		public void run() {
			while (!this.isInterrupted()) {
				try {
					getXrayMatchesInRadius(radius);
					Thread.sleep(10);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}

		private List<BlockPos> getBlocksInRadius(int radius) {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			ClientPlayerEntity player = minecraftClient.player;
			int x = (int) player.x;
			int y = (int) player.y;
			int z = (int) player.z;

			int lowX = x - radius;
			int lowY = y - radius;
			int lowZ = z - radius;
			int highX =	x + radius;
			int highY = y + radius;
			int highZ = z + radius;

			highY = highY > 255 ? 255 : highY;

			lowY = lowY < 1 ? 1 : lowY;

			val blocks = BlockPos.getBlocksInCuboid(lowX, lowY, lowZ, highX, highY, highZ);

			return blocks.map(BlockPos::toImmutable).collect(Collectors.toList());
		}

		// Function assumes the block being referenced is actually a target - do not use on unchecked block positions
		private XrayMatch blockPosToXrayMatch(BlockPos blockPos) {
			XrayTarget target = xrayTargets.stream()
					.filter(t -> blockPosToString(blockPos).contains(t.string))
					.findFirst()
					.get();
			return new XrayMatch(blockPos, target.color);
		}

		private void getXrayMatchesInRadius(int radius) {
			List<BlockPos> blocks = getBlocksInRadius(radius);

			xrayMatches = blocks.stream()
					.filter(Xray.this::blockIsTarget)
					.map(this::blockPosToXrayMatch)
					.collect(Collectors.toList());

		}
	}
}
