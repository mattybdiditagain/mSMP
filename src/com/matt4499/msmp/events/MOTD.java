package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
public class MOTD implements Listener {
    public ArrayList<String> funnyMessages = new ArrayList<>();
    public MOTD() {
        funnyMessages.add("#dc143cLogging in has been disabled.");
        funnyMessages.add("#dc143cAliens have taken over.");
        funnyMessages.add("#dc143cYou have been banned.");
        funnyMessages.add("#dc143cX 4499 Z 4499");
        funnyMessages.add("#dc143cdiscord.io/msmp");
        funnyMessages.add("#dc143cProfessorOG9 stinks.");
        funnyMessages.add("#dc143cem ess em pee");
        funnyMessages.add("#dc143cMatt4499 says hi!");
        funnyMessages.add("#dc143cAntiGrief + AntiXray = Good Luck ;)");
        funnyMessages.add("#dc143cWe have block logs ;)");
    }
    @EventHandler
    public void ping(ServerListPingEvent e) {
        e.setMotd(Main.hex("&cmSMP / 1.19.2 / Fresh Map & Economy / Discord.IO/mSMP \n" + funnyMessages.get((int) (Math.random() * funnyMessages.size()))));
    }

}
