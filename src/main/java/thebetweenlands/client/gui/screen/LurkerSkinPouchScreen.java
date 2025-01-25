package thebetweenlands.client.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import thebetweenlands.common.inventory.LurkerSkinPouchMenu;

public class LurkerSkinPouchScreen extends AbstractContainerScreen<LurkerSkinPouchMenu> {

	private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.withDefaultNamespace("textures/gui/container/generic_54.png");
	private final int containerRows;

	public LurkerSkinPouchScreen(LurkerSkinPouchMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
		this.containerRows = menu.getRows();
		this.imageHeight = 114 + this.containerRows * 18;
		this.inventoryLabelY = this.imageHeight - 94;

		menu.addUUIDChangeListener();
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
		super.render(graphics, mouseX, mouseY, partialTick);
		this.renderTooltip(graphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		graphics.blit(RenderType::guiTextured, CONTAINER_BACKGROUND, i, j, 0.0F, 0.0F, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
		graphics.blit(RenderType::guiTextured, CONTAINER_BACKGROUND, i, j + this.containerRows * 18 + 17, 0.0F, 0.0F, 0, 126, this.imageWidth, 96);

		// For debugging UUID sync
//		graphics.drawString(font, Component.literal(menu.getSecureContainer().getContainerStackUUID().toString()), this.leftPos, this.topPos - font.lineHeight * 2, 0xFFFFFF);
//		graphics.drawString(font, Component.literal(Minecraft.getInstance().player.getMainHandItem().get(DataComponentRegistry.INVENTORY_ITEM_UUID).toString()), this.leftPos, this.topPos - font.lineHeight * 3, 0xFFFFFF);
	}
}
