package org.ljieshuo.lfservermod.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.Collection;
import java.util.function.Supplier;

public class GetAddrCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder<ServerCommandSource>)
                CommandManager.literal("getaddr").
                        then(((RequiredArgumentBuilder)CommandManager.
                                argument("targets", GameProfileArgumentType.gameProfile()).
                                executes(context -> GetAddrCommand.getaddr((ServerCommandSource)context.getSource(),
                                        GameProfileArgumentType.
                                                getProfileArgument(context, "targets")))).
                                        executes(context -> GetAddrCommand.getaddr((ServerCommandSource)context.getSource(),
                                                GameProfileArgumentType.getProfileArgument(context, "targets")))));
    }
    public static int getaddr(ServerCommandSource source, Collection<GameProfile> targets) {
        for (GameProfile gameProfile : targets) {
            String name = gameProfile.getName();
            ServerPlayerEntity serverPlayerEntity = source.getServer().getPlayerManager().getPlayer(gameProfile.getId());
            if (serverPlayerEntity == null) continue;
            int dx = serverPlayerEntity.getBlockX();
            int dy = serverPlayerEntity.getBlockY();
            int dz = serverPlayerEntity.getBlockZ();
            RegistryKey<World> world = serverPlayerEntity.getEntityWorld().getRegistryKey();
            String dimension = "unknow";
            if (World.OVERWORLD.equals(world)) {
                dimension = "minecraft:overworld";
            } else if (World.NETHER.equals(world)) {
                dimension = "minecraft:nether";
            } else if (World.END.equals(world)) {
                dimension = "minecraft:end";
            }
            String finalDimension = dimension;
            source.sendFeedback(new Supplier<Text>() {
                @Override
                public Text get() {
                    return Text.of("[name:\"" + name +"\", x:" + dx + ", y:\" + dx + \", z:\" + dx + \", dim:" + finalDimension + "]");
                }
            }, false);
        }
        return targets.size();
    }
}
