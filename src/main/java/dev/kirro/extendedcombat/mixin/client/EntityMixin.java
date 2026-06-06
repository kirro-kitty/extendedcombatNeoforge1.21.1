package dev.kirro.extendedcombat.mixin.client;

import dev.kirro.extendedcombat.behavior.ability.WardenSightBehavior;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class EntityMixin {
    /*@Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
    private void extendedcombat$wardenSight(CallbackInfoReturnable<Boolean> cir) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        if (player == null) {
            return;
        }
        WardenSightBehavior wardenSight = player.getExistingDataOrNull(ModDataAttachments.WARDEN_SIGHT);
        if (wardenSight != null) {
            if ((Object) this instanceof LivingEntity livingEntity) {
                cir.setReturnValue(wardenSight.getVisibility(livingEntity, player));
            } else if (player != wardenSight.getPlayer()) {
                cir.setReturnValue(wardenSight.getVisibility((LivingEntity) (Object) this, player));
            }
        }
    }*/

    @Shadow
    @Nullable
    public LocalPlayer player;

    @Inject(method = "shouldEntityAppearGlowing", at = @At("HEAD"), cancellable = true)
    private void extendedcombat$wardenSight(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (player == null) {
            return;
        }
        WardenSightBehavior wardenSight = player.getData(ModDataAttachments.WARDEN_SIGHT);
        if (entity instanceof LivingEntity livingEntity) {
            cir.setReturnValue(wardenSight.getVisibility(livingEntity, player));
        } else if (entity instanceof Player distPlayer) {
            cir.setReturnValue(wardenSight.getVisibility(player, distPlayer));
        }
    }
}
