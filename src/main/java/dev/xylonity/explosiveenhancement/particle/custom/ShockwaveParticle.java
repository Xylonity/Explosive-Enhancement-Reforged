package dev.xylonity.explosiveenhancement.particle.custom;

import dev.xylonity.explosiveenhancement.config.ExplosiveValues;
import dev.xylonity.explosiveenhancement.particle.ExplosiveParticleOptions;
import dev.xylonity.explosiveenhancement.registry.ExplosiveParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShockwaveParticle extends TextureSheetParticle {

    private final SpriteSet sprites;
    private final float scale;

    ShockwaveParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteProvider, float scale) {
        super(world, x, y, z);
        this.sprites = spriteProvider;
        this.scale = scale;
        this.quadSize = scale * 1.25F;
        this.lifetime = 9 + (int) (this.quadSize / 5);
        this.setParticleSpeed(0D, 0D, 0D);
        this.setSpriteFromAge(spriteProvider);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
        }
        else {
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if(this.age >= this.lifetime * 0.65 && ExplosiveValues.showUnderwaterSparks) {
                Minecraft.getInstance().particleEngine.createParticle(ExplosiveParticles.UNDERWATERSPARKS.get().withScale(this.scale), this.x, this.y, this.z, 0, 0, 0);
            }

            this.setSpriteFromAge(this.sprites);
        }

    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getLightColor(float pPartialTick) {
        return ExplosiveValues.emissiveWaterExplosion ? 15728880 : super.getLightColor(pPartialTick);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<ExplosiveParticleOptions> {

        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(ExplosiveParticleOptions options, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new ShockwaveParticle(level, x, y, z, this.sprites, options.scale());
        }

    }

}