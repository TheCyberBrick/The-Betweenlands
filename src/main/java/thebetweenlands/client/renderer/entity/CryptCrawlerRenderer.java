package thebetweenlands.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import thebetweenlands.client.BLModelLayers;
import thebetweenlands.client.model.entity.CryptCrawlerModel;
import thebetweenlands.client.state.CryptCrawlerRenderState;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.entity.monster.CryptCrawler;

public class CryptCrawlerRenderer extends MobRenderer<CryptCrawler, CryptCrawlerRenderState, CryptCrawlerModel> {

	public static final ResourceLocation TEXTURE = TheBetweenlands.prefix("textures/entity/crypt_crawler.png");

	public CryptCrawlerRenderer(EntityRendererProvider.Context context) {
		super(context, new CryptCrawlerModel(context.bakeLayer(BLModelLayers.CRYPT_CRAWLER)), 0.5F);
	}

	@Override
	public CryptCrawlerRenderState createRenderState() {
		return new CryptCrawlerRenderState();
	}

	@Override
	public void extractRenderState(CryptCrawler entity, CryptCrawlerRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.onGround = entity.onGround();
		state.standingAngle = Mth.lerp(partialTick, entity.prevStandingAngle, entity.standingAngle);
	}

	@Override
	public ResourceLocation getTextureLocation(CryptCrawlerRenderState state) {
		return TEXTURE;
	}
}
