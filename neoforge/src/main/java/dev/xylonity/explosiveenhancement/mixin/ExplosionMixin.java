package dev.xylonity.explosiveenhancement.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.api.ExplosiveConfig;
import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow @Final private float radius;

    @Shadow public abstract boolean interactsWithBlocks();

    @Shadow @Final private ParticleOptions smallExplosionParticles;
    @Shadow @Final private ParticleOptions largeExplosionParticles;

    @WrapWithCondition(method = "finalizeExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private boolean replaceExplosionParticles(Level level, ParticleOptions particle, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        if (!ExplosiveValues.modEnabled || this.radius <= 0) {
            return true;
        }

        // Wind charges keep their vanilla gust particles
        if (ParticleTypes.GUST_EMITTER_SMALL == this.smallExplosionParticles && ParticleTypes.GUST_EMITTER_LARGE == this.largeExplosionParticles) {
            return true;
        }

        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("finalizeExplosion has been called!");
        }

        final boolean isUnderWater = ExplosiveValues.underwaterExplosions && level.getFluidState(BlockPos.containing(x, y, z)).is(FluidTags.WATER);
        if (isUnderWater && ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("particle is underwater!");
        }

        ExplosiveConfig.spawnParticles(level, x, y, z, this.radius, isUnderWater, this.interactsWithBlocks());

        return false;
    }

}