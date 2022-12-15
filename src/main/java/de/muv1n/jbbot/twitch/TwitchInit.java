package de.muv1n.jbbot.twitch;

import com.github.philippheuer.events4j.core.EventManager;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.TwitchClientHelper;
import de.muv1n.jbbot.JBBot;
import de.muv1n.jbbot.twitch.events.StreamGoLiveEvent;
import io.github.cdimascio.dotenv.Dotenv;

public class TwitchInit {
    
    private final TwitchClientBuilder builder;
    private final TwitchClient client;
    private final TwitchClientHelper helper;
    private final EventManager manager;

    private static TwitchInit instance;
    private static int i = 0;

    public TwitchInit(JBBot bot){
        Dotenv dotenv = Dotenv.load();

        this.builder = TwitchClientBuilder.builder();
        this.client = this.builder
                .withClientId(dotenv.get("CLIENTID"))
                .withClientSecret(dotenv.get("CLIENTSECRET"))
                .withEnableHelix(true).build();
        this.helper = this.client.getClientHelper();
        this.manager = this.client.getEventManager();

        if (helper.enableStreamEventListener("theRealJ0sh") == null) System.out.println("Stream Event Listener (J0sh) didn't load!");
        if (helper.enableStreamEventListener("blueeye01") == null) System.out.println("Stream Event Listener (Blue) didn't load!");

        new StreamGoLiveEvent(manager, bot);

    }


    public TwitchClientBuilder getBuilder() {
        return builder;
    }
    public TwitchClient getClient() {
        return client;
    }
    public TwitchClientHelper getHelper() {
        return helper;
    }
    public EventManager getManager() {
        return manager;
    }
    public static TwitchInit getInstance() {
        return instance;
    }
}
