package de.muv1n.jbbot.command.slash.util;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.command.slash.message.MessageCommand;
import de.muv1n.jbbot.command.slash.roles.ReactionRolesCommand;
import de.muv1n.jbbot.translation.CommonTranslation;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    //Inspired by @DerBanko

    private final List<CommandObject> list;

    public CommandManager(){
        this.list = new ArrayList<>();

    }

    public void load(@NotNull JBBot jbBot, @NotNull CommonTranslation common){

        list.add(new ReactionRolesCommand(jbBot, common));
        list.add(new MessageCommand(jbBot, common));

        CommandListUpdateAction commands = jbBot.getBot().updateCommands();

        for (CommandObject object: list){
            commands = commands.addCommands(object.getCommand());
        }
        commands.queue();

    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e){
        for (CommandObject object : list){
            if (!object.getCommand().getName().equalsIgnoreCase(e.getInteraction().getName())){
                continue;
            }
            object.respond(e);
        }

    }

    @Override
    public void onCommandAutoCompleteInteraction(CommandAutoCompleteInteractionEvent e) {
        for (CommandObject object : list){
            if (!object.getCommand().getName().equalsIgnoreCase(e.getInteraction().getName())){
                continue;
            }
            object.autoComplete(e);
        }
    }
}
