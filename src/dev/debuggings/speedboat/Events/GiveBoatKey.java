package dev.debuggings.speedboat.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveBoatKey implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			player.sendMessage(ChatColor.YELLOW + "You have recieved a " + ChatColor.BLUE + "Boat Key");
			ItemStack controller = new ItemStack(Material.TRIPWIRE_HOOK, 1);
			ItemMeta meta2 = controller.getItemMeta();
			meta2.setDisplayName(ChatColor.GREEN + "Boat Key");
			controller.setItemMeta(meta2);
			player.getInventory().addItem(controller);
		}
		return false;
	}

}
