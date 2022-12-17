package de.muv1n.jbbot.ticket;

import de.muv1n.jbbot.JBBot;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class CreateTicketChannelButtonInteraction extends ListenerAdapter{
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (!event.getInteraction().getId().equals("ticketCreate")) return;

        TextInput title = TextInput.create("titleTicket", "Titel", TextInputStyle.SHORT)
                .setValue(event.getMember().getEffectiveName())
                .setRequiredRange(0,100)
                .setRequired(true)
                .build();
        TextInput problem = TextInput.create("problemTicket", "Schildere dein Problem", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Schildere dein Problem")
                .setRequiredRange(0,4000)
                .setRequired(true)
                .build();

        Modal modal = Modal.create("modalTicket", "Ticket").addActionRows(ActionRow.of(title)).addActionRows(ActionRow.of(problem)).build();

        event.replyModal(modal).queue();
    }
}
