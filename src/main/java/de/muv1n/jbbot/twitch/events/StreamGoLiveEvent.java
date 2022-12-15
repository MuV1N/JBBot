package de.muv1n.jbbot.twitch.events;

import com.github.philippheuer.events4j.core.EventManager;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.translation.CommonTranslation;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class StreamGoLiveEvent {

    public StreamGoLiveEvent(EventManager manager, JBBot bot){
        Dotenv dotenv = Dotenv.load();
        CommonTranslation c = bot.getCommon();
        TextChannel channel = bot.getBot().getTextChannelById(dotenv.get("TWITCH_NOTIFICATION_CHANNEl_ID"));
        if (channel == null) return;

        manager.onEvent(ChannelGoLiveEvent.class, event -> {
            channel.sendMessageEmbeds(new EmbedBuilder()
                    .setTitle(c.get("twitch.go-live.embed.title"), event.getChannel().getName())
                    .setDescription(c.get("twitch.go-live.embed.description",
                            event.getStream().getTitle(), event.getStream().getTitle(), event.getStream().getGameName()))
                    .setColor(new Color(40, 39, 39))
                    .build()).queue();
            channel.sendMessage(Objects.requireNonNull(Objects.requireNonNull(
                    bot.getBot().getGuildById(dotenv.get("GUILD_ID"))).getRoleById(dotenv.get("TWITCH_NOTIFICATION_ROLE_ID")))
                    .getAsMention()).queue(q ->{
                    q.delete().queueAfter(1000, TimeUnit.MILLISECONDS);
            });
        });
    }
}
