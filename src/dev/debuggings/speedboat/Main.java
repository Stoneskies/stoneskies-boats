package dev.debuggings.speedboat;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import dev.debuggings.speedboat.Events.GiveBoatKey;
import dev.debuggings.speedboat.Events.GiveController;
import dev.debuggings.speedboat.Events.GiveSuperController;


public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		System.out.println("I hope this shit works...");
		getCommand("speedboat").setExecutor(new GiveController());
		getCommand("super-speedboat").setExecutor(new GiveSuperController());
		getCommand("boatkey").setExecutor(new GiveBoatKey());
		getServer().getPluginManager().registerEvents(this, this);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onVehicleDrive(VehicleMoveEvent event){
		Entity vehicle = event.getVehicle();
		Entity passenger = (Entity) event.getVehicle().getPassenger();
		if (vehicle instanceof Boat){
			if(passenger instanceof Player){
				Boat boat = (Boat) vehicle;
				Player player = (Player) passenger;
				ItemStack item = player.getItemInHand();
				if(item != null && item.getType() == Material.REDSTONE && player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Boat Controller")) {
					boat.setVelocity(new Vector(boat.getLocation().getDirection().getX(), 0, boat.getLocation().getDirection().getZ()));
					Location loc = boat.getLocation();
					player.getWorld().playSound(loc, Sound.ENTITY_WOLF_GROWL, 1, 0);
					player.getWorld().spawnParticle(Particle.SQUID_INK, loc.getX(), loc.getY() + 1, loc.getZ(), 0);
					player.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc.getX(), loc.getY() + 1, loc.getZ(), 0);
				}
				if(item != null && item.getType() == Material.GLOWSTONE_DUST && player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Super Boat Controller")) {
					boat.setVelocity(new Vector(boat.getLocation().getDirection().multiply(2).getX(), 0, boat.getLocation().getDirection().multiply(2).getZ()));
					Location loc = boat.getLocation();
					player.getWorld().playSound(loc, Sound.ENTITY_WOLF_GROWL, 1, 0);
					player.getWorld().spawnParticle(Particle.SQUID_INK, loc.getX(), loc.getY() + 1, loc.getZ(), 0);
					player.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc.getX(), loc.getY() + 1, loc.getZ(), 0);
					player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, loc.getX(), loc.getY() + 1, loc.getZ(), 0);
				}
				if(item != null && item.getType() == Material.TRIPWIRE_HOOK && player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Boat Key")) {
					Location loc = boat.getLocation();
					player.getWorld().playSound(loc, Sound.ENTITY_WOLF_GROWL, 1, 0);
					player.getWorld().spawnParticle(Particle.SQUID_INK, loc.getX(), loc.getY() + 1, loc.getZ(), 0);
					player.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc.getX(), loc.getY() + 1, loc.getZ(), 0);
				}
			}
		}
	}

}
