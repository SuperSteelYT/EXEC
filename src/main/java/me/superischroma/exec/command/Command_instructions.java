package me.superischroma.exec.command;

import me.superischroma.exec.config.ExecutableData;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Command_instructions implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        ExecutableData edata = ExecutableData.getConfig();
        if (args.length != 1)
            return false;
        if (args[0].equalsIgnoreCase("list"))
        {
            sender.sendMessage(ChatColor.GRAY + "List of usable instructions:");
            sender.sendMessage(ChatColor.GRAY + " - vector <power> <direction> <player>");
            sender.sendMessage(ChatColor.GRAY + " - chat <message>");
            sender.sendMessage(ChatColor.GRAY + " - lightning <x> <y> <z> <world>");
            sender.sendMessage(ChatColor.GRAY + " - explosion <x> <y> <z> <world> <power>");
            return true;
        }
        String statement = args[0];
        List<String> instructions = edata.getStringList(statement + ".instructions");
        for (String instruction : instructions)
        {
            sender.sendMessage("" + ChatColor.GRAY + instructions.indexOf(instruction) + ": " + instruction);
        }
        return true;
    }
}