package me.superischroma.exec.command;

import me.superischroma.exec.config.ExecutableData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Command_removeinstruction implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args)
    {
        ExecutableData edata = ExecutableData.getConfig();
        if (args.length != 2)
            return false;
        String statement = args[0];
        int index = Integer.valueOf(args[1]);
        List<String> instructions = edata.getStringList(statement + ".instructions");
        String legacy = instructions.get(index);
        instructions.remove(index);
        edata.set(statement + ".instructions", instructions);
        edata.save();
        sender.sendMessage(ChatColor.GRAY + "Removed \"" + legacy + "\" (index: " + index + ") from \"" + statement + "\"");
        return true;
    }
}