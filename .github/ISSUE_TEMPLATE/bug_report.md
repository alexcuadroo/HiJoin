name: Bug Report
description: Reportar un error o problema
title: "[BUG] "
labels: ["bug"]

body:
  - type: markdown
    attributes:
      value: "## Descripción del bug"
      
  - type: textarea
    attributes:
      label: Descripción clara y concisa
      description: Describe el bug lo más detallado posible
      placeholder: "El plugin hace... cuando debería..."
    validations:
      required: true

  - type: textarea
    attributes:
      label: Pasos para reproducir
      description: Pasos para reproducir el comportamiento
      placeholder: |
        1. Ve a '...'
        2. Configura '...'
        3. Ejecuta '...'
        4. Ve el error
    validations:
      required: true

  - type: textarea
    attributes:
      label: Comportamiento esperado
      description: Describe qué debería pasar
    validations:
      required: true

  - type: textarea
    attributes:
      label: Comportamiento actual
      description: Describe qué está pasando
    validations:
      required: true

  - type: dropdown
    attributes:
      label: Versión del plugin
      options:
        - "1.1.0"
        - "1.0.0"
    validations:
      required: true

  - type: dropdown
    attributes:
      label: Versión de Paper
      options:
        - "1.21"
        - "1.20"
        - "Otro"
    validations:
      required: true

  - type: textarea
    attributes:
      label: Logs o errores
      description: Si hay errores en consola, pégalos aquí
      render: bash

  - type: textarea
    attributes:
      label: Información adicional
      description: Información adicional que pueda ayudar
