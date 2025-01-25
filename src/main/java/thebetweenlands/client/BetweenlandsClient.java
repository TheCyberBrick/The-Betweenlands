package thebetweenlands.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplayContext;
import net.minecraft.world.level.Level;
import thebetweenlands.api.recipes.AnimatorRecipe;
import thebetweenlands.api.recipes.MortarRecipe;
import thebetweenlands.api.recipes.SmokingRackRecipe;
import thebetweenlands.api.recipes.SteepingPotRecipe;
import thebetweenlands.client.event.ClientRegistrationEvents;
import thebetweenlands.client.particle.ParticleFactory;
import thebetweenlands.client.particle.VanillaParticleFactory;
import thebetweenlands.common.registries.RecipePropertySetRegistry;
import thebetweenlands.common.registries.RecipeRegistry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("resource")
public class BetweenlandsClient {

	public static class ClientRecipes implements RecipeAccess {

		private static final List<RecipeHolder<MortarRecipe>> MORTAR = new ArrayList<>();

		private final Map<ResourceKey<RecipePropertySet>, RecipePropertySet> itemSets;

		public ClientRecipes(Map<ResourceKey<RecipePropertySet>, RecipePropertySet> itemSets) {
			this.itemSets = itemSets;
		}

		@Override
		public RecipePropertySet propertySet(ResourceKey<RecipePropertySet> set) {
			return this.itemSets.getOrDefault(set, RecipePropertySet.EMPTY);
		}

		@Override
		public SelectableRecipe.SingleInputSet<StonecutterRecipe> stonecutterRecipes() {
			return SelectableRecipe.SingleInputSet.empty();
		}

		public static boolean doesSteepingPotUseItem(ItemStack stack) {
			for (RecipeHolder<SteepingPotRecipe> recipe : STEEPING) {
				for (Ingredient ingredient : recipe.value().placementInfo().ingredients()) {
					if (ingredient.test(stack)) {
						return true;
					}
				}
			}

			return false;
		}

		public static List<Ingredient> getMortarInputs(ItemStack output) {
			var ctx = SlotDisplayContext.fromLevel(Minecraft.getInstance().level);
			for (RecipeHolder<MortarRecipe> recipe : MORTAR) {
				for (RecipeDisplay display : recipe.value().display()) {
					for (ItemStack stack : display.result().resolveForStacks(ctx)) {
						if (ItemStack.isSameItem(output, stack)) {
							return recipe.value().placementInfo().ingredients();
						}
					}
				}
			}
			return List.of();
		}
	}

	@Nullable
	public static ClientLevel getClientLevel() {
		return Minecraft.getInstance().level;
	}

	@Nullable
	public static Player getClientPlayer() {
		return Minecraft.getInstance().player;
	}

	public static ClientRecipes getClientRecipeInfo() {

	}

	@Deprecated(forRemoval = true) //just use Gui.getCameraPlayer, theres no reason for us to copy the method here
	@Nullable
	public static Player getCameraPlayer() {
		return Minecraft.getInstance().getCameraEntity() instanceof Player player ? player : null;
	}

	public static void playLocalSound(SoundInstance instance) {
		Minecraft.getInstance().getSoundManager().play(instance);
	}

	public static RiftVariantReloadListener getRiftVariantLoader() {
		return ClientRegistrationEvents.riftVariantListener;
	}

	public static AspectIconTextureManager getAspectIconManager() {
		return ClientRegistrationEvents.aspectIcons;
	}

	/**
	 * DO NOT USE DIRECTLY. Call via TheBetweenlands.createParticle as that will send particles to the client if called on the server
	 */
	@SuppressWarnings("unchecked")
	public static <T extends ParticleOptions> void createParticle(T options, Level level, double x, double y, double z, @Nullable ParticleFactory.ParticleArgs<?> args) {
		ResourceLocation location = BuiltInRegistries.PARTICLE_TYPE.getKey(options.getType());
		Particle particle;
		ParticleProvider<T> provider = (ParticleProvider<T>) Minecraft.getInstance().particleEngine.providers.get(location);
		if (provider instanceof ParticleFactory<?, T> factory) {
			particle = factory.create(options, (ClientLevel) level, x, y, z, args);
		} else {
			VanillaParticleFactory<T> factory = VanillaParticleFactory.create(provider);
			particle = factory.create(options, (ClientLevel) level, x, y, z, args);
		}

		if (particle != null) {
			Minecraft.getInstance().particleEngine.add(particle);
		}
	}
}
