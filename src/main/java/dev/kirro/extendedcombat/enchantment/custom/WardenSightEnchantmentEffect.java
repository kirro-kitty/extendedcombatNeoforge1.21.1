package dev.kirro.extendedcombat.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public record WardenSightEnchantmentEffect(EnchantmentValueEffect wardenSight) {
    public static final Codec<WardenSightEnchantmentEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EnchantmentValueEffect.CODEC.fieldOf("warden_sight").forGetter(WardenSightEnchantmentEffect::wardenSight)
    ).apply(instance, WardenSightEnchantmentEffect::new));
}
