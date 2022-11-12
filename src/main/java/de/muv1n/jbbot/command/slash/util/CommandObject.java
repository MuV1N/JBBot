package de.muv1n.jbbot.command.slash.util;

import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.translation.CommonTranslation;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.CommandAutoCompleteInteraction;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

public abstract class CommandObject {

    protected final JBBot jbBot;
    protected final CommonTranslation common;

    public CommandObject(@NotNull JBBot jbBot, @NotNull CommonTranslation common){
        this.jbBot = jbBot;
        this.common = common;
    }

    public abstract CommandData getCommand();
    public abstract void respond(SlashCommandInteractionEvent e);
    public abstract void autoComplete(CommandAutoCompleteInteraction e);
}
