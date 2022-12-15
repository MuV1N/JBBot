package de.muv1n.jbbot.command.slash.roles;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.command.slash.util.CommandObject;
import de.muv1n.jbbot.translation.CommonTranslation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.CommandAutoCompleteInteraction;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static net.dv8tion.jda.api.Permission.*;

public class ReactionRolesCommand extends CommandObject {
    public ReactionRolesCommand(@NotNull JBBot jbBot, @NotNull CommonTranslation common) {
        super(jbBot, common);
    }

    @Override
    public CommandData getCommand() {
        return Commands.slash("srm", common.get("srm.description"))
                .addOption(OptionType.CHANNEL, "channel", common.get(common.get("srm.type.channel.description")), true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(VIEW_AUDIT_LOGS));
    }

    @Override
    public void respond(SlashCommandInteractionEvent e) {
        final Member member = e.getMember();
        if (!e.getName().equals("srm")) return;
        assert member != null;
        TextChannel channel = e.getOption("channel").getAsChannel().asTextChannel();
            channel.sendMessageEmbeds(new EmbedBuilder()
                    .setColor(new Color(47, 49, 54))
                    .setTitle(common.get("srm.embed.title", e.getUserLocale()))
                    .setDescription(common.get("srm.embed.description", "<@566521375842631686>", "<@802492979750764574>")).build())
                    .addActionRow(Button.secondary("twitch-button", "Twitch-Benachrichtigungen"))
                    .addActionRow(Button.secondary("youtube-button", "YouTube-Benachrichtigungen"))
                    .addActionRow(Button.secondary("discord-button", "Discord-Benachrichtigungen"))
                    .queue();

            e.reply(common.get("srm.reply.success","<#" + e.getOption("channel").getAsChannel().getId() + ">", e.getUserLocale())).setEphemeral(true).queue();

        }

    @Override
    public void autoComplete(CommandAutoCompleteInteraction e) {
        return;
    }
}
