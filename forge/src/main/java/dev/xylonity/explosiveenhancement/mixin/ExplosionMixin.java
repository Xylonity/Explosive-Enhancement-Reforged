package dev.xylonity.explosiveenhancement.mixin;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.api.ExplosiveConfig;
import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;

@Mixin(ServerLevel.class)
public abstract class ExplosionMixin {

    @Shadow public abstract ServerLevel getLevel();

    @Unique
    private boolean explosiveenhancement$isWindCharge;

    @Inject(method = "explode", at = @At("HEAD"))
    private void explosiveenhancement$onExplodeHead(@Nullable Entity source, @Nullable DamageSource p_1, @Nullable ExplosionDamageCalculator p_2, double p_3, double p_4, double p_5, float p_6, boolean p_7, Level.ExplosionInteraction p_8, ParticleOptions p_9, ParticleOptions p_10, Holder<SoundEvent> p_11, CallbackInfo ci) {
        explosiveenhancement$isWindCharge = (source instanceof WindCharge);
    }

    @ModifyArg(method = "explode", at = @At(value  = "INVOKE", target = "Lnet/minecraft/network/protocol/game/ClientboundExplodePacket;<init>(Lnet/minecraft/world/phys/Vec3;Ljava/util/Optional;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/Holder;)V"), index = 2)
    private ParticleOptions explosiveenhancement$stripVanillaParticles(ParticleOptions original) {
        if (ExplosiveValues.modEnabled && !explosiveenhancement$isWindCharge) {
            return ExplosiveParticles.EMPTY.get();
        }

        return original;
    }

    @Inject(method = "explode", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/ServerExplosion;isSmall()Z"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void explosiveenhancement$spawnCustomParticles(Entity source, DamageSource damageSource, ExplosionDamageCalculator dmgCalc, double x, double y, double z, float radius, boolean causesFire, Level.ExplosionInteraction interaction, ParticleOptions smallP, ParticleOptions largeP, Holder<SoundEvent> sound, CallbackInfo ci, Explosion.BlockInteraction blockInteraction, Vec3 center, ServerExplosion serverExplosion) {
        if (!ExplosiveValues.modEnabled || explosiveenhancement$isWindCharge) return;

        boolean underwater = ExplosiveValues.underwaterExplosions && getLevel().getFluidState(BlockPos.containing(x, y, z)).is(FluidTags.WATER);
        boolean interactsWithBlocks = serverExplosion.interactsWithBlocks();

        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("Explosion has been called!");
            if (underwater) {
                ExplosiveEnhancement.LOGGER.info("The explosion is underwater!");
            }
        }

        ExplosiveConfig.spawnParticles(getLevel(), x, y, z, radius, underwater, interactsWithBlocks, false);
    }

}