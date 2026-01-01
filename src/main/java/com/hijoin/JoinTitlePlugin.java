package com.hijoin;

import com.hijoin.commands.HiJoinCommand;
import com.hijoin.listeners.PlayerJoinListener;
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
        getLogger().info("✓ Plugin HiJoin activado correctamente");
        getLogger().info("✓ Mostrando títulos de bienvenida al conectar");
        getLogger().info("✓ Configuración cargada desde config.yml");
        getLogger().info("✓ Usa /hijoin para más información");
        getLogger().info("═══════════════════════════════════");
    }

    @Override
    public void onDisable() {
        getLogger().info("HiJoin desactivado");
    }
}
