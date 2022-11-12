package de.muv1n.jbbot.command.slash;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.command.slash.util.CommandObject;
import de.muv1n.jbbot.translation.CommonTranslation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.CommandAutoCompleteInteraction;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

import static net.dv8tion.jda.api.Permission.ADMINISTRATOR;

public class ReactionRolesCommand extends CommandObject {
    public ReactionRolesCommand(@NotNull JBBot jbBot, @NotNull CommonTranslation common) {
        super(jbBot, common);
    }

    @Override
    public CommandData getCommand() {
        return Commands.slash("srm", common.get("command.srm.description"))
                .addOption(OptionType.CHANNEL, "channel", common.get(common.get("command.srm.type.channel.description")), true, true)
                .setDefaultPermissions(DefaultMemberPermissions.enabledFor(ADMINISTRATOR));
    }

    @Override
    public void respond(SlashCommandInteractionEvent e) {
        final Member member = e.getMember();
        if (!e.getName().equals("srm")) return;
        assert member != null;
        //if (jbBot.getPermission().canManageAll(member)){
            e.getChannel().sendMessageEmbeds(new EmbedBuilder()
                    .setColor(new Color(44, 56, 172))
                    .setTitle(common.get("command.srm.embed.title")).build()).queue();

        //}


        /*if (!e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            e.getUser().openPrivateChannel().queue(ch -> {
                ch.sendMessageEmbeds(new EmbedBuilder()
                        .setColor(new Color(22, 42, 170))
                        .setTitle(c.get("command.srm.embed.title", e.getUserLocale()))
                        .setDescription(c.get("command.srm.embed.description", e.getUserLocale())).build()).queue();
            });*/

        }

    @Override
    public void autoComplete(CommandAutoCompleteInteraction e) {
        return;
    }
}
