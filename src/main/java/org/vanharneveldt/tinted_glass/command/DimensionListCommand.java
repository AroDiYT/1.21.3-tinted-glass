package org.vanharneveldt.tinted_glass.command;

// ...existing code imports...
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import org.vanharneveldt.tinted_glass.worldgen.custom.DimensionHandler;

public class DimensionListCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("dimensionlist")
            .executes(context -> {
                var source = context.getSource();
                source.sendSuccess(() ->
                    Component.literal(DimensionHandler.getAllDimensionKeys().toString()), false
                );
                return Command.SINGLE_SUCCESS;
            })
        );
    }
}
