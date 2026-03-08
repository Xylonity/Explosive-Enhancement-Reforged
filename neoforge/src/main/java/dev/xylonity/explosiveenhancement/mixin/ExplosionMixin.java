package dev.xylonity.explosiveenhancement.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.ExplosiveHandler;
import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ServerLevel.class)
public abstract class ExplosionMixin {

    @Inject(
            method = "explode",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/level/ServerExplosion;isSmall()Z"
            )
    )
    private void explosiveenhancement$spawnCustomParticles(
            @Nullable Entity source,
            @Nullable DamageSource damageSource,
            @Nullable ExplosionDamageCalculator damageCalculator,
            double x,
            double y,
            double z,
            float radius,
            boolean causesFire,
            Level.ExplosionInteraction interaction,
            ParticleOptions smallExplosionParticles,
            ParticleOptions largeExplosionParticles,
            WeightedList<ExplosionParticleInfo> blockParticles,
            Holder<SoundEvent> sound,
            CallbackInfo ci,
            @Local ServerExplosion serverExplosion
    ) {
        if (!ExplosiveValues.modEnabled || source instanceof WindCharge) {
            return;
        }

        ServerLevel level = (ServerLevel) (Object) this;
        BlockPos pos = BlockPos.containing(x, y, z);

        boolean underwater = ExplosiveValues.underwaterExplosions && level.getFluidState(pos).is(FluidTags.WATER);
        boolean interactsWithBlocks = ((ServerExplosionAccessor) serverExplosion).explosiveenhancement$invokeInteractsWithBlocks();

        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info(
                    "Explosion detected at ({}, {}, {}), radius={}, underwater={}, interactsWithBlocks={}",
                    x, y, z, radius, underwater, interactsWithBlocks
            );

        }

        ExplosiveHandler.spawnParticles(level, x, y, z, radius, underwater, interactsWithBlocks, false);
    }

}