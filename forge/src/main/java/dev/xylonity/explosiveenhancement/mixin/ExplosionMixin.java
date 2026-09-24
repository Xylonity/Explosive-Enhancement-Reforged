package dev.xylonity.explosiveenhancement.mixin;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.api.ExplosiveConfig;
import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.network.ExplosionSourceTracker;
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
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow @Final private Level level;
    @Shadow @Final private double x;
    @Shadow @Final private double y;
    @Shadow @Final private double z;
    @Shadow @Final private float radius;

    @Shadow public abstract boolean interactsWithBlocks();

    @Shadow @Final private ParticleOptions smallExplosionParticles;
    @Shadow @Final private ParticleOptions largeExplosionParticles;

    @ModifyVariable(method = "finalizeExplosion", at = @At("HEAD"), argsOnly = true)
    private boolean replaceExplosionParticles(boolean spawnParticles) {
        if (spawnParticles && this.level.isClientSide && ExplosionSourceTracker.isBlacklisted(this.x, this.y, this.z)) {
            return spawnParticles;
        }

        if (!spawnParticles || !ExplosiveValues.modEnabled || this.radius <= 0) {
            return spawnParticles;
        }

        // Wind charges keep their vanilla gust particles
        if (ParticleTypes.GUST_EMITTER_SMALL == this.smallExplosionParticles && ParticleTypes.GUST_EMITTER_LARGE == this.largeExplosionParticles) {
            return spawnParticles;
        }

        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("finalizeExplosion has been called!");
        }

        final boolean isUnderWater = ExplosiveValues.underwaterExplosions && this.level.getFluidState(BlockPos.containing(this.x, this.y, this.z)).is(FluidTags.WATER);
        if (isUnderWater && ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("particle is underwater!");
        }

        ExplosiveConfig.spawnParticles(this.level, this.x, this.y, this.z, this.radius, isUnderWater, this.interactsWithBlocks());

        return false;
    }

}