package dev.xylonity.explosiveenhancement.config;

import net.neoforged.fml.event.config.ModConfigEvent;

public class ExplosiveValues {

    public static boolean showBlastWave = ExplosiveEnhancementConfig.showBlastWave.getDefault();
    public static boolean showFireball = ExplosiveEnhancementConfig.showFireball.getDefault();
    public static boolean showMushroomCloud = ExplosiveEnhancementConfig.showMushroomCloud.getDefault();
    public static boolean showSparks = ExplosiveEnhancementConfig.showSparks.getDefault();
    public static double sparkSize = ExplosiveEnhancementConfig.sparkSize.getDefault();
    public static double sparkOpacity = ExplosiveEnhancementConfig.sparkOpacity.getDefault();
    public static boolean showDefaultExplosion = ExplosiveEnhancementConfig.showDefaultExplosion.getDefault();
    public static boolean underwaterExplosions = ExplosiveEnhancementConfig.underwaterExplosions.getDefault();
    public static boolean showShockwave = ExplosiveEnhancementConfig.showShockwave.getDefault();
    public static boolean showUnderwaterBlastWave = ExplosiveEnhancementConfig.showUnderwaterBlastWave.getDefault();
    public static int bubbleAmount = ExplosiveEnhancementConfig.bubbleAmount.getDefault();
    public static boolean showUnderwaterSparks = ExplosiveEnhancementConfig.showUnderwaterSparks.getDefault();
    public static double underwaterSparkSize = ExplosiveEnhancementConfig.underwaterSparkSize.getDefault();
    public static double underwaterSparkOpacity = ExplosiveEnhancementConfig.underwaterSparkOpacity.getDefault();
    public static boolean showDefaultExplosionUnderwater = ExplosiveEnhancementConfig.showDefaultExplosionUnderwater.getDefault();
    public static boolean dynamicSize = ExplosiveEnhancementConfig.dynamicSize.getDefault();
    public static boolean dynamicUnderwater = ExplosiveEnhancementConfig.dynamicUnderwater.getDefault();
    public static boolean attemptBetterSmallExplosions = ExplosiveEnhancementConfig.attemptBetterSmallExplosions.getDefault();
    public static double smallExplosionYOffset = ExplosiveEnhancementConfig.smallExplosionYOffset.getDefault();
    public static boolean modEnabled = ExplosiveEnhancementConfig.modEnabled.getDefault();
    public static boolean emissiveExplosion = ExplosiveEnhancementConfig.emissiveExplosion.getDefault();
    public static boolean emissiveWaterExplosion = ExplosiveEnhancementConfig.emissiveWaterExplosion.getDefault();
    public static boolean alwaysShow = ExplosiveEnhancementConfig.alwaysShow.getDefault();
    public static boolean debugLogs = ExplosiveEnhancementConfig.debugLogs.getDefault();

    public static void onConfigLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() == ExplosiveEnhancementConfig.SPEC) {
            reload();
        }

    }

    private static void reload() {
        showBlastWave = ExplosiveEnhancementConfig.showBlastWave.get();
        showFireball = ExplosiveEnhancementConfig.showFireball.get();
        showMushroomCloud = ExplosiveEnhancementConfig.showMushroomCloud.get();
        showSparks = ExplosiveEnhancementConfig.showSparks.get();
        sparkSize = ExplosiveEnhancementConfig.sparkSize.get();
        sparkOpacity = ExplosiveEnhancementConfig.sparkOpacity.get();
        showDefaultExplosion = ExplosiveEnhancementConfig.showDefaultExplosion.get();
        underwaterExplosions = ExplosiveEnhancementConfig.underwaterExplosions.get();
        showShockwave = ExplosiveEnhancementConfig.showShockwave.get();
        showUnderwaterBlastWave = ExplosiveEnhancementConfig.showUnderwaterBlastWave.get();
        bubbleAmount = ExplosiveEnhancementConfig.bubbleAmount.get();
        showUnderwaterSparks = ExplosiveEnhancementConfig.showUnderwaterSparks.get();
        underwaterSparkSize = ExplosiveEnhancementConfig.underwaterSparkSize.get();
        underwaterSparkOpacity = ExplosiveEnhancementConfig.underwaterSparkOpacity.get();
        showDefaultExplosionUnderwater = ExplosiveEnhancementConfig.showDefaultExplosionUnderwater.get();
        dynamicSize = ExplosiveEnhancementConfig.dynamicSize.get();
        dynamicUnderwater = ExplosiveEnhancementConfig.dynamicUnderwater.get();
        attemptBetterSmallExplosions = ExplosiveEnhancementConfig.attemptBetterSmallExplosions.get();
        smallExplosionYOffset = ExplosiveEnhancementConfig.smallExplosionYOffset.get();
        modEnabled = ExplosiveEnhancementConfig.modEnabled.get();
        emissiveExplosion = ExplosiveEnhancementConfig.emissiveExplosion.get();
        emissiveWaterExplosion = ExplosiveEnhancementConfig.emissiveWaterExplosion.get();
        alwaysShow = ExplosiveEnhancementConfig.alwaysShow.get();
        debugLogs = ExplosiveEnhancementConfig.debugLogs.get();
    }

}