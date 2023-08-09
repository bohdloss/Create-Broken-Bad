package com.jetpacker06.CreateBrokenBad.commands;

import com.jetpacker06.CreateBrokenBad.register.CBBCapabilities;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class AddictionDebugCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("addiction")
                .then(Commands.literal("show").executes(AddictionDebugCommand::show))
                .then(Commands.literal("clear").executes(AddictionDebugCommand::clear))
                .then(Commands.literal("set")
                        .then(Commands.literal("addiction")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setAddiction)))
                        .then(Commands.literal("withdrawal")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setWithdrawal)))
                        .then(Commands.literal("tolerance")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setTolerance)))
                        .then(Commands.literal("toxicity")
                                .then(Commands.argument("value", FloatArgumentType.floatArg())
                                        .executes(AddictionDebugCommand::setToxicity)))
                )
        );
    }

    private static int show(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = (ServerPlayer) context.getSource().getEntity();

            player.getCapability(CBBCapabilities.PLAYER_ADDICTION).ifPresent(addiction -> {
                context.getSource().sendSuccess(Component.nullToEmpty(
                        ChatFormatting.BLUE + "Addiction data: \n" +
                                ChatFormatting.GREEN + "Addiction: " +
                                ChatFormatting.YELLOW + addiction.addiction + "\n" +
                                ChatFormatting.GREEN + "Withdrawal: " +
                                ChatFormatting.YELLOW + addiction.withdrawal + "\n" +
                                ChatFormatting.GREEN + "Tolerance: " +
                                ChatFormatting.YELLOW + addiction.tolerance + "\n" +
                                ChatFormatting.GREEN + "Toxicity: " +
                                ChatFormatting.YELLOW + addiction.toxicity
                ), true);
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
                context.getSource().sendSuccess(Component.nullToEmpty(
                        ChatFormatting.BLUE + "Addiction was removed"), true);
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
                context.getSource().sendSuccess(Component.nullToEmpty(
                        ChatFormatting.BLUE + "Addiction set to: " +
                                ChatFormatting.YELLOW + value
                ), true);
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
                context.getSource().sendSuccess(Component.nullToEmpty(
                        ChatFormatting.BLUE + "Withdrawal set to: " +
                                ChatFormatting.YELLOW + value
                ), true);
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
                context.getSource().sendSuccess(Component.nullToEmpty(
                        ChatFormatting.BLUE + "Tolerance set to: " +
                                ChatFormatting.YELLOW + value
                ), true);
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
                context.getSource().sendSuccess(Component.nullToEmpty(
                        ChatFormatting.BLUE + "Toxicity set to: " +
                                ChatFormatting.YELLOW + value
                ), true);
            });

            return 1;
        } catch (ClassCastException | NullPointerException e) {
            return 0;
        }
    }
}
