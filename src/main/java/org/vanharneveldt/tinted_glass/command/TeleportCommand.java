package org.vanharneveldt.tinted_glass.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.vanharneveldt.tinted_glass.registry.DimensionRegistry;
import org.vanharneveldt.tinted_glass.worldgen.custom.DimensionHandler;

public class TeleportCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tgdim")
            .requires(source -> source.hasPermission(2))
            .executes(TeleportCommand::execute));
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() instanceof ServerPlayer player) {
            DimensionHandler.teleportToDimension(player, DimensionRegistry.CUSTOM_DIMENSION);
            context.getSource().sendSuccess(() -> Component.literal("Teleported to custom dimension!"), false);
            return 1;
        }
        return 0;
    }
}
