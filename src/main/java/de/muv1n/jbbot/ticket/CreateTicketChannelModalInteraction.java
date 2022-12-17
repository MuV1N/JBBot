package de.muv1n.jbbot.ticket;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.ForumChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;

import java.util.Objects;

public class CreateTicketChannelModalInteraction extends ListenerAdapter {

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        if(!event.getModalId().equals("modalTicket")) return;
        Dotenv dotenv = Dotenv.load();

        String title = Objects.requireNonNull(event.getValue("titleTicket")).getAsString();
        String problem = Objects.requireNonNull(event.getValue("problemTicket")).getAsString();
        ForumChannel channel = event.getGuild().getForumChannelById(dotenv.get("TICKET_FORUM_ID"));

        channel.createForumPost("", MessageCreateData.fromEmbeds(embed(title, problem).build())).queue();

        event.reply("Hat geklappt die Nachricht wurde in den Channel: " + channel.getAsMention() + " gesendet :partying_face:").setEphemeral(true).queue();

        //TODO: Complete Log channel with the Button to join, delete, close and so on and the messages!

        TextChannel logChannel = event.getGuild().getTextChannelById(dotenv.get("TICKET_LOGS_CHANNEL_ID"));

        logChannel.sendMessageEmbeds(embed(title + " " + event.getMember().getEffectiveName(), "Probelm:\n >" + problem).build())
                .addActionRow(Button.secondary("beitreten", "Beitreteten").withEmoji(Emoji.fromFormatted("✅"))).queue();

    }
    public EmbedBuilder embed(String title, String problem){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setDescription(problem);
        return eb;
    }
}
