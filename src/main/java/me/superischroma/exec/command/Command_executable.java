package me.superischroma.exec.command;

import me.superischroma.exec.config.ExecutableData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_executable implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        if (args.length != 2)
            return false;
        String statement = args[1];
        if (args[0].equalsIgnoreCase("create"))
        {
            ExecutableData.createExecutable(statement);
            sender.sendMessage(ChatColor.GRAY + "Created a new executable with the statement \"" + statement + "\"");
        }
        if (args[0].equalsIgnoreCase("delete"))
        {
            ExecutableData.deleteExecutable(statement);
            sender.sendMessage(ChatColor.GRAY + "Deleted \"" + statement + "\"");
        }
        return true;
    }
}
