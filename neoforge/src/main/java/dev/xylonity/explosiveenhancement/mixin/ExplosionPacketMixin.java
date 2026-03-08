package dev.xylonity.explosiveenhancement.mixin;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.WeightedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ServerLevel.class)
public abstract class ExplosionPacketMixin {

    @ModifyArgs(
            method = "explode",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/network/protocol/game/ClientboundExplodePacket;<init>(Lnet/minecraft/world/phys/Vec3;FILjava/util/Optional;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;Lnet/minecraft/util/random/WeightedList;)V"
            )
    )
    private void explosiveenhancement$replaceExplosionPacketArgs(Args args) {
        if (ExplosiveValues.modEnabled && !ExplosiveValues.showDefaultExplosion) {
            args.set(4, ExplosiveParticles.EMPTY.get());
            args.set(6, WeightedList.of());
        }

    }

}