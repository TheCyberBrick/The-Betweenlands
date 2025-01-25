package thebetweenlands.api.item;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import thebetweenlands.api.attachment.IDecayData;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.component.item.CorrosionData;
import thebetweenlands.common.config.BetweenlandsConfig;
import thebetweenlands.common.datagen.tags.BLBiomeTagProvider;
import thebetweenlands.common.datagen.tags.BLDimensionTypeTagProvider;
import thebetweenlands.common.datagen.tags.BLItemTagProvider;
import thebetweenlands.common.registries.AttachmentRegistry;
import thebetweenlands.common.registries.DataComponentRegistry;

public class CorrosionHelper {

	/**
	 * The number of corrosion stages, used for the item model overrides
	 */
	public static final int CORROSION_STAGE_COUNT = 6;

	/**
	 * The maximum number of corrosion points a tool can have
	 */
	public static final int MAX_CORROSION = 255;

	/**
	 * The maximum number of coating points a tool can have
	 */
	public static final int MAX_COATING = 600;


	/**
	 * Returns the maximum corrosion of the specified item, or -1 if it can't be corroded.
	 */
	public static int getMaximumCorrosion(ItemStack stack) {
		if(!isCorrodible(stack)) {
			return -1;
		}

		if(stack.is(BLItemTagProvider.CUSTOM_CORRODIBLE) && stack.getItem() instanceof CustomCorrodible) {
			return ((CustomCorrodible) stack.getItem()).getMaxCorrosion(stack);
		} else {
			return stack.getOrDefault(DataComponentRegistry.MAX_CORROSION, MAX_CORROSION);
		}
	}

	/**
	 * Returns the current corrosion of the specified item, or -1 if it can't be corroded.
	 */
	public static int getCorrosion(ItemStack stack) {
		if(!isCorrodible(stack)) {
			return -1;
		}

		if(stack.is(BLItemTagProvider.CUSTOM_CORRODIBLE) && stack.getItem() instanceof CustomCorrodible) {
			return ((CustomCorrodible) stack.getItem()).getCorrosion(stack);
		} else if(stack.has(DataComponentRegistry.CORROSION)) {
			return stack.get(DataComponentRegistry.CORROSION).corrosion();
		} else {
			return 0; // Hasn't been tagged yet
		}
	}

	/**
	 * Returns the maximum coating of the specified item, or -1 if it can't be corroded.
	 */
	public static int getMaximumCoating(ItemStack stack) {
		if(!isCorrodible(stack)) {
			return -1;
		}

		if(stack.is(BLItemTagProvider.CUSTOM_CORRODIBLE) && stack.getItem() instanceof CustomCorrodible) {
			return ((CustomCorrodible) stack.getItem()).getMaxCoating(stack);
		} else {
			return stack.getOrDefault(DataComponentRegistry.MAX_COATING, MAX_COATING);
		}
	}

	/**
	 * Returns the current coating of the specified item, or -1 if it can't be corroded.
	 */
	public static int getCoating(ItemStack stack) {
		if(!isCorrodible(stack)) {
			return -1;
		}

		if(stack.is(BLItemTagProvider.CUSTOM_CORRODIBLE) && stack.getItem() instanceof CustomCorrodible) {
			return ((CustomCorrodible) stack.getItem()).getCoating(stack);
		} else if(stack.has(DataComponentRegistry.CORROSION)) {
			return stack.get(DataComponentRegistry.CORROSION).coating();
		} else {
			return 0; // Hasn't been tagged yet
		}
	}


	/**
	 * Sets the corrosion on a stack.
	 */
	public static void setCorrosion(ItemStack stack, int corrosion) {
		if(!isCorrodible(stack)) {
			return;
		}

		if(stack.is(BLItemTagProvider.CUSTOM_CORRODIBLE) && stack.getItem() instanceof CustomCorrodible) {
			((CustomCorrodible) stack.getItem()).setCorrosion(stack, corrosion);
		} else {
			stack.set(DataComponentRegistry.CORROSION, stack.getOrDefault(DataComponentRegistry.CORROSION, new CorrosionData(0, 0)).withCorrosion(corrosion));
		}
	}

	/**
	 * Sets the coating on a stack.
	 */
	public static void setCoating(ItemStack stack, int coating) {
		if(!isCorrodible(stack)) {
			return;
		}

		if(stack.is(BLItemTagProvider.CUSTOM_CORRODIBLE) && stack.getItem() instanceof CustomCorrodible) {
			((CustomCorrodible) stack.getItem()).setCoating(stack, coating);
		} else {
			stack.set(DataComponentRegistry.CORROSION, stack.getOrDefault(DataComponentRegistry.CORROSION, new CorrosionData(0, 0)).withCoating(coating));
		}
	}

	/**
	 * Returns a general modifier at the amount corrosion of the specified item
	 */
	public static float getModifier(ItemStack stack) {
		if(!isCorrodible(stack)) {
			return 1.0F;
		}

		int corrosion, maxCorrosion;
		if(stack.is(BLItemTagProvider.CUSTOM_CORRODIBLE) && stack.getItem() instanceof CustomCorrodible corrodible) {
			corrosion = corrodible.getCorrosion(stack);
			maxCorrosion = corrodible.getMaxCorrosion(stack);
		} else if(stack.has(DataComponentRegistry.CORROSION)) {
			maxCorrosion = MAX_CORROSION;
			corrosion = stack.get(DataComponentRegistry.CORROSION).corrosion();
		} else {
			return 1.0F;
		}
		int oneStage = maxCorrosion / CORROSION_STAGE_COUNT;
		return (-0.7F * Math.max(0, ((corrosion - oneStage) / (float)(maxCorrosion - oneStage))) + 1);
	}

