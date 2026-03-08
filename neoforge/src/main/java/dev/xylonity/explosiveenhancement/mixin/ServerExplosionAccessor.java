package dev.xylonity.explosiveenhancement.mixin;

import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerExplosion.class)
public interface ServerExplosionAccessor {

    @Invoker("interactsWithBlocks")
    boolean explosiveenhancement$invokeInteractsWithBlocks();

}