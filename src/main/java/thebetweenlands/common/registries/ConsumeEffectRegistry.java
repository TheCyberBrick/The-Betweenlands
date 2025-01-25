package thebetweenlands.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.item.consumable.*;

public class ConsumeEffectRegistry {

	public static final DeferredRegister<ConsumeEffect.Type<?>> CONSUME_EFFECTS = DeferredRegister.create(Registries.CONSUME_EFFECT_TYPE, TheBetweenlands.ID);

	public static final DeferredHolder<ConsumeEffect.Type<?>, ConsumeEffect.Type<FallDamageImmunityConsumeEffect>> FALL_DAMAGE_IMMUNITY = CONSUME_EFFECTS.register("fall_damage_immunity", () -> new ConsumeEffect.Type<>(FallDamageImmunityConsumeEffect.CODEC, FallDamageImmunityConsumeEffect.STREAM_CODEC));
	public static final DeferredHolder<ConsumeEffect.Type<?>, ConsumeEffect.Type<GiveXPConsumeEffect>> GIVE_XP = CONSUME_EFFECTS.register("fall_damage_immunity", () -> new ConsumeEffect.Type<>(GiveXPConsumeEffect.CODEC, GiveXPConsumeEffect.STREAM_CODEC));
	public static final DeferredHolder<ConsumeEffect.Type<?>, ConsumeEffect.Type<MaskInfestationConsumeEffect>> MASK_INFESTATION = CONSUME_EFFECTS.register("fall_damage_immunity", () -> new ConsumeEffect.Type<>(MaskInfestationConsumeEffect.CODEC, MaskInfestationConsumeEffect.STREAM_CODEC));
	public static final DeferredHolder<ConsumeEffect.Type<?>, ConsumeEffect.Type<MudWalkerConsumeEffect>> MUD_WALKER = CONSUME_EFFECTS.register("fall_damage_immunity", () -> new ConsumeEffect.Type<>(MudWalkerConsumeEffect.CODEC, MudWalkerConsumeEffect.STREAM_CODEC));
	public static final DeferredHolder<ConsumeEffect.Type<?>, ConsumeEffect.Type<WitchTeaConsumeEffect>> WITCH_TEA = CONSUME_EFFECTS.register("fall_damage_immunity", () -> new ConsumeEffect.Type<>(WitchTeaConsumeEffect.CODEC, WitchTeaConsumeEffect.STREAM_CODEC));

}