	public static boolean isCorrodible(ItemStack stack) {
		return stack != null && !stack.isEmpty() && stack.is(BLItemTagProvider.CORRODIBLE);
	}

	/**
	 * Returns the dig speed of an item at the amount of corrosion of the specified item
	 */
	public static float getDestroySpeed(float normalStrength, ItemStack itemStack, BlockState blockState) {
		return normalStrength * getModifier(itemStack);
	}

	/**
	 * Returns whether corrosion is enabled
	 */
	public static boolean isCorrosionEnabled(ServerLevel level) {
		// TODO check getting level
		return level.getGameRules().getBoolean(TheBetweenlands.CORROSION_GAMERULE) && BetweenlandsConfig.useCorrosion;
	}


	/**
	 * Returns whether an entity should have items in its inventory corroded
	 */
	public static boolean shouldEntityCorrode(ServerLevel level, @Nullable Entity entity) {
		if(entity == null) return isCorrosionEnabled(level);

		// If corrosion is disabled: false
		if(!(BetweenlandsConfig.useCorrosion && level.getGameRules().getBoolean(TheBetweenlands.CORROSION_GAMERULE)))
			return false;

		// If player is in creative: false
		if(entity instanceof Player && ((Player)entity).isCreative())
			return false;

		Holder<Biome> biome = level.getBiome(entity.blockPosition());
		if(biome != null) {
			// If the biome disables corrosion: false
			if(biome.is(BLBiomeTagProvider.DISABLE_CORROSION))
				return false;

			// If the biome enables corrosion (outside of the Betweenlands): true
			if(biome.is(BLBiomeTagProvider.CORRODING_AURA))
				return true;
		}

		// If the dimension enables corrosion: true
		return level.dimensionTypeRegistration().is(BLDimensionTypeTagProvider.CORRODING_AURA);
	}


	/**
	 * Updates the corrosion on the specified item
	 */
	public static void updateCorrosion(ItemStack stack, Level level, Entity holder, int slot, boolean isHeldItem) {
		if (!(level instanceof ServerLevel serverLevel)) {
			return;
		}
		if(shouldEntityCorrode(serverLevel, holder)) {
			if(!isCorrodible(stack)) {
				return;
			}

			int corrosion = getCorrosion(stack);
			if(!isCorrosionEnabled(serverLevel)) {
				if(corrosion != 0) {
					setCorrosion(stack, 0);
				}
			} else if (corrosion < getMaximumCorrosion(stack)) {
				float probability = holder.isInWater() ? 0.0014F : 0.0007F;
				if (holder instanceof Player player) {
					probability *= (isHeldItem && !player.getMainHandItem().isEmpty() ? 2.8F : 1.0F);
					IDecayData cap = player.getData(AttachmentRegistry.DECAY);
					if(cap != null) {
						float playerCorruption = cap.getDecayLevel(player) / 20.0F;
						probability *= (float) (1 - Math.pow(playerCorruption, 2) * 0.9F);
					}
				}
				if (level.getRandom().nextFloat() < probability) {
					int coating = getCoating(stack);
					if(coating > 0) {
						setCoating(stack, coating - 1);
					} else {
						setCorrosion(stack, corrosion + 1);
					}
				}
			}
		}
	}

	/**
	 * Adds the corrosion tooltips
	 */
	public static void addCorrosionTooltips(ItemStack stack, List<Component> lines, TooltipFlag tooltipFlags, TooltipContext tooltipContext) {
		if(!isCorrodible(stack)) {
			return;
		}

		boolean advancedItemTooltips = tooltipFlags.isAdvanced();

		int tooltipIndex = 1;
		if(stack.has(DataComponentRegistry.CORROSION)) {
			MutableComponent mutableComponent = MutableComponent.create(Component.translatable("item.thebetweenlands.corrosion." + getCorrosionStage(stack)).getContents());
			if (advancedItemTooltips) {
				String corrosionInfo = " (" +
					getCorrosion(stack) +
					"/" + getMaximumCorrosion(stack) + ")";
				mutableComponent.append(corrosionInfo);
			}
			lines.add(tooltipIndex++, mutableComponent.withStyle(ChatFormatting.GRAY));
		}

		int coating = getCoating(stack);
		if(coating > 0 || advancedItemTooltips) {
			MutableComponent mutableComponent = MutableComponent.create(Component.translatable("item.thebetweenlands.coated." + getCoatingStage(stack)).getContents());
			if (advancedItemTooltips) {
				String corrosionInfo = " (" +
					coating +
					"/" + getMaximumCoating(stack) + ")";
				mutableComponent.append(corrosionInfo);
			}
			lines.add(tooltipIndex, mutableComponent.withStyle(ChatFormatting.GRAY));
		}
	}

	/**
	 * Returns the corrosion stage of the specified item. Ranges from [0, 5]
	 */
	public static int getCorrosionStage(ItemStack stack) {
		int corrosion = getCorrosion(stack);
		int maxCorrosion = getMaximumCorrosion(stack);
		if(corrosion != -1 && maxCorrosion != -1)
			return Math.min(5, (int)Math.floor((float)corrosion / (float)maxCorrosion * 6));
		return 0;
	}

	/**
	 * Returns the coating stage of the specified item. Ranges from [0, 5]
	 */
	public static int getCoatingStage(ItemStack stack) {
		int coating = getCoating(stack);
		int maxCoating = getMaximumCoating(stack);
		if(coating != -1 && maxCoating != -1)
			return Math.min(5, (int)Math.floor((float)coating / (float)maxCoating * 6));
		return 0;
	}


}
