package dev.xylonity.explosiveenhancement;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

import java.util.Random;
import java.util.function.BiConsumer;

public class ExplosiveHandler {
    public static void spawnParticles(ServerLevel world, double x, double y, double z, float power, boolean isUnderWater, boolean didDestroyBlocks, boolean isImportant) {
        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("ExplosiveHandler has been called!");
        }

        if (isUnderWater) {
            power = ExplosiveValues.dynamicUnderwater ? power : 4;
        } else {
            power = ExplosiveValues.dynamicSize ? power : 4;
        }

        if (ExplosiveValues.attemptBetterSmallExplosions && power == 1) {
            y += ExplosiveValues.smallExplosionYOffset;
        }

        isImportant = isImportant || ExplosiveValues.alwaysShow;

        float blastwavePower = power * 1.75f;
        float fireballPower = power * 1.25f;
        float smokePower = power * 0.40f;

        final int COUNT_DIRECTIONAL = 0;
        final double SPEED = 1.0;

        boolean finalIsImportant = isImportant;
        boolean finalIsImportant1 = isImportant;
        double finalY = y;
        BiConsumer<ParticleOptions, Vec3> send = (type, vel) ->
                world.sendParticles(type, finalIsImportant, finalIsImportant1, x, finalY, z, COUNT_DIRECTIONAL, vel.x, vel.y, vel.z, SPEED);

        if (isUnderWater) {
            if (ExplosiveValues.showUnderwaterBlastWave) {
                send.accept(ExplosiveParticles.UNDERWATERBLASTWAVE.get(),
                        new Vec3(blastwavePower, 0, 0));
            }
            if (ExplosiveValues.showShockwave) {
                send.accept(ExplosiveParticles.SHOCKWAVE.get(),
                        new Vec3(fireballPower, isImportant ? 1 : 0, 0));
            } else if (ExplosiveValues.showUnderwaterSparks) {
                send.accept(ExplosiveParticles.BLANK_SHOCKWAVE.get(),
                        new Vec3(fireballPower, isImportant ? 1 : 0, 0));
            }
            for (int i = 0; i < ExplosiveValues.bubbleAmount; i++) {
                Vec3 v = new Vec3(
                        nextBetween(-7, 7) * 0.3,
                        nextBetween(1, 10) * 0.1,
                        nextBetween(-7, 7) * 0.3
                );
                send.accept(ExplosiveParticles.BUBBLE.get(), v);
            }
            if (ExplosiveValues.showDefaultExplosionUnderwater) {
                showDefaultParticles(world, x, y, z, power, didDestroyBlocks, isImportant);
            }
        } else {
            if (ExplosiveValues.debugLogs) {
                ExplosiveEnhancement.LOGGER.info("Particle is being shown!");
            }
            if (ExplosiveValues.showBlastWave) {
                send.accept(ExplosiveParticles.BLASTWAVE.get(),
                        new Vec3(blastwavePower, 0, 0));
            }
            if (ExplosiveValues.showFireball) {
                send.accept(ExplosiveParticles.FIREBALL.get(),
                        new Vec3(fireballPower, isImportant ? 1 : 0, 0));
            } else if (ExplosiveValues.showSparks) {
                send.accept(ExplosiveParticles.BLANK_FIREBALL.get(),
                        new Vec3(fireballPower, isImportant ? 1 : 0, 0));
            }
            if (ExplosiveValues.showMushroomCloud) {
                send.accept(ExplosiveParticles.SMOKE.get(), new Vec3(power, smokePower, 0));
                send.accept(ExplosiveParticles.SMOKE.get(), new Vec3(power, power * 0.25, 0));
                send.accept(ExplosiveParticles.SMOKE.get(), new Vec3( 0.15, smokePower, power));
                send.accept(ExplosiveParticles.SMOKE.get(), new Vec3(-0.15, smokePower, power));
                send.accept(ExplosiveParticles.SMOKE.get(), new Vec3(power, smokePower,  0.15));
                send.accept(ExplosiveParticles.SMOKE.get(), new Vec3(power, smokePower, -0.15));
            }
            if (ExplosiveValues.showDefaultExplosion) {
                showDefaultParticles(world, x, y, z, power, didDestroyBlocks, isImportant);
            }
        }

        if (ExplosiveValues.debugLogs) {
            ExplosiveEnhancement.LOGGER.info("Particle finished!");
        }
    }

    private static void showDefaultParticles(ServerLevel world, double x, double y, double z, float power, boolean didDestroyBlocks, boolean isImportant) {

        ParticleOptions type = (! (power < 2.0f) && didDestroyBlocks) ? ParticleTypes.EXPLOSION_EMITTER : ParticleTypes.EXPLOSION;

        world.sendParticles(type, isImportant, isImportant, x, y, z, 1, 0, 0, 0, 0);
    }

    private static int nextBetween(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

}

