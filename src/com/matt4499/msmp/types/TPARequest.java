package com.matt4499.msmp.types;
import org.bukkit.Location;
import org.bukkit.entity.Player;
public class TPARequest {
    public Player from;
    public Player to;
    public TPARequest(Player from, Player to) {
        this.from = from;
        this.to = to;
    }
}
