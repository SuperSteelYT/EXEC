package me.superischroma.exec.listener;

import me.superischroma.exec.EXEC;
import me.superischroma.exec.config.ExecutableData;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
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
        String command = e.getMessage().split(" ")[0];
        Logger log = EXEC.getPlugin(EXEC.class).getLogger();
        for (String exec : edata.getKeys(false))
        {
            if (command.equalsIgnoreCase("/" + exec))
            {
                e.setCancelled(true);
                for (String ins : edata.getStringList(exec + ".instructions"))
                {
                    String[] informationDetails = ins.split(" ");
                    if (informationDetails[0].equals("vector"))
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
                    if (informationDetails[0].equals("chat"))
                    {
                        String chat = StringUtils.join(informationDetails, " ", 1, informationDetails.length);
                        Bukkit.broadcastMessage(chat);
                    }
                }
            }
        }
    }
}

// vector <power> <x> <y> <z> <player> [wait]