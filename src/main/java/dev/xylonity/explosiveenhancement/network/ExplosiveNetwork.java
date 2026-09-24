package dev.xylonity.explosiveenhancement.network;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ExplosiveNetwork {

    private static final String PROTOCOL_VERSION = "2";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ExplosiveEnhancement.MOD_ID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        CHANNEL.messageBuilder(ExplosionSourcePacket.class, 0, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ExplosionSourcePacket::encode)
                .decoder(ExplosionSourcePacket::decode)
                .consumerNetworkThread(ExplosionSourcePacket::handle)
                .add();
    }

}
