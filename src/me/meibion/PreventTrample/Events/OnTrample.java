package me.meibion.PreventTrample.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class OnTrample implements Listener {

    public PlayerListener pl = new PlayerListener() {
        public void onPlayerInteract(PlayerInteractEvent event) {

            // Check if the action is of the correct type
            if(event.getAction() != Action.PHYSICAL) { return; }

            // Get event details
            Block trampledBlock = event.getClickedBlock();
            final byte subID = trampledBlock.getData();
            Player player = event.getPlayer();

            // Check if the block is soil
            if(trampledBlock.getType() != Material.SOIL) { return; }

            // Check permissions

            if(subID == 0) { // Dry soil
                if(!player.hasPermission("PreventTrample.Dry")) { return; }
            }
            else if(subID == 7) { // Dry soil
                if(!player.hasPermission("PreventTrample.Wet")) { return; }
            }


            trampledBlock.setType(Material.SOIL);
            trampledBlock.setData(subID);
            event.setCancelled(true);


        }
    };

    public EntityListener el = new EntityListener() {
        public void onEntityInteract(EntityInteractEvent event) {
            Block block = event.getBlock();
            final byte subID = block.getData();
            if(block.getType() == Material.SOIL) {
                block.setType(Material.SOIL);
                block.setData(subID);
                event.setCancelled(true);
            }
        }
    };
}
