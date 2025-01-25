package thebetweenlands.common.registries;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import thebetweenlands.common.herblore.elixir.ElixirEffectRegistry;
import thebetweenlands.common.item.consumable.*;

import java.util.List;

public class ConsumableRegistry {
	public static final Consumable ROTTEN_FOOD = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.HUNGER, 200, 1), new MobEffectInstance(MobEffects.POISON, 200, 1)))).build();
	public static final Consumable GERTS_DONUT = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1))).build();
	public static final Consumable WEEPING_BLUE_PETAL = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(ElixirEffectRegistry.EFFECT_RIPENING.get().createEffect(600, 2))).build();
	public static final Consumable WIGHT_HEART = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEAL, 1, 1))).build();
	public static final Consumable GREEN_MARSHMALLOW = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 1))).build();
	public static final Consumable PINK_MARSHMALLOW = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP, 400, 1))).build();
	public static final Consumable FORBIDDEN_FIG = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.BLINDNESS, 1200, 1), new MobEffectInstance(MobEffects.WEAKNESS, 1200, 1)))).build();
	public static final Consumable WEEPING_BLUE_PETAL_SALAD = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(ElixirEffectRegistry.EFFECT_RIPENING.get().createEffect(4200, 2))).build();
	public static final Consumable SPIRIT_FRUIT = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), new MobEffectInstance(MobEffects.ABSORPTION, 2400)))).build();
	public static final Consumable ROCK_SNOT_PEARL = Consumable.builder().onConsume(new GiveXPConsumeEffect(10)).build();
	public static final Consumable PEARLED_PEAR = Consumable.builder().onConsume(new GiveXPConsumeEffect(80)).build();
	public static final Consumable PHEROMONE_EXTRACT = Consumable.builder().onConsume(new MaskInfestationConsumeEffect(400)).build();
	public static final Consumable SWAMP_BROTH = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(ElixirEffectRegistry.EFFECT_MASKING.get().createEffect(400, 1))).build();
	public static final Consumable PEAR_CORDIAL = Consumable.builder().onConsume(new FallDamageImmunityConsumeEffect(400)).build();
	public static final Consumable SHAMANS_BREW = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(List.of(ElixirEffectRegistry.EFFECT_HUNTERSSENSE.get().createEffect(400, 1), ElixirEffectRegistry.EFFECT_CATSEYES.get().createEffect(400, 1)))).build();
	public static final Consumable LAKE_BROTH = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 400, 1))).build();
	public static final Consumable SHELL_STOCK = Consumable.builder().onConsume(new MudWalkerConsumeEffect(400)).build();
	public static final Consumable FROG_LEG_EXTRACT = Consumable.builder().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP, 400, 1))).build();
	public static final Consumable WITCH_TEA = Consumable.builder().onConsume(new WitchTeaConsumeEffect(4)).build();
}
