package de.muv1n.jbbot.ticket;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.command.slash.util.CommandObject;
import de.muv1n.jbbot.translation.CommonTranslation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.CommandAutoCompleteInteraction;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.dv8tion.jda.api.Permission.VIEW_AUDIT_LOGS;

public class CreateTicketChannelMessage extends CommandObject {
    public CreateTicketChannelMessage(@NotNull JBBot jbBot, @NotNull CommonTranslation common) {
        super(jbBot, common);
    }
//TODO: ALL MESSAGES
    @Override
    public CommandData getCommand() {
        return Commands.slash("ticketMessage", common.get("command.ticket.description"))
                .addOption(OptionType.CHANNEL, "channel", common.get(common.get("command.ticket.type.channel.description")), true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(VIEW_AUDIT_LOGS));
    }

    @Override
    public void respond(SlashCommandInteractionEvent e) {
        if (!e.getName().equals("ticketMessage")) return;

        TextChannel channel = Objects.requireNonNull(e.getGuild()).getTextChannelById(Objects.requireNonNull(e.getOption("channel")).getAsString());

        channel.sendMessageEmbeds(new EmbedBuilder()
                        .setTitle(common.get("command.ticket.embed.title"))
                        .setDescription(common.get("command.ticket.embed.description"))
                .build())
                .addActionRow(Button.secondary("ticketCreate", "Erstelle ein Ticket"))
                .queue();
    }

    @Override
    public void autoComplete(CommandAutoCompleteInteraction e) {

    }
}
