package com.matt4499.msmp.events;
import com.matt4499.msmp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
public class JoinLeaveMessages implements Listener {
    public ArrayList<String> joinMsgs = new ArrayList<>();
    public ArrayList<String> leaveMsgs = new ArrayList<>();
    public ArrayList<String> firstJoinMsgs = new ArrayList<>();

    public JoinLeaveMessages() {
        firstJoinMsgs.add("&7[&a++&7] #dc143cWelcome new player #dc143c%s #dc143cto the server!");

        joinMsgs.add("&7[&a+&7] %s is back. (%s)");
        joinMsgs.add("&7[&a+&7] Rev up those fryers because %s is back (%s)");
        joinMsgs.add("&7[&a+&7] %s has joined the game. (%s)");
        joinMsgs.add("&7[&a+&7] %s has returned. (%s)");
        joinMsgs.add("&7[&a+&7] A WILD %s APPEARS! (%s)");
        joinMsgs.add("&7[&a+&7] Really %s? Another cobblestone build? (%s)");
        joinMsgs.add("&7[&a+&7] We hope you brought pizza, %s (%s)");
        joinMsgs.add("&7[&a+&7] Shhhh.... %s is here. (%s)");
        joinMsgs.add("&7[&a+&7] %s has joined the game. (%s)");

        leaveMsgs.add("&7[#dc143c-&7] %s pushed a pull door.");
        leaveMsgs.add("&7[#dc143c-&7] %s pulled a push door.");
        leaveMsgs.add("&7[#dc143c-&7] It is past %s's bed time.");
        leaveMsgs.add("&7[#dc143c-&7] %s hated the color crimson.");
        leaveMsgs.add("&7[#dc143c-&7] %s had somewhere else to be.");
        leaveMsgs.add("&7[#dc143c-&7] %s is going to bed.");
        leaveMsgs.add("&7[#dc143c-&7] %s went to get pizza.");
        leaveMsgs.add("&7[#dc143c-&7] %s left the game.");
        leaveMsgs.add("&7[#dc143c-&7] %s was abducted by aliens.");
        leaveMsgs.add("&7[#dc143c-&7] %s accidentally ALT+F4'd.");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()) {
            e.setJoinMessage(getFormattedMsg(p, "fjoin"));
            Location spawn = new Location(Bukkit.getWorld("world"), -34.0, 66.0, 5.0);
            e.getPlayer().teleport(spawn);
        } else {
            e.setJoinMessage(getFormattedMsg(p, "join"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(getFormattedMsg(p, "quit"));
    }

    public String getFormattedMsg(Player p, String type) {
        String finalmsg = "";
        switch (type) {
            case "join" -> {
                int index = (int) (Math.random() * joinMsgs.size());
                String randomMsg = joinMsgs.get(index);
                finalmsg = Main.hex(String.format(randomMsg, p.getDisplayName(), Main.getData(p.getUniqueId(), "totaljoins")));
            }
            case "quit" -> {
                int index2 = (int) (Math.random() * leaveMsgs.size());
                String randomMsg2 = leaveMsgs.get(index2);
                finalmsg = Main.hex(String.format(randomMsg2, p.getDisplayName()));
            }
            case "fjoin" -> {
                int index3 = (int) (Math.random() * firstJoinMsgs.size());
                String randomMsg = firstJoinMsgs.get(index3);
                finalmsg = Main.hex(String.format(randomMsg, p.getDisplayName()));
            }
            default -> {}
        }
        return finalmsg;
    }
}
