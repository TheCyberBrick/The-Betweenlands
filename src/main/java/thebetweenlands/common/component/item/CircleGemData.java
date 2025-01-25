package thebetweenlands.common.component.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import thebetweenlands.common.component.entity.circlegem.CircleGemType;

import java.util.Optional;

public record CircleGemData(CircleGemType type, Optional<ResourceLocation> overrideTexture) {

	public static final CircleGemData EMPTY = new CircleGemData(CircleGemType.NONE, Optional.empty());

	public static final Codec<CircleGemData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		CircleGemType.CODEC.fieldOf("type").forGetter(CircleGemData::type),
		ResourceLocation.CODEC.optionalFieldOf("override_texture").forGetter(CircleGemData::overrideTexture)
	).apply(instance, CircleGemData::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, CircleGemData> STREAM_CODEC = StreamCodec.composite(
		CircleGemType.STREAM_CODEC, CircleGemData::type,
		ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs::optional), CircleGemData::overrideTexture,
		CircleGemData::new);

	public CircleGemData(CircleGemType type) {
		this(type, Optional.empty());
	}
}
