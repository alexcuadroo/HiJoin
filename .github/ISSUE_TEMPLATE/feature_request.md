name: Feature Request
description: Sugerir una nueva característica
title: "[FEATURE] "
labels: ["enhancement"]

body:
  - type: markdown
    attributes:
      value: "## Solicitud de nueva característica"
      
  - type: textarea
    attributes:
      label: Descripción de la solicitud
      description: Describe la característica que te gustaría que tuviera
      placeholder: "Me gustaría que el plugin..."
    validations:
      required: true

  - type: textarea
    attributes:
      label: Problema o caso de uso
      description: ¿Qué problema resolvería esto? ¿Cuál es el caso de uso?
      placeholder: "Actualmente, cuando... necesito que..."
    validations:
      required: true

  - type: textarea
    attributes:
      label: Solución propuesta
      description: Describe cómo imaginas que funcione
    validations:
      required: false

  - type: textarea
    attributes:
      label: Alternativas consideradas
      description: ¿Hay otras formas de resolver esto?
    validations:
      required: false

  - type: textarea
    attributes:
      label: Contexto adicional
      description: ¿Hay algo más que debamos saber?
    validations:
      required: false
