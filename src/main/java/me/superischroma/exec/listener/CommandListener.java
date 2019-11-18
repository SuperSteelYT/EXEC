package me.superischroma.exec.listener;

import me.superischroma.exec.EXEC;
import me.superischroma.exec.config.ExecutableData;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CommandListener implements Listener
{
    private EXEC plugin;
    public CommandListener(EXEC plugin)
    {
        this.plugin = plugin;
    }

    private ExecutableData edata = ExecutableData.getConfig();

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e)
    {
        String commandFull = e.getMessage().split(" ")[0];
        Logger log = EXEC.getPlugin(EXEC.class).getLogger();
        for (String exec : edata.getKeys(false))
        {
            if (commandFull.equalsIgnoreCase("/" + exec))
            {
                e.setCancelled(true);
                for (String ins : edata.getStringList(exec + ".instructions"))
                {
                    String[] informationDetails = ins.split(" ");
                    for (int i = 0; i < informationDetails.length; i++)
                    {
                        informationDetails[i] = informationDetails[i].replace("senderx", String.valueOf(e.getPlayer().getLocation().getBlockX()))
                                .replace("sendery", String.valueOf(e.getPlayer().getLocation().getBlockY()))
                                .replace("senderz", String.valueOf(e.getPlayer().getLocation().getBlockZ()))
                                .replace("sendername", e.getPlayer().getName())
                                .replace("senderdisplayname", e.getPlayer().getDisplayName());
                    }
                    String command = informationDetails[0];
                    if (command.equals("vector"))
                    {
                        int x = Integer.valueOf(informationDetails[2]);
                        int y = Integer.valueOf(informationDetails[3]);
                        int z = Integer.valueOf(informationDetails[4]);
                        Player player = Bukkit.getPlayer(informationDetails[5]);
                        boolean all = false;
                        if (informationDetails[5].equals("*"))
                            all = true;
                        if (all)
                        {
                            for (Player p : Bukkit.getOnlinePlayers())
                            {
                                p.setVelocity(p.getVelocity().clone().add(new Vector(x, y, z)));
                            }
                        }
                        else
                            player.setVelocity(player.getVelocity().clone().add(new Vector(x, y, z)));
                    }
                    if (command.equals("chat"))
                    {
                        String chat = StringUtils.join(informationDetails, " ", 1, informationDetails.length);
                        chat = ChatColor.translateAlternateColorCodes('&', chat);
                        Bukkit.broadcastMessage(chat);
                    }
                    if (command.equals("lightning"))
                    {
                        int x = Integer.valueOf(informationDetails[1]);
                        int y = Integer.valueOf(informationDetails[2]);
                        int z = Integer.valueOf(informationDetails[3]);
                        World world = Bukkit.getWorld(informationDetails[4]);
                        world.strikeLightning(new Location(world, x, y, z));
                    }
                    if (command.equals("explosion"))
                    {
                        int x = Integer.valueOf(informationDetails[1]);
                        int y = Integer.valueOf(informationDetails[2]);
                        int z = Integer.valueOf(informationDetails[3]);
                        World world = Bukkit.getWorld(informationDetails[4]);
                        int power = Integer.valueOf(informationDetails[5]);
                        world.createExplosion(new Location(world, x, y, z), power);
                    }
                }
            }
        }
    }
}