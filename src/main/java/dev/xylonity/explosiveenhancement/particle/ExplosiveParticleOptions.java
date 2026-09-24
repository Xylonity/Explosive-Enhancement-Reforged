package dev.xylonity.explosiveenhancement.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ExtraCodecs;

import java.util.Locale;

public record ExplosiveParticleOptions(
        ParticleType<ExplosiveParticleOptions> type,
        float scale
) implements ParticleOptions {

    private static final SimpleCommandExceptionType ERROR_INVALID_SCALE = new SimpleCommandExceptionType(Component.literal("The particle scale must be positive"));

    public static final ParticleOptions.Deserializer<ExplosiveParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {

        @Override
        public ExplosiveParticleOptions fromCommand(ParticleType<ExplosiveParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            final int start = reader.getCursor();
            final float scale = reader.readFloat();
            if (scale <= 0) {
                reader.setCursor(start);
                throw ERROR_INVALID_SCALE.createWithContext(reader);
            }

            return new ExplosiveParticleOptions(type, scale);
        }

        @Override
        public ExplosiveParticleOptions fromNetwork(ParticleType<ExplosiveParticleOptions> type, FriendlyByteBuf buf) {
            return new ExplosiveParticleOptions(type, buf.readFloat());
        }

    };

    public static Codec<ExplosiveParticleOptions> codec(ParticleType<ExplosiveParticleOptions> type) {
        // 4f is the default vanilla tnt explosion
        return ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("scale", 4f)
                .xmap(scale -> new ExplosiveParticleOptions(type, scale), ExplosiveParticleOptions::scale)
                .codec();
    }

    @Override
    public ParticleType<ExplosiveParticleOptions> getType() {
        return this.type;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buf) {
        buf.writeFloat(this.scale);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %.2f", BuiltInRegistries.PARTICLE_TYPE.getKey(this.type), this.scale);
    }

}
