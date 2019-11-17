package me.superischroma.exec.config;

import me.superischroma.exec.EXEC;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExecutableData extends YamlConfiguration
{
    private static ExecutableData execs;

    public static ExecutableData getConfig()
    {
        if (execs == null)
        {
            execs = new ExecutableData();
        }
        return execs;
    }

    private static EXEC plugin;
    private File configFile;

    public ExecutableData()
    {
        plugin = EXEC.getPlugin(EXEC.class);
        configFile = new File(plugin.getDataFolder(), "execdata.yml");
        saveDefault();
        reload();
    }

    public void reload()
    {
        try
        {
            super.load(configFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void save()
    {
        try
        {
            super.save(configFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void saveDefault()
    {
        plugin.saveResource("execdata.yml", false);
    }

    // Methods

    private static ExecutableData edata = ExecutableData.getConfig();

    public static void createExecutable(String statement)
    {
        statement = statement.toLowerCase();
        edata.set(statement + ".statement", statement);
        edata.save();
    }

    public static void deleteExecutable(String statement)
    {
        statement = statement.toLowerCase();
        edata.set(statement, null);
        edata.save();
    }

    public static void addInstruction(String statement, String information)
    {
        List<String> instructions = edata.getStringList(statement + ".instructions");
        instructions.add(information);
        edata.set(statement + ".instructions", instructions);
        edata.save();
    }

    public static void removeInstruction(String statement, int index)
    {
        List<String> instructions = edata.getStringList(statement + ".instructions");
        instructions.remove(index);
        edata.set(statement + ".instructions", instructions);
        edata.save();
    }

    public static String getInstruction(String statement, int index)
    {
        return edata.getStringList(statement + ".instructions").get(index);
    }
}