package thebetweenlands.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import thebetweenlands.api.recipes.MortarRecipe;
import thebetweenlands.common.registries.RecipeRegistry;

public class MortarGrindRecipe extends MortarRecipe {

	public MortarGrindRecipe(Ingredient input, ItemStack result) {
		super(input, result);
	}

	@Override
	public RecipeSerializer<MortarGrindRecipe> getSerializer() {
		return RecipeRegistry.MORTAR_GRIND_SERIALIZER.get();
	}

	public static class Serializer implements RecipeSerializer<MortarGrindRecipe> {

		public static final MapCodec<MortarGrindRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC.fieldOf("input").forGetter(MortarGrindRecipe::input),
			ItemStack.STRICT_CODEC.fieldOf("result").forGetter(MortarGrindRecipe::result)
		).apply(instance, MortarGrindRecipe::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, MortarGrindRecipe> STREAM_CODEC = StreamCodec.composite(
			Ingredient.CONTENTS_STREAM_CODEC, MortarGrindRecipe::input,
			ItemStack.STREAM_CODEC, MortarGrindRecipe::result,
			MortarGrindRecipe::new);

		@Override
		public MapCodec<MortarGrindRecipe> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, MortarGrindRecipe> streamCodec() {
			return STREAM_CODEC;
		}
	}
}
