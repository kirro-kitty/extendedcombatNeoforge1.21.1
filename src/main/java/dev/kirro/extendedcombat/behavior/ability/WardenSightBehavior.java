package dev.kirro.extendedcombat.behavior.ability;

import dev.kirro.extendedcombat.api.Ability;
import dev.kirro.extendedcombat.api.TickingAttachment;
import dev.kirro.extendedcombat.data.ModDataComponents;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.tags.ModItemTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class WardenSightBehavior implements TickingAttachment, Ability {
    private final Player player;
    private boolean visible = false;

    public WardenSightBehavior(Player player) {
        this.player = player;
    }

    @Override
    public EquipmentSlot slot() {
        return EquipmentSlot.HEAD;
    }

    public int level() {
        return this.getLevel(player, slot(), ModEnchantmentEffects.WARDEN_SIGHT.get());
    }

    @Override
    public void tick() {
        ItemStack head = slotItem(player);
        if (!head.isEmpty() && head.is(ModItemTags.ECHO_STEEL_WEARABLES)
                && level() > 0
                && !head.getOrDefault(ModDataComponents.HIDDEN, true)) {
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 0, false, false, false));
        }
    }

    @Override
    public void clientTick() {
        tick();
        ItemStack head = slotItem(player);
        setVisible(!head.isEmpty() && head.is(ModItemTags.ECHO_STEEL_WEARABLES)
                && level() > 0
                && !head.getOrDefault(ModDataComponents.HIDDEN, true));
    }

    public boolean getVisibility(LivingEntity entity, Player player) {
        ItemStack head = slotItem(this.player);
        if (!head.isEmpty() && level() > 0 && visible()) {
            return (entity != player && entity != this.player) || player != this.player;
        }

        return false;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean visible() {
        return this.visible;
    }

    public Player getPlayer() {
        return this.player;
    }
}
