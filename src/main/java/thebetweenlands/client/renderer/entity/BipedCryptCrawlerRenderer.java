package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.BipedCryptCrawlerModel;
import thebetweenlands.client.state.BipedCryptCrawlerRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.BipedCryptCrawler;

public class BipedCryptCrawlerRenderer extends MobRenderer<BipedCryptCrawler, BipedCryptCrawlerRenderState, BipedCryptCrawlerModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/crypt_crawler.png");

	public BipedCryptCrawlerRenderer(EntityRendererProvider.Context context) {
		super(context, new BipedCryptCrawlerModel(context.bakeLayer(BLModelLayers.BIPED_CRYPT_CRAWLER)), 0.5F);
		this.addLayer(new ItemInHandLayer<>(this, context.getItemRenderer()));
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
	public void extractRenderState(BipedCryptCrawler entity, BipedCryptCrawlerRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.isBlocking = entity.isBlocking();
		state.onGround = entity.onGround();
	}
}
