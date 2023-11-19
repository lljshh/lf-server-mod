package org.ljieshuo.lfservermod;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import org.ljieshuo.lfservermod.command.GetAddrCommand;

public class CommandManager {
    private static CommandDispatcher<ServerCommandSource> dispatcher;
    CommandManager() {
        dispatcher = new CommandDispatcher();
    }
    public static void register() {
        GetAddrCommand.register(dispatcher);
    }
}
