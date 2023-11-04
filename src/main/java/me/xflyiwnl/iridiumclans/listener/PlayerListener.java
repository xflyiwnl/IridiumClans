package me.xflyiwnl.iridiumclans.listener;

import me.xflyiwnl.iridiumclans.IridiumClans;
import me.xflyiwnl.iridiumclans.object.ClanPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        ClanPlayer clanPlayer = IridiumClans.getInstance().getPlayer(player.getUniqueId());

        if (clanPlayer != null) return;

        clanPlayer = new ClanPlayer(player.getUniqueId());
        clanPlayer.create(true);

    }

}
