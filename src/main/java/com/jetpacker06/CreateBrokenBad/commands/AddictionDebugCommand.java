package com.jetpacker06.CreateBrokenBad.commands;

import com.jetpacker06.CreateBrokenBad.register.CBBCapabilities;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

public class AddictionDebugCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("addiction")
                .then(Commands.literal("show")
                        .requires(c -> c.hasPermission(2))
                        .executes(AddictionDebugCommand::show))
                .then(Commands.literal("clear")
                        .requires(c -> c.hasPermission(2))
                        .executes(AddictionDebugCommand::clear))
                .then(Commands.literal("set")
                        .then(Commands.literal("addiction")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .requires(c -> c.hasPermission(2))
                                        .executes(AddictionDebugCommand::setAddiction)))
                        .then(Commands.literal("withdrawal")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .requires(c -> c.hasPermission(2))
                                        .executes(AddictionDebugCommand::setWithdrawal)))
                        .then(Commands.literal("tolerance")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .requires(c -> c.hasPermission(2))
                                        .executes(AddictionDebugCommand::setTolerance)))
                        .then(Commands.literal("toxicity")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .requires(c -> c.hasPermission(2))
                                        .executes(AddictionDebugCommand::setToxicity)))
                )
        );
    }

    private static int show(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                player.sendMessage(new TranslatableComponent("commands.createbb.addiction.show",
                        "" + ChatFormatting.YELLOW +addiction.addiction,
                        "" + ChatFormatting.YELLOW + addiction.withdrawal,
                        "" + ChatFormatting.YELLOW + addiction.tolerance,
                        "" + ChatFormatting.YELLOW + addiction.toxicity), ChatType.GAME_INFO, Util.NIL_UUID);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int clear(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();

            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.reset();
                player.sendMessage(new TranslatableComponent("commands.createbb.addiction.clear"), ChatType.GAME_INFO, Util.NIL_UUID);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setAddiction(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.addiction = value;
                player.sendMessage(new TranslatableComponent("commands.createbb.addiction.set_addiction", "" + ChatFormatting.YELLOW + value), ChatType.GAME_INFO, Util.NIL_UUID);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setWithdrawal(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.withdrawal = value;
                player.sendMessage(new TranslatableComponent("commands.createbb.addiction.set_withdrawal", "" + ChatFormatting.YELLOW + value), ChatType.GAME_INFO, Util.NIL_UUID);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setTolerance(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.tolerance = value;
                player.sendMessage(new TranslatableComponent("commands.createbb.addiction.set_tolerance", "" + ChatFormatting.YELLOW + value), ChatType.GAME_INFO, Util.NIL_UUID);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }

    private static int setToxicity(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();
            float value = FloatArgumentType.getFloat(context, "value");
            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                addiction.toxicity = value;
                player.sendMessage(new TranslatableComponent("commands.createbb.addiction.set_toxicity", "" + ChatFormatting.YELLOW + value), ChatType.GAME_INFO, Util.NIL_UUID);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }
}
