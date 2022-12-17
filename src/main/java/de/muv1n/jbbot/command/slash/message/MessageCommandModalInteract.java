package de.muv1n.jbbot.command.slash.message;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.NonNull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class MessageCommandModalInteract extends ListenerAdapter {
        @Override
    public void onModalInteraction(@NonNull ModalInteractionEvent event) {
        if (!event.getModalId().equals("messageCommand")) return;
        Dotenv dotenv = Dotenv.load();
        TextChannel textChannel;

        String title = Objects.requireNonNull(event.getValue("titleInput")).getAsString();
        String message = Objects.requireNonNull(event.getValue("messageInput")).getAsString();
        textChannel = Objects.requireNonNull(event.getGuild()).getTextChannelById(Objects.requireNonNull(event.getValue("channelInput")).getAsString());

        if (textChannel == event.getGuild().getTextChannelById(dotenv.get("INFORMATION_CHANNEL_ID"))){
            textChannel.sendMessageEmbeds(embed(title, message).build()).queue();
            textChannel.sendMessage(event.getGuild().getRoleById(dotenv.get("DISCORD_NOTIFICATION_ROLE_ID")).getAsMention() + "").queue(q->{
                q.delete().queue();
            });
        }

        textChannel.sendMessageEmbeds(embed(title, message).build()).queue();

        event.reply("Hat geklappt die Nachricht wurde in den Channel: " + textChannel.getAsMention() + " gesendet :partying_face:").setEphemeral(true).queue();
        }

    public EmbedBuilder embed(String title, String message){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setDescription(message);
        return eb;
    }

}
