package dev.xylonity.explosiveenhancement;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ExplosiveHandler {
    public static void spawnParticles(Level world, double x, double y, double z, float power, boolean isUnderWater, boolean didDestroyBlocks, boolean isImportant) {

        if(ExplosiveValues.debugLogs) { ExplosiveEnhancement.LOGGER.info("ExplosiveConfig has been called!"); }
        if(isUnderWater) {
            power = ExplosiveValues.dynamicUnderwater ? power : 4;
        } else {
            power = ExplosiveValues.dynamicSize ? power : 4;
        }
        y = ExplosiveValues.attemptBetterSmallExplosions && power == 1 ? y + ExplosiveValues.smallExplosionYOffset : y;
        isImportant = isImportant || ExplosiveValues.alwaysShow;
        float blastwavePower = power * 1.75f;
        float fireballPower = power * 1.25f;
        float smokePower = power * 0.4f;
        if (isUnderWater) {
            if(ExplosiveValues.showUnderwaterBlastWave) {
                world.addParticle(ExplosiveParticles.UNDERWATERBLASTWAVE.get(), isImportant, x, y + 0.5, z, blastwavePower, 0, 0);
            }
            if(ExplosiveValues.showShockwave) {
                world.addParticle(ExplosiveParticles.SHOCKWAVE.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            } else if (ExplosiveValues.showUnderwaterSparks) {
                world.addParticle(ExplosiveParticles.BLANK_SHOCKWAVE.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            }
            for(int total = ExplosiveValues.bubbleAmount; total >= 1; total--) {
                world.addParticle(ExplosiveParticles.BUBBLE.get(), isImportant, x, y, z, nextBetween(1, 7) * 0.3 * nextBetween(-1, 1), nextBetween(1, 10) * 0.1, nextBetween(1, 7) * 0.3 * nextBetween(-1, 1));
            }
            if(ExplosiveValues.showDefaultExplosionUnderwater) {
                showDefaultParticles(world, x, y, z, power, didDestroyBlocks, isImportant);
            }
        } else {
            if(ExplosiveValues.debugLogs) { ExplosiveEnhancement.LOGGER.info("Particle is being shown!"); }
            if(ExplosiveValues.showBlastWave) {
                world.addParticle(ExplosiveParticles.BLASTWAVE.get(), isImportant, x, y, z, blastwavePower, 0, 0);
            }
            if(ExplosiveValues.showFireball) {
                world.addParticle(ExplosiveParticles.FIREBALL.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            } else if (ExplosiveValues.showSparks) {
                world.addParticle(ExplosiveParticles.BLANK_FIREBALL.get(), isImportant, x, y + 0.5, z, fireballPower, isImportant ? 1 : 0, 0);
            }
            if(ExplosiveValues.showMushroomCloud) {
                world.addParticle(ExplosiveParticles.SMOKE.get(), isImportant, x, y, z, power, power * 0.25, 0);
                world.addParticle(ExplosiveParticles.SMOKE.get(), isImportant, x, y, z, power, smokePower, 0);

                world.addParticle(ExplosiveParticles.SMOKE.get(), isImportant, x, y, z, 0.15, smokePower, power);
                world.addParticle(ExplosiveParticles.SMOKE.get(), isImportant, x, y, z, -0.15, smokePower, power);
                world.addParticle(ExplosiveParticles.SMOKE.get(), isImportant, x, y, z, power, smokePower, 0.15);
                world.addParticle(ExplosiveParticles.SMOKE.get(), isImportant, x, y, z, power, smokePower, -0.15);
            }
            if(ExplosiveValues.showDefaultExplosion) {
                showDefaultParticles(world, x, y, z, power, didDestroyBlocks, isImportant);
            }
        }
        if(ExplosiveValues.debugLogs) { ExplosiveEnhancement.LOGGER.info("Particle finished!"); }
    }

    private static void showDefaultParticles(Level world, double x, double y, double z, float power, boolean didDestroyBlocks, boolean isImportant) {
        if(!(power < 2.0f) && didDestroyBlocks) {
            world.addParticle(ParticleTypes.EXPLOSION_EMITTER, isImportant, x, y, z, 1.0, 0.0, 0.0);
        } else {
            world.addParticle(ParticleTypes.EXPLOSION, isImportant, x, y, z, 1.0, 0.0, 0.0);
        }
    }

    private static int nextBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(min, max);
    }

}
