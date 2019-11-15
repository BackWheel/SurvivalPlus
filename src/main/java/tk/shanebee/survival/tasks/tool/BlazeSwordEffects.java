package tk.shanebee.survival.tasks.tool;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import tk.shanebee.survival.Survival;
import tk.shanebee.survival.managers.ItemManager;
import tk.shanebee.survival.managers.Items;

public class BlazeSwordEffects extends BukkitRunnable {

	private Survival plugin;

	public BlazeSwordEffects(Survival plugin) {
		this.plugin = plugin;
		this.runTaskTimer(plugin, 1, 10);
	}

	@Override
	public void run() {
		for (Player player : plugin.getServer().getOnlinePlayers()) {
			if (ItemManager.compare(player.getInventory().getItemInMainHand(), Items.BLAZE_SWORD)) {
				player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
				player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20, 0, false));
				Location particleLoc = player.getLocation();
				particleLoc.setY(particleLoc.getY() + 1);
				assert particleLoc.getWorld() != null;
				particleLoc.getWorld().spawnParticle(Particle.FLAME, particleLoc, 10, 0.5, 0.5, 0.5);

				player.setFireTicks(20);
				if (player.getHealth() > 14)
					player.setHealth(14);
			}
		}
	}

}
