# HiJoin

Un plugin de **Minecraft Paper/Spigot** optimizado que muestra títulos de bienvenida personalizables cuando los jugadores se conectan al servidor.

![Version](https://img.shields.io/badge/version-1.1.0-blue)
![Paper](https://img.shields.io/badge/Paper-1.21-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![License](https://img.shields.io/badge/license-MIT-green)

## Características

- **Títulos personalizables** - Configura el texto, color y estilo del título y subtítulo
- **Soporte de colores y estilos** - Bold, Italic, Underlined, Strikethrough, Obfuscated
- **Optimizado para CPU** - Caché de colores y validaciones eficientes
- **Fácil configuración** - Archivo `config.yml` sencillo
- **Recarga en caliente** - Usa `/hijoin reload` sin reiniciar el servidor
- **Mensajes de consola opcionales** - Deshabilitados por defecto para ahorrar recursos

## Requisitos

- **Paper 1.21+** (o compatible con Spigot)
- **Java 21+**

## Instalación

1. Descarga el archivo `.jar` desde [Releases](https://github.com/alexcuadroo/HiJoin/releases)
2. Coloca el archivo en la carpeta `plugins/` de tu servidor
3. Reinicia el servidor
4. Personaliza la configuración en `plugins/HiJoin/config.yml`
5. Usa `/hijoin reload` para aplicar los cambios

## Configuración

### Estructura del `config.yml`

```yaml
# Configuración del Título
title:
  text: "¡Bienvenido, {player}!"
  color: "LIGHT_PURPLE"
  styles:
    - "BOLD"

# Configuración del Subtítulo
subtitle:
  text: "✨ Que disfrutes tu estadía en el servidor ✨"
  color: "AQUA"
  styles:
    - "ITALIC"

# Tiempos en milisegundos
timing:
  fade-in: 500    # Tiempo de entrada
  stay: 3000      # Tiempo que permanece visible
  fade-out: 500   # Tiempo de salida

# Mensaje de consola (dejar vacío para deshabilitar)
console-message: ""
```

### Colores disponibles

`BLACK`, `DARK_BLUE`, `DARK_GREEN`, `DARK_AQUA`, `DARK_RED`, `DARK_PURPLE`, `GOLD`, `GRAY`, `DARK_GRAY`, `BLUE`, `GREEN`, `AQUA`, `RED`, `LIGHT_PURPLE`, `YELLOW`, `WHITE`

### Estilos disponibles

- `BOLD` - Texto en negrita
- `ITALIC` - Texto inclinado
- `UNDERLINED` - Texto subrayado
- `STRIKETHROUGH` - Tachado
- `OBFUSCATED` - Texto ofuscado

### Variables disponibles

- `{player}` - Nombre del jugador que se conecta

## Comandos

| Comando | Descripción | Permiso |
|---------|-------------|---------|
| `/hijoin` | Muestra la ayuda | ninguno |
| `/hijoin reload` | Recarga la configuración | `hijoin.admin` |
| `/hijoin info` | Información del plugin | ninguno |

## Permisos

```yaml
hijoin.admin:
  description: Permite usar comandos de administración
  default: op
```

## Optimizaciones

Este plugin está optimizado para servers con muchos jugadores:

- **Caché de colores** - Los colores se cachean al cargar la clase, sin búsquedas repetidas
- **Validaciones de listas** - No itera listas vacías innecesariamente
- **Mensajes de consola opcionales** - Deshabilitados por defecto para ahorrar CPU
- **Validación de permisos** - Previene accesos no autorizados

## Construcción desde código

```bash
# Clonar el repositorio
git clone https://github.com/alexcuadroo/HiJoin.git
cd HiJoin

# Compilar con Maven
mvn clean package

# El JAR compilado estará en target/HiJoin-1.1.0.jar
```

## Problemas y Sugerencias

¿Encontraste un bug o tienes una sugerencia? Abre un [issue](https://github.com/alexcuadroo/HiJoin/issues) en GitHub.

## Licencia

Este proyecto está licenciado bajo la licencia MIT. Ver [LICENSE](LICENSE) para más detalles.

## Autor

**alexcuadro** - [GitHub](https://github.com/alexcuadroo)

---

**Disfruta del plugin y que tus jugadores se sientan bienvenidos** 
