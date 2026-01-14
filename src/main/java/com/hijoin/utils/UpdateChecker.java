package com.hijoin.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class UpdateChecker {

  private final JavaPlugin plugin;
  private final String githubUser;
  private final String githubRepo;

  public UpdateChecker(JavaPlugin plugin, String githubUser, String githubRepo) {
    this.plugin = plugin;
    this.githubUser = githubUser;
    this.githubRepo = githubRepo;
  }

  /**
   * Verifica si hay una nueva versión disponible de forma asíncrona
   */
  public void checkForUpdates() {
    CompletableFuture.runAsync(() -> {
      try {
        String currentVersion = plugin.getPluginMeta().getVersion();
        String latestVersion = getLatestVersion();

        if (latestVersion == null) {
          plugin.getLogger().warning("No se pudo verificar actualizaciones en GitHub");
          return;
        }

        if (isNewerVersion(currentVersion, latestVersion)) {
          plugin.getLogger().warning("═══════════════════════════════════");
          plugin.getLogger().warning("¡Nueva versión disponible!");
          plugin.getLogger().warning("Versión actual: " + currentVersion);
          plugin.getLogger().warning("Última versión: " + latestVersion);
          plugin.getLogger()
              .warning("Descarga: https://github.com/" + githubUser + "/" + githubRepo + "/releases/latest");
          plugin.getLogger().warning("═══════════════════════════════════");
        } else {
          plugin.getLogger().info("Plugin actualizado (v" + currentVersion + ")");
        }

      } catch (Exception e) {
        plugin.getLogger().warning("Error al verificar actualizaciones: " + e.getMessage());
      }
    });
  }

  /**
   * Obtiene la última versión desde la API de GitHub
   */
  private String getLatestVersion() {
    try {
      String apiUrl = "https://api.github.com/repos/" + githubUser + "/" + githubRepo + "/releases/latest";
      URL url = URI.create(apiUrl).toURL();
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
      connection.setRequestProperty("User-Agent", plugin.getName() + "/" + plugin.getPluginMeta().getVersion());
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);

      int responseCode = connection.getResponseCode();
      if (responseCode == 200) {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
          StringBuilder response = new StringBuilder();
          String line;

          while ((line = reader.readLine()) != null) {
            response.append(line);
          }

          // Parsear la respuesta JSON
          JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
          if (!jsonObject.has("tag_name")) {
            plugin.getLogger().warning("Respuesta de GitHub sin tag_name");
            return null;
          }
          String tagName = jsonObject.get("tag_name").getAsString();
          String normalized = normalizeTag(tagName);
          if (normalized.isEmpty()) {
            plugin.getLogger().warning("No se pudo interpretar la versión del tag: " + tagName);
            return null;
          }
          return normalized;
        }
      } else {
        plugin.getLogger()
            .warning("GitHub API devolvió estado " + responseCode + ": " + connection.getResponseMessage());
      }

    } catch (Exception e) {
      plugin.getLogger().warning("No se pudo conectar a GitHub: " + e.getMessage());
    }
    return null;
  }

  /**
   * Compara dos versiones para determinar si la segunda es más nueva
   * Formato esperado: X.Y.Z (ej: 1.1.0)
   */
  private boolean isNewerVersion(String current, String latest) {
    try {
      String[] currentParts = current.split("\\.");
      String[] latestParts = latest.split("\\.");

      int maxLength = Math.max(currentParts.length, latestParts.length);

      for (int i = 0; i < maxLength; i++) {
        int currentPart = i < currentParts.length ? Integer.parseInt(currentParts[i]) : 0;
        int latestPart = i < latestParts.length ? Integer.parseInt(latestParts[i]) : 0;

        if (latestPart > currentPart) {
          return true;
        } else if (latestPart < currentPart) {
          return false;
        }
      }

      return false; // Son iguales
    } catch (Exception e) {
      return false;
    }
  }

  private String normalizeTag(String rawTag) {
    if (rawTag == null) {
      return "";
    }
    String cleaned = rawTag.trim();
    // Quitar prefijo v o V y un posible punto siguiente (ej: v1.2.0, v.1.2.0)
    cleaned = cleaned.replaceFirst("^[vV]\\.?", "");
    // Quitar punto inicial si queda (ej: .1.2.0)
    while (cleaned.startsWith(".")) {
      cleaned = cleaned.substring(1);
    }
    return cleaned;
  }
}
