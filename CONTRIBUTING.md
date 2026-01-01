# Contribuyendo a HiJoin

Primero que nada, ¡gracias por considerar contribuir a HiJoin!

## Cómo puedo contribuir

### Reportando bugs

Los bugs se rastrean usando GitHub Issues. Cuando reportes un bug, por favor incluye:

- **Descripción clara** del problema
- **Pasos para reproducir** el comportamiento
- **Comportamiento esperado vs actual**
- **Capturas de pantalla** si es relevante
- **Versión del plugin** y **versión de Paper**
- **Logs de error** si es aplicable

### Sugiriendo mejoras

Las mejoras también se sugieren usando GitHub Issues. Por favor incluye:

- **Descripción clara** de la mejora
- **Caso de uso** o problema que resuelve
- **Solución propuesta** (opcional)
- **Ejemplos** de cómo funcionaría

### Pull Requests

1. Haz un Fork del repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

#### Guías de desarrollo

- Usa Java 21+
- Sigue el estilo de código existente
- Agrega comentarios en código complejo
- Prueba los cambios en un servidor Paper 1.21+
- Actualiza la documentación si es necesario

## Styleguide

### Commits

- Usa mensajes descriptivos en inglés o español
- Ejemplos:
  - Add feature description
  - Fix bug description
  - Update documentation
  - Optimize performance

### Código Java

```java
// Usa nombres descriptivos
private static final String DEFAULT_COLOR = "WHITE";

// Comenta código complejo
public void complexMethod() {
    // Explicación de qué hace
}

// Manejo de excepciones adecuado
try {
    // código
} catch (IllegalArgumentException e) {
    // Manejo específico
}
```

## Proceso de revisión

1. Los mantenedores revisarán tu PR
2. Se puede solicitar cambios
3. Una vez aprobado, se mergeará a main
4. Tu cambio estará en la siguiente versión

## Licencia

Al contribuir, aceptas que tus cambios se licencien bajo la licencia MIT.

---

¡Gracias por contribuir a HiJoin!
