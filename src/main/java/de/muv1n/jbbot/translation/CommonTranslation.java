package de.muv1n.jbbot.translation;

import net.dv8tion.jda.api.entities.Guild;

import java.util.Locale;

public class CommonTranslation extends Translation{

    //Inspired BY @DerBanko

    public CommonTranslation() {
        super("common", CommonTranslation.class.getClassLoader());
    }
    public String get(String key, Guild guild, Object... format) {
        return super.get(key, guild.getLocale(), format);
    }
    public String get(String key, Guild guild, Locale locale, Object... format) {
        return super.get(key, locale, format);
    }
}
