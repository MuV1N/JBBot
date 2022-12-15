package de.muv1n.jbbot;

import de.muv1n.jbbot.command.slash.ReactionRolesButtonInteraction;
import de.muv1n.jbbot.command.slash.util.CommandManager;
import de.muv1n.jbbot.event.JoinEvent;
import de.muv1n.jbbot.event.QuitEent;
import de.muv1n.jbbot.translation.CommonTranslation;
import de.muv1n.jbbot.twitch.TwitchInit;
import de.muv1n.jbbot.util.Stop;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.IOException;
import java.util.ArrayList;

@AllArgsConstructor
public class JBBot {
    private final JDA bot;
    private final CommonTranslation common;

    public JBBot(String key) throws IOException {
        JDA bot;
        JDABuilder builder = JDABuilder.createDefault(key);
        ArrayList<GatewayIntent> intent = new ArrayList<>();
        intent.add(GatewayIntent.DIRECT_MESSAGES);intent.add(GatewayIntent.DIRECT_MESSAGE_REACTIONS);intent.add(GatewayIntent.DIRECT_MESSAGE_TYPING);intent.add(GatewayIntent.GUILD_BANS);intent.add(GatewayIntent.GUILD_EMOJIS_AND_STICKERS);intent.add(GatewayIntent.GUILD_MEMBERS);intent.add(GatewayIntent.GUILD_INVITES);intent.add(GatewayIntent.GUILD_MESSAGE_REACTIONS);intent.add(GatewayIntent.GUILD_MESSAGE_TYPING);intent.add(GatewayIntent.GUILD_PRESENCES);intent.add(GatewayIntent.GUILD_VOICE_STATES);intent.add(GatewayIntent.GUILD_WEBHOOKS);intent.add(GatewayIntent.GUILD_MESSAGES);intent.add(GatewayIntent.MESSAGE_CONTENT);

        this.common = new CommonTranslation();

        CommandManager commandManager = new CommandManager();

        builder.enableIntents(intent);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.addEventListeners(new JoinEvent(this), new QuitEent(this), new ReactionRolesButtonInteraction(this));
        builder.setActivity(Activity.playing(common.get("activity")));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(commandManager);

        bot = builder.build();

        this.bot = bot;
        System.out.println("§2BOT ONLINE");
        commandManager.load(this, this.common);

        new TwitchInit(this);
        new Stop(bot, builder);
    }

    public JDA getBot() {
        return bot;
    }

    public CommonTranslation getCommon() {
        return common;
    }

}
