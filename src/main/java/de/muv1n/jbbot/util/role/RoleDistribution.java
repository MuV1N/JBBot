package de.muv1n.jbbot.util.role;

import de.muv1n.jbbot.JBBot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

public class RoleDistribution {

    public static void addRoleToMember(JBBot jbBot, Member member, Long roleID){
        Role role = jbBot.getBot().getRoleById(roleID);
        member.getRoles().add(role);
    }
    public static void removeRoleFromMember(JBBot jbBot, Member member, Long roleID){
        Role role = jbBot.getBot().getRoleById(roleID);
        member.getRoles().remove(role);
    }

}
