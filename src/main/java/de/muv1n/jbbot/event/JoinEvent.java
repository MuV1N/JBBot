package de.muv1n.jbbot.event;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.translation.CommonTranslation;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JoinEvent extends ListenerAdapter {
    private final JBBot bot;

    public JoinEvent(JBBot bot) {
        this.bot = bot;
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        Dotenv d = Dotenv.load();
        CommonTranslation c = this.bot.getCommon();
        TextChannel channel = this.bot.getBot().getTextChannelById(d.get("JOIN_QUIT_NOTIFICATION_CHANNEL_ID"));
        String memberCount = String.valueOf(e.getJDA().getGuildById(e.getGuild().getId()).getMemberCount());
        channel.sendMessageEmbeds(new EmbedBuilder()
                        .setTitle(c.get("join.embed.title", e.getUser().getName()))
                        .setDescription(c.get("join.embed.description", e.getUser().getId(), memberCount))
                .build()).queue();

    }
}
