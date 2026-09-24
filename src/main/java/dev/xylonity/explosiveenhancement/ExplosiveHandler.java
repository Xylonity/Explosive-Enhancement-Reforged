package dev.xylonity.explosiveenhancement;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public class ExplosiveHandler {

    public static void spawnParticles(Level world, double x, double y, double z, float power, boolean isUnderWater, boolean didDestroyBlocks, boolean isImportant) {

        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("ExplosiveConfig has been called!");
        }

        // Some mods use explosions of radius 0 as a visual effect, which would give invisible particles
        if (power <= 0) {
            return;
        }

        if (isUnderWater) {
            power = ExplosiveValues.dynamicUnderwater ? power : 4;
        }
        else {
            power = ExplosiveValues.dynamicSize ? power : 4;
        }

        y = ExplosiveValues.attemptBetterSmallExplosions && power == 1 ? y + ExplosiveValues.smallExplosionYOffset : y;
        isImportant = isImportant || ExplosiveValues.alwaysShow;

        if (isUnderWater) {
            if (ExplosiveValues.showUnderwaterBlastWave) {
                world.addParticle(ExplosiveParticles.UNDERWATERBLASTWAVE.get().withScale(power), isImportant, x, y + 0.5, z, 0, 0, 0);
            }

            if (ExplosiveValues.showShockwave) {
                world.addParticle(ExplosiveParticles.SHOCKWAVE.get().withScale(power), isImportant, x, y + 0.5, z, 0, 0, 0);
            }
            else if (ExplosiveValues.showUnderwaterSparks) {
                world.addParticle(ExplosiveParticles.BLANK_SHOCKWAVE.get().withScale(power), isImportant, x, y + 0.5, z, 0, 0, 0);
            }

            for (int total = ExplosiveValues.bubbleAmount; total >= 1; total--) {
                world.addParticle(ExplosiveParticles.BUBBLE.get(), isImportant, x, y, z, 0, 0, 0);
            }

            if (ExplosiveValues.showDefaultExplosionUnderwater) {
                showDefaultParticles(world, x, y, z, power, didDestroyBlocks, isImportant);
            }

        }
        else {
            if (ExplosiveValues.debugLogs) {
                ExplosiveEnhancement.LOGGER.info("Particle is being shown!");
            }

            if (ExplosiveValues.showBlastWave) {
                world.addParticle(ExplosiveParticles.BLASTWAVE.get().withScale(power), isImportant, x, y, z, 0, 0, 0);
            }

            if (ExplosiveValues.showFireball) {
                world.addParticle(ExplosiveParticles.FIREBALL.get().withScale(power), isImportant, x, y + 0.5, z, 0, 0, 0);
            }
            else if (ExplosiveValues.showSparks) {
                world.addParticle(ExplosiveParticles.BLANK_FIREBALL.get().withScale(power), isImportant, x, y + 0.5, z, 0, 0, 0);
            }

            if (ExplosiveValues.showMushroomCloud) {
                spawnMushroomCloud(world, x, y, z, power, isImportant);
            }

            if (ExplosiveValues.showDefaultExplosion) {
                showDefaultParticles(world, x, y, z, power, didDestroyBlocks, isImportant);
            }

        }

        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("Particle finished!");
        }

    }

    private static void spawnMushroomCloud(Level world, double x, double y, double z, float power, boolean isImportant) {
        final double stem = power * 0.25 / 1.85;
        final double rise = power * 0.4 / 1.85;
        final double spread = power * 0.075;

        world.addParticle(ExplosiveParticles.SMOKE.get().withScale(power), isImportant, x, y, z, 0, stem, 0);
        world.addParticle(ExplosiveParticles.SMOKE.get().withScale(power), isImportant, x, y, z, 0, rise, 0);

        world.addParticle(ExplosiveParticles.SMOKE.get().withScale(power), isImportant, x, y, z, spread, rise, 0);
        world.addParticle(ExplosiveParticles.SMOKE.get().withScale(power), isImportant, x, y, z, -spread, rise, 0);
        world.addParticle(ExplosiveParticles.SMOKE.get().withScale(power), isImportant, x, y, z, 0, rise, spread);
        world.addParticle(ExplosiveParticles.SMOKE.get().withScale(power), isImportant, x, y, z, 0, rise, -spread);
    }

    private static void showDefaultParticles(Level world, double x, double y, double z, float power, boolean didDestroyBlocks, boolean isImportant) {
        if (!(power < 2.0f) && didDestroyBlocks) {
            world.addParticle(ParticleTypes.EXPLOSION_EMITTER, isImportant, x, y, z, 1.0, 0.0, 0.0);
        }
        else {
            world.addParticle(ParticleTypes.EXPLOSION, isImportant, x, y, z, 1.0, 0.0, 0.0);
        }

    }

}