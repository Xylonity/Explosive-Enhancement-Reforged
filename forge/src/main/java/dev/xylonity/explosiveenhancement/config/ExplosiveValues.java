package dev.xylonity.explosiveenhancement.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Provides auxiliary configuration values as a contingency mechanism, in the event that
 * the configuration file is not detected. This ensures the mod can continue operating
 * under predefined conditions while also supporting dynamic hot-reloading capabilities.
 */
public class ExplosiveValues {

    static Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("explosiveenhancement.toml");
    private static final boolean V = Files.exists(CONFIG_PATH);

    public static boolean showBlastWave = V ? ExplosiveEnhancementConfig.showBlastWave.get() : true;
    public static boolean showFireball = V ? ExplosiveEnhancementConfig.showFireball.get() : true;
    public static boolean showMushroomCloud = V ? ExplosiveEnhancementConfig.showMushroomCloud.get() : true;
    public static boolean showSparks = V ? ExplosiveEnhancementConfig.showSparks.get() : true;
    public static double sparkSize = V ? ExplosiveEnhancementConfig.sparkSize.get() : 5.3;
    public static double sparkOpacity = V ? ExplosiveEnhancementConfig.sparkOpacity.get() : 0.70;
    public static boolean showDefaultExplosion = V ? ExplosiveEnhancementConfig.showDefaultExplosion.get() : false;
    public static boolean underwaterExplosions = V ? ExplosiveEnhancementConfig.underwaterExplosions.get() : true;
    public static boolean showShockwave = V ? ExplosiveEnhancementConfig.showShockwave.get() : true;
    public static boolean showUnderwaterBlastWave = V ? ExplosiveEnhancementConfig.showUnderwaterBlastWave.get() : true;
    public static int bubbleAmount = V ? ExplosiveEnhancementConfig.bubbleAmount.get() : 50;
    public static boolean showUnderwaterSparks = V ? ExplosiveEnhancementConfig.showUnderwaterSparks.get() : false;
    public static double underwaterSparkSize = V ? ExplosiveEnhancementConfig.underwaterSparkSize.get() : 4.0;
    public static double underwaterSparkOpacity = V ? ExplosiveEnhancementConfig.underwaterSparkOpacity.get() : 0.30;
    public static boolean showDefaultExplosionUnderwater = V ? ExplosiveEnhancementConfig.showDefaultExplosionUnderwater.get() : false;
    public static boolean dynamicSize = V ? ExplosiveEnhancementConfig.dynamicSize.get() : true;
    public static boolean dynamicUnderwater = V ? ExplosiveEnhancementConfig.dynamicUnderwater.get() : true;
    public static double explosionScale = V ? ExplosiveEnhancementConfig.explosionScale.get() : 1.0;
    public static double explosionDuration = V ? ExplosiveEnhancementConfig.explosionDuration.get() : 1.0;
    public static double explosionOpacity = V ? ExplosiveEnhancementConfig.explosionOpacity.get() : 1.0;
    public static boolean attemptBetterSmallExplosions = V ? ExplosiveEnhancementConfig.attemptBetterSmallExplosions.get() : true;
    public static double smallExplosionYOffset = V ? ExplosiveEnhancementConfig.smallExplosionYOffset.get() : -0.5;
    public static boolean modEnabled = V ? ExplosiveEnhancementConfig.modEnabled.get() : true;
    public static boolean emissiveExplosion = V ? ExplosiveEnhancementConfig.emissiveExplosion.get() : true;
    public static boolean emissiveWaterExplosion = V ? ExplosiveEnhancementConfig.emissiveWaterExplosion.get() : true;
    public static boolean alwaysShow = V ? ExplosiveEnhancementConfig.alwaysShow.get() : false;
    public static Set<ResourceLocation> entityBlacklist = V ? toEntityIds(ExplosiveEnhancementConfig.entityBlacklist.get()) : Set.of();
    public static boolean debugLogs = V ? ExplosiveEnhancementConfig.debugLogs.get() : false;

    private static Set<ResourceLocation> toEntityIds(List<? extends String> ids) {
        return ids.stream().map(ResourceLocation::tryParse).filter(Objects::nonNull).collect(Collectors.toUnmodifiableSet());
    }

}
