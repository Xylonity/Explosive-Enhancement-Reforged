package dev.xylonity.explosiveenhancement.network;

import dev.xylonity.explosiveenhancement.ExplosiveEnhancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.SimpleChannel;

public class ExplosiveNetwork {

    public static final SimpleChannel CHANNEL = ChannelBuilder
            .named(ResourceLocation.fromNamespaceAndPath(ExplosiveEnhancement.MOD_ID, "main_channel"))
            .networkProtocolVersion(2)
            .simpleChannel();

    public static void register() {
        CHANNEL.messageBuilder(ExplosionSourcePacket.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ExplosionSourcePacket::encode)
                .decoder(ExplosionSourcePacket::decode)
                .consumerNetworkThread(ExplosionSourcePacket::handle)
                .add();
    }

}
