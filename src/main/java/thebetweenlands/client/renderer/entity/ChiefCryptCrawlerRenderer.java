package thebetweenlands.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.BipedCryptCrawlerModel;
import thebetweenlands.client.state.BipedCryptCrawlerRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.ChiefCryptCrawler;

public class ChiefCryptCrawlerRenderer extends MobRenderer<ChiefCryptCrawler, BipedCryptCrawlerRenderState, BipedCryptCrawlerModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/crypt_crawler.png");

	public ChiefCryptCrawlerRenderer(EntityRendererProvider.Context context) {
		super(context, new BipedCryptCrawlerModel(context.bakeLayer(BLModelLayers.BIPED_CRYPT_CRAWLER)), 0.5F);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemRenderer()));
	}

	@Override
	protected void scale(BipedCryptCrawlerRenderState state, PoseStack stack) {
		stack.scale(1.35F, 1.35F, 1.35F);
	}

	@Override
	public ResourceLocation getTextureLocation(BipedCryptCrawlerRenderState state) {
		return TEXTURE;
	}

	@Override
	public BipedCryptCrawlerRenderState createRenderState() {
		return new BipedCryptCrawlerRenderState();
	}

	@Override
	public void extractRenderState(ChiefCryptCrawler entity, BipedCryptCrawlerRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.isBlocking = entity.isBlocking();
		state.onGround = entity.onGround();
	}
}
