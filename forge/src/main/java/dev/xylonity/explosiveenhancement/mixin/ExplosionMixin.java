package dev.xylonity.explosiveenhancement.mixin;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import dev.xylonity.explosiveenhancement.api.ExplosiveConfig;
import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow @Final private Level level;
    @Shadow @Final private double x;
    @Shadow @Final private double y;
    @Shadow @Final private double z;
    @Shadow @Final private float radius;
    private boolean isUnderWater = false;
    @Shadow public abstract boolean interactsWithBlocks();

    @Inject(method = "finalizeExplosion", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"), cancellable = true)
    private void finalizeExplosion(boolean pSpawnParticles, CallbackInfo ci) {
        if (ExplosiveValues.modEnabled) {
            if (ExplosiveValues.debugLogs) {
                ExplosiveEnhancement.LOGGER.info("finalizeExplosion has been called!");
            }
            BlockPos pos = BlockPos.containing(this.x, this.y, this.z);
            if (ExplosiveValues.underwaterExplosions && this.level.getFluidState(pos).is(FluidTags.WATER)) {
                isUnderWater = true;
                if (ExplosiveValues.debugLogs) {
                    ExplosiveEnhancement.LOGGER.info("particle is underwater!");
                }
            }
            ExplosiveConfig.spawnParticles(level, x, y, z, radius, isUnderWater, interactsWithBlocks());
            ci.cancel();
        }
    }

}
