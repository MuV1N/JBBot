package de.muv1n.jbbot.command.slash.message;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.command.slash.util.CommandObject;
import de.muv1n.jbbot.translation.CommonTranslation;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.CommandAutoCompleteInteraction;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;

import static net.dv8tion.jda.api.Permission.VIEW_AUDIT_LOGS;

public class MessageCommand extends CommandObject {

    public MessageCommand(@NotNull JBBot jbBot, @NotNull CommonTranslation common) {
        super(jbBot, common);
    }

    @Override
    public CommandData getCommand() {
        return Commands.slash("message", common.get("message.description"))
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(VIEW_AUDIT_LOGS));
    }

    @Override
    public void respond(SlashCommandInteractionEvent e) {

        if (!e.getName().equals("message")) return;

        TextInput titleInput = TextInput.create("titleInput", "Titel eingabe", TextInputStyle.SHORT)
                .setPlaceholder("Titel")
                .setRequiredRange(5, 100)
                .setRequired(true)
                .build();

        TextInput messageInput = TextInput.create("messageInput", "Nachrichten eingabe", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Nachricht")
                .setRequiredRange(10, 4000)
                .setRequired(true)
                .build();
        TextInput channelInput = TextInput.create("channelInput", "Text Kanal id", TextInputStyle.SHORT)
                .setPlaceholder("Info = 934204263259861052")
                .setRequiredRange(5, 100)
                .setRequired(true)
                .build();

        Modal modal = Modal.create("messageCommand", "Nachricht")
                .addActionRows(ActionRow.of(titleInput)).addActionRows(ActionRow.of(messageInput)).addActionRows(ActionRow.of(channelInput)).build();

        e.replyModal(modal).queue();

    }

    @Override
    public void autoComplete(CommandAutoCompleteInteraction e) {

    }
}
