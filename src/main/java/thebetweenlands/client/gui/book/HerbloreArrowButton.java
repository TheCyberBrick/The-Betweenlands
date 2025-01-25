package thebetweenlands.client.gui.book;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import thebetweenlands.common.TheBetweenlands;

public class HerbloreArrowButton extends Button {

	private static final ArrowSprites ARROW = new ArrowSprites(
		TheBetweenlands.prefix("manual/left_arrow"),
		TheBetweenlands.prefix("manual/left_arrow_highlighted"),
		TheBetweenlands.prefix("manual/right_arrow"),
		TheBetweenlands.prefix("manual/right_arrow_highlighted")
	);
	private final boolean left;

	public HerbloreArrowButton(int x, int y, boolean left, OnPress onPress) {
		super(x, y, 19, 9, Component.empty(), onPress, Button.DEFAULT_NARRATION);
		this.left = left;
	}

	@Override
	protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
		RenderSystem.enableBlend();
		RenderSystem.enableDepthTest();
		graphics.blitSprite(RenderType::guiTextured, ARROW.get(this.left, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight(), ARGB.white(this.alpha));
	}

	public record ArrowSprites(ResourceLocation left, ResourceLocation leftFocused, ResourceLocation right, ResourceLocation rightFocused) {

		public ResourceLocation get(boolean left, boolean focused) {
			if (left) {
				return focused ? this.leftFocused : this.left;
			} else {
				return focused ? this.rightFocused : this.right;
			}
		}
	}
}
