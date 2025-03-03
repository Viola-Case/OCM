package com.violacase.ocm;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * @Author ViolaCase
 *
 */

import static com.violacase.ocm.OCM.MODID;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("darkcheck")
                .executes(ModCommands::darkCheck));
        dispatcher.register(Commands.literal("darkset")
                .then(Commands.argument("bool", BoolArgumentType.bool())
                .executes(commandContext -> {
                            return darkSet(commandContext, BoolArgumentType.getBool(commandContext, "bool"));
                        } )));
        dispatcher.register(Commands.literal("cowsay").then(Commands.argument("message", StringArgumentType.string())
                .executes(commandContext -> {
                    return cowSay(commandContext, StringArgumentType.getString(commandContext, "message"));
                })));
        dispatcher.register(Commands.literal("ricefight").
                executes(ModCommands::riceFight));

    }


    static int darkCheck(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player) {
            Player player = (Player) command.getSource().getEntity();
//            player.sendSystemMessage(Component.literal("Dark is set to: " + Boolean.toString(Dawnforge.darkData.IsDark)));
            player.sendSystemMessage(Component.literal("dark check success"));
        }

        return Command.SINGLE_SUCCESS;
    }

    static int darkSet(CommandContext<CommandSourceStack> command, boolean argument) {
        if (command.getSource().getEntity() instanceof Player) {
            Player player = (Player) command.getSource().getEntity();
//            Dawnforge.darkData.SetDark(argument);
//            player.sendSystemMessage(Component.literal("Set Dark to: " + Boolean.toString(Dawnforge.darkData.IsDark)));
            player.sendSystemMessage(Component.literal("dark set success"));
        }

        return Command.SINGLE_SUCCESS;

    }

    static int cowSay(CommandContext<CommandSourceStack> command, String argument) {
        if (command.getSource().getEntity() instanceof Player) {
            cs((Player) command.getSource().getEntity(), argument);
        }

        return Command.SINGLE_SUCCESS;
    }

    static void cs(Player player, String message) {
        int msglength = message.length();
        String underscores = "";
        String dashes = "";
        for (int i = 0; i < msglength + 2; i++) {
            underscores += "_";
            dashes += "-";
        }
        player.sendSystemMessage(Component.literal(" " + underscores + "\n< " + message + " >\n " + dashes +
                        "\n  \\  ^__^" +
                        "\n   \\ (oo)\\_______" +
                        "\n     (__)\\       )\\/\\" +
                        "\n         ||----w |" +
                        "\n         ||     ||"
                ));
    }


    static int riceFight(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player) {
            Player player = (Player) command.getSource().getEntity();
            player.sendSystemMessage(Component.literal("Never dies!"));
        }

        return Command.SINGLE_SUCCESS;
    }



}
