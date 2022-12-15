package de.muv1n.jbbot.command.slash;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.translation.CommonTranslation;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class ReactionRolesButtonInteraction extends ListenerAdapter {
    private final JBBot bot;
    public ReactionRolesButtonInteraction(JBBot bot){
        this.bot = bot;
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent e) {
        Dotenv d = Dotenv.load();
        CommonTranslation c = bot.getCommon();
        String interacton = e.getButton().getId();
        if (interacton.equals("twitch-button")) {
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(bot.getBot().getRoleById(d.get("TWITCH_NOTIFICATION_ROLE_ID")))) {
                e.getGuild().removeRoleFromMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById(d.get("TWITCH_NOTIFICATION_ROLE_ID")))).queue();
                e.replyEmbeds(new EmbedBuilder()
                        .setTitle(c.get("srm.button.twitch.success.remove.title"))
                        .setDescription(c.get("srm.button.twitch.success.remove.description", d.get("TWITCH_NOTIFICATION_ROLE_ID"), d.get("J0SH_ID"), d.get("BLUE_ID")))
                        .build()).setEphemeral(true).queue();
            } else {
                e.getGuild().addRoleToMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById(d.get("TWITCH_NOTIFICATION_ROLE_ID")))).queue();
                e.replyEmbeds(new EmbedBuilder()
                        .setTitle(c.get("srm.button.twitch.success.give.title"))
                        .setDescription(c.get("srm.button.twitch.success.give.description", d.get("TWITCH_NOTIFICATION_ROLE_ID"), d.get("J0SH_ID"), d.get("BLUE_ID")))
                        .build()).setEphemeral(true).queue();
            }
        }
        if (interacton.equals("youtube-button")) {
            if (Objects.requireNonNull(e.getMember()).getRoles().contains(bot.getBot().getRoleById(d.get("YOUTUBE_NOTIFICATION_ROLE_ID")))) {
                e.getGuild().removeRoleFromMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById(d.get("YOUTUBE_NOTIFICATION_ROLE_ID")))).queue();
                e.replyEmbeds(new EmbedBuilder()
                        .setTitle(c.get("srm.button.youtube.success.remove.title"))
                        .setDescription(c.get("srm.button.youtube.success.remove.description", d.get("YOUTUBE_NOTIFICATION_ROLE_ID"), d.get("J0SH_ID"), d.get("BLUE_ID")))
                        .build()).setEphemeral(true).queue();
            } else {
                e.getGuild().addRoleToMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById(d.get("YOUTUBE_NOTIFICATION_ROLE_ID")))).queue();
                e.replyEmbeds(new EmbedBuilder()
                        .setTitle(c.get("srm.button.youtube.success.give.title"))
                        .setDescription(c.get("srm.button.youtube.success.give.description", d.get("YOUTUBE_NOTIFICATION_ROLE_ID"), d.get("J0SH_ID"), d.get("BLUE_ID")))
                        .build()).setEphemeral(true).queue();
            }
        }
        if (interacton.equals("discord-button")) {
            if (e.getMember().getRoles().contains(bot.getBot().getRoleById(d.get("DISCORD_NOTIFICATION_ROLE_ID")))) {
                e.getGuild().removeRoleFromMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById(d.get("DISCORD_NOTIFICATION_ROLE_ID")))).queue();
                e.replyEmbeds(new EmbedBuilder()
                        .setTitle(c.get("srm.button.discord.success.remove.title"))
                        .setDescription(c.get("srm.button.discord.success.remove.description", d.get("DISCORD_NOTIFICATION_ROLE_ID")))
                        .build()).setEphemeral(true).queue();
            } else {
                e.getGuild().addRoleToMember(e.getMember(), Objects.requireNonNull(e.getGuild().getRoleById(d.get("DISCORD_NOTIFICATION_ROLE_ID")))).queue();
                e.replyEmbeds(new EmbedBuilder()
                        .setTitle(c.get("srm.button.discord.success.give.title"))
                        .setDescription(c.get("srm.button.discord.success.give.description",d.get("DISCORD_NOTIFICATION_ROLE_ID")))
                        .build()).setEphemeral(true).queue();
            }
        }
    }
}
