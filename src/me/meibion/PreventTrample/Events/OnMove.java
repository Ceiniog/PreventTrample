package me.meibion.PreventTrample.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnMove implements Listener {

    public PlayerListener pl = new PlayerListener(){
        public void onPlayerMove(PlayerMoveEvent event) {
            Player player = event.getPlayer();
            Block block = event.getFrom().add(0d,-1d,0d).getBlock();

            if(block.getType() != Material.SOIL) { return; }

            final byte subID = block.getData();

            // Check permissions

            if(subID == 0) { // Dry soil
                if(!player.hasPermission("PreventTrample.Dry")) { return; }
            }
            else if(subID == 7) { // Dry soil
                if(!player.hasPermission("PreventTrample.Wet")) { return; }
            }

            block.setType(Material.SOIL);
            block.setData(subID);
        }
    };
}
