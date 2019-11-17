package me.superischroma.exec.command;

import me.superischroma.exec.config.ExecutableData;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Command_addinstruction implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length == 0)
            return false;
        String information = "";
        if (args.length >= 2)
        {
            if (args[1].equalsIgnoreCase("chat"))
            {
                String chat = StringUtils.join(args, " ", 2, args.length);
                information = "chat " + chat;
            }
        }
        if (args.length >= 5 && args.length <= 6)
        {
            if (args[1].equalsIgnoreCase("vector"))
            {
                int power = Integer.valueOf(args[2]);
                int x = 0;
                int y = 0;
                int z = 0;
                if (args[3].equalsIgnoreCase("x+"))
                    x = power;
                if (args[3].equalsIgnoreCase("x-"))
                    x = -power;
                if (args[3].equalsIgnoreCase("y+"))
                    y = power;
                if (args[3].equalsIgnoreCase("y-"))
                    y = -power;
                if (args[3].equalsIgnoreCase("z+"))
                    z = power;
                if (args[3].equalsIgnoreCase("z-"))
                    z = -power;
                information = "vector " + power + " " + x + " " + y + " " + z;
                boolean all = false;
                Player player = Bukkit.getPlayer(args[4]);
                if (args[4].equalsIgnoreCase("*"))
                    all = true;
                if (player != null)
                {
                    information += " " + player.getName();
                }
                else if (all)
                {
                    information += " *";
                }
                else
                {
                    sender.sendMessage(ChatColor.GRAY + "Player not found.");
                    return true;
                }
            }
        }
        sender.sendMessage(ChatColor.GRAY + "Added a " + args[1] + " instruction to \"" + args[0] + "\"");
        ExecutableData.addInstruction(args[0], information);
        return true;
    }
}

// Vector - /modify <executable> vector <power> <dir> <player> [wait]

/*
 * Executable Capabilities
 *
 * Vector:
 *  - /modify <executable> vector <power> <dir> <player> [wait]
 *  - vector <power> <x> <y> <z> <player> [wait]
  */