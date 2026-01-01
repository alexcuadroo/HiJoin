package com.hijoin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class HiJoinCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public HiJoinCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        
        if (args.length == 0) {
            sender.sendMessage("§6═══════════════════════════════════");
            sender.sendMessage("§e§lHiJoin Plugin");
            sender.sendMessage("§6═══════════════════════════════════");
            sender.sendMessage("§b/hijoin reload §7- Recarga la configuración");
            sender.sendMessage("§b/hijoin info §7- Muestra información del plugin");
            sender.sendMessage("§6═══════════════════════════════════");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("hijoin.admin")) {
                sender.sendMessage("§cNo tienes permisos para ejecutar este comando.");
                return true;
            }
            plugin.reloadConfig();
            sender.sendMessage("§a✓ Configuración recargada exitosamente!");
            plugin.getLogger().info("[HiJoin] Configuración recargada por " + sender.getName());
            return true;
        }

        if (args[0].equalsIgnoreCase("info")) {
            sender.sendMessage("§6═══════════════════════════════════");
            sender.sendMessage("§eHiJoin v1.1.0");
            sender.sendMessage("§eMuestra títulos de bienvenida al conectar");
            sender.sendMessage("§eUsa /hijoin reload para recargar la configuración");
            sender.sendMessage("§6═══════════════════════════════════");
            return true;
        }

        sender.sendMessage("§cComando desconocido. Usa /hijoin para ver los comandos disponibles.");
        return false;
    }
}
