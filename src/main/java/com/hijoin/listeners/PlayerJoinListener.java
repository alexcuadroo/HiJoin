package com.hijoin.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.util.List;

public class PlayerJoinListener implements Listener {

    private final JavaPlugin plugin;

    public PlayerJoinListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = plugin.getConfig();

        // Obtener configuración del título
        String titleText = config.getString("title.text", "¡Bienvenido, {player}!")
                .replace("{player}", player.getName());
        String titleColor = config.getString("title.color", "LIGHT_PURPLE");
        List<String> titleStyles = config.getStringList("title.styles");

        // Obtener configuración del subtítulo
        String subtitleText = config.getString("subtitle.text", "✨ Que disfrutes tu estadía en el servidor ✨");
        String subtitleColor = config.getString("subtitle.color", "AQUA");
        List<String> subtitleStyles = config.getStringList("subtitle.styles");

        // Obtener configuración de tiempos
        long fadeIn = config.getLong("timing.fade-in", 500);
        long stay = config.getLong("timing.stay", 3000);
        long fadeOut = config.getLong("timing.fade-out", 500);

        // Crear componente del título con color y estilos
        Component title = Component.text(titleText)
                .color(getColor(titleColor));
        if (!titleStyles.isEmpty()) {
            for (String style : titleStyles) {
                try {
                    title = title.decorate(TextDecoration.valueOf(style.toUpperCase()));
                } catch (IllegalArgumentException ignored) {
                    // Estilo inválido, ignorar
                }
            }
        }

        // Crear componente del subtítulo con color y estilos
        Component subtitle = Component.text(subtitleText)
                .color(getColor(subtitleColor));
        if (!subtitleStyles.isEmpty()) {
            for (String style : subtitleStyles) {
                try {
                    subtitle = subtitle.decorate(TextDecoration.valueOf(style.toUpperCase()));
                } catch (IllegalArgumentException ignored) {
                    // Estilo inválido, ignorar
                }
            }
        }

        // Crear el título con tiempos configurables
        Title titleWithTiming = Title.title(
                title,
                subtitle,
                Title.Times.times(
                        Duration.ofMillis(fadeIn),
                        Duration.ofMillis(stay),
                        Duration.ofMillis(fadeOut)
                )
        );

        // Enviar el título al jugador
        player.showTitle(titleWithTiming);

        // Mensaje en consola (solo si está habilitado)
        String consoleMessage = config.getString("console-message", "");
        if (consoleMessage != null && !consoleMessage.isEmpty()) {
            String consoleMsg = consoleMessage.replace("{player}", player.getName());
            plugin.getLogger().info(consoleMsg);
        }
    }

    /**
     * Obtiene un NamedTextColor por nombre usando Map cacheado
     * Esto reduce CPU al evitar llamadas a .toUpperCase() repetidas
     */
    private static final java.util.Map<String, NamedTextColor> COLOR_CACHE = java.util.Map.ofEntries(
            java.util.Map.entry("BLACK", NamedTextColor.BLACK),
            java.util.Map.entry("DARK_BLUE", NamedTextColor.DARK_BLUE),
            java.util.Map.entry("DARK_GREEN", NamedTextColor.DARK_GREEN),
            java.util.Map.entry("DARK_AQUA", NamedTextColor.DARK_AQUA),
            java.util.Map.entry("DARK_RED", NamedTextColor.DARK_RED),
            java.util.Map.entry("DARK_PURPLE", NamedTextColor.DARK_PURPLE),
            java.util.Map.entry("GOLD", NamedTextColor.GOLD),
            java.util.Map.entry("GRAY", NamedTextColor.GRAY),
            java.util.Map.entry("DARK_GRAY", NamedTextColor.DARK_GRAY),
            java.util.Map.entry("BLUE", NamedTextColor.BLUE),
            java.util.Map.entry("GREEN", NamedTextColor.GREEN),
            java.util.Map.entry("AQUA", NamedTextColor.AQUA),
            java.util.Map.entry("RED", NamedTextColor.RED),
            java.util.Map.entry("LIGHT_PURPLE", NamedTextColor.LIGHT_PURPLE),
            java.util.Map.entry("YELLOW", NamedTextColor.YELLOW),
            java.util.Map.entry("WHITE", NamedTextColor.WHITE)
    );

    private NamedTextColor getColor(String colorName) {
        if (colorName == null || colorName.isEmpty()) {
            return NamedTextColor.WHITE;
        }
        return COLOR_CACHE.getOrDefault(colorName.toUpperCase(), NamedTextColor.WHITE);
    }
}
