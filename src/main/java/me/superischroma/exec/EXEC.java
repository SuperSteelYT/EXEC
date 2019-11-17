package me.superischroma.exec;

import me.superischroma.exec.command.*;
import me.superischroma.exec.config.ExecutableData;
import me.superischroma.exec.listener.CommandListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EXEC extends JavaPlugin
{
    public void onEnable()
    {
        loadCommands();
        loadConfig();
        loadListeners();
        this.getLogger().info("Enabled.");
    }

    public void onDisable()
    {
        this.getLogger().info("Disabled.");
    }

    private void loadCommands()
    {
        this.getCommand("executable").setExecutor(new Command_executable());
        this.getCommand("addinstruction").setExecutor(new Command_addinstruction());
        this.getCommand("removeinstruction").setExecutor(new Command_removeinstruction());
        this.getCommand("instructions").setExecutor(new Command_instructions());
    }

    private void loadConfig()
    {
        ExecutableData edata = ExecutableData.getConfig();
        edata.options().copyDefaults(true);
        edata.save();
    }

    private void loadListeners()
    {
        PluginManager manager = this.getServer().getPluginManager();
        manager.registerEvents(new CommandListener(this), this);
    }
}
