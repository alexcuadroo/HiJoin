package com.hijoin;

import com.hijoin.commands.HiJoinCommand;
import com.hijoin.listeners.PlayerJoinListener;
import com.hijoin.utils.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinTitlePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Guardar el config.yml por defecto si no existe
        saveDefaultConfig();

        // Registrar el listener de eventos pasando el plugin
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        // Registrar el comando /hijoin
        getCommand("hijoin").setExecutor(new HiJoinCommand(this));

        getLogger().info("═══════════════════════════════════");
        getLogger().info("✓ Plugin HiJoin activado correctamente, usa /hijoin para más información");
        getLogger().info("═══════════════════════════════════");

        // Verificar actualizaciones en GitHub
        new UpdateChecker(this, "alexcuadroo", "HiJoin").checkForUpdates();
    }

    @Override
    public void onDisable() {
        getLogger().info("HiJoin desactivado");
    }
}
