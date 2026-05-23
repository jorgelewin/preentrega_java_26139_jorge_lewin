# Sistema de Gestión de Artículos

[![Estado](https://img.shields.io/badge/estado-en%20desarrollo-yellow)](https://shields.io)
[![Java](https://img.shields.io/badge/java-8%2B-blue)](https://www.oracle.com/java/)
[![Tipo](https://img.shields.io/badge/tipo-aplicaci%C3%B3n%20de%20consola-brightgreen)](https://shields.io)
[![Arquitectura](https://img.shields.io/badge/arquitectura-POO-informational)](https://shields.io)

> Aplicación de consola en Java para administrar artículos y categorías con una estructura clara, extensible y alineada a buenas prácticas de documentación.

Aplicación de consola desarrollada en Java para la administración de artículos y categorías de una tienda. El proyecto fue estructurado bajo principios de programación orientada a objetos, con el objetivo de presentar una base clara, escalable y alineada con prácticas estándar de documentación y diseño de software.

## Índice

- [Estado del proyecto](#estado-del-proyecto)
- [Resumen ejecutivo](#resumen-ejecutivo)
- [Funcionalidades](#funcionalidades)
- [Tecnologías y estándares aplicados](#tecnologías-y-estándares-aplicados)
- [Arquitectura del proyecto](#arquitectura-del-proyecto)
- [Estructura del repositorio](#estructura-del-repositorio)
- [Reglas de negocio](#reglas-de-negocio)
- [Requisitos previos](#requisitos-previos)
- [Cómo compilar y ejecutar](#cómo-compilar-y-ejecutar)
- [Flujo operativo](#flujo-operativo)
- [Consideraciones de diseño](#consideraciones-de-diseño)
- [Buenas prácticas aplicadas y recomendadas](#buenas-prácticas-aplicadas-y-recomendadas)
- [Evolución sugerida](#evolución-sugerida)
- [Mantenimiento](#mantenimiento)

## Estado del proyecto

| Campo | Descripción |
| --- | --- |
| Estado funcional | En desarrollo |
| Persistencia | En memoria |
| Interfaz | Consola / CLI |
| Lenguaje | Java |
| Alcance | Académico / demostrativo |

## Resumen ejecutivo

El sistema permite gestionar el ciclo de vida básico de dos entidades principales: **categorías** y **artículos**. La solución incorpora validaciones, relaciones entre objetos, herencia, abstracción y un repositorio genérico para almacenamiento temporal en memoria.

### Enfoque del proyecto

- claridad estructural
- separación de responsabilidades
- reutilización de código
- consistencia en la interacción por consola
- facilidad de mantenimiento

[![CRUD](https://img.shields.io/badge/CRUD-Categor%C3%ADas%20y%20Art%C3%ADculos-blue)](https://shields.io)
[![Validación](https://img.shields.io/badge/Validaci%C3%B3n-Entrada%20por%20consola-orange)](https://shields.io)
[![Repositorio](https://img.shields.io/badge/Repositorio-Gen%C3%A9rico-success)](https://shields.io)

## Funcionalidades

### Gestión de categorías

- Alta de categorías.
- Listado de categorías.
- Consulta por código.
- Modificación de registros.
- Eliminación con validación de dependencias.

### Gestión de artículos

- Alta de artículos.
- Listado general.
- Consulta por código.
- Modificación de datos.
- Eliminación individual.

### Tipos de artículo

- `ArticuloElectronico`
- `ArticuloAlimenticio`

### Validaciones aplicadas

- Campos obligatorios no vacíos.
- Valores numéricos no negativos.
- Categoría existente antes de asignarla a un artículo.
- Restricción de eliminación de categorías con artículos asociados.
- Generación automática de códigos.

## Tecnologías y estándares aplicados

- **Java**
- **POO**: clases, herencia, abstracción y polimorfismo
- **Interfaces** para contratos de comportamiento
- **Genéricos** para reutilización del repositorio
- **Colecciones Java** (`ArrayList`)
- **Consola / CLI** como capa de interacción

> El proyecto no utiliza frameworks ni dependencias externas.

## Arquitectura del proyecto

### `App.java`
Punto de entrada de la aplicación. Orquesta el arranque del sistema, crea los repositorios y conecta los menús principales.

```java
// Orquestación general del sistema
```

### `model`
Contiene las entidades de negocio:

- `Articulo`: clase abstracta base.
- `ArticuloElectronico`: especialización con garantía.
- `ArticuloAlimenticio`: especialización con vencimiento.
- `Categoria`: entidad de clasificación para los artículos.

### `interfaces`
Define contratos comunes:

- `Identificable`: expone un código único.
- `Calculable`: define el cálculo del precio final.

### `repository`
Incluye `Repositorio<T extends Identificable>`, responsable del almacenamiento temporal en memoria y de las operaciones CRUD básicas sobre objetos identificables.

### `menu`
Contiene la capa de presentación por consola:

- `Menu`: base abstracta con utilidades de lectura y validación.
- `MenuArticulos`: flujo de gestión de artículos.
- `MenuCategorias`: flujo de gestión de categorías.

### `utils`
Agrupa funciones transversales:

- `Secuencias`: generación automática de códigos.
- `Validaciones`: validación reutilizable de datos.

## Estructura del repositorio

```text
preentrega_template/
├─ src/
│  └─ com/techlab/articulo/
│     ├─ App.java
│     ├─ interfaces/
│     │  ├─ Calculable.java
│     │  └─ Identificable.java
│     ├─ menu/
│     │  ├─ Menu.java
│     │  ├─ MenuArticulos.java
│     │  └─ MenuCategorias.java
│     ├─ model/
│     │  ├─ Articulo.java
│     │  ├─ ArticuloAlimenticio.java
│     │  ├─ ArticuloElectronico.java
│     │  └─ Categoria.java
│     ├─ repository/
│     │  └─ Repositorio.java
│     └─ utils/
│        ├─ Secuencias.java
│        └─ Validaciones.java
├─ build/
│  └─ classes/
└─ README.md
```

## Reglas de negocio

1. No se puede crear un artículo si no existen categorías cargadas.
2. Todo artículo debe asociarse a una categoría válida.
3. No se puede eliminar una categoría si existe al menos un artículo relacionado.
4. Los códigos se generan automáticamente.
5. La entrada de datos debe ser validada antes de persistirse en memoria.

## Requisitos previos

- **JDK 8 o superior**
- Terminal compatible con comandos de Java
- Opcional: IntelliJ IDEA, Eclipse o Visual Studio Code

## Cómo compilar y ejecutar

### Windows PowerShell

Desde la raíz del proyecto, ejecutar:

```powershell
javac -d build/classes src\com\techlab\articulo\interfaces\*.java src\com\techlab\articulo\model\*.java src\com\techlab\articulo\repository\*.java src\com\techlab\articulo\utils\*.java src\com\techlab\articulo\menu\*.java src\com\techlab\articulo\App.java
java -cp build/classes com.techlab.articulo.App
```

### Acceso rápido

- Compilación: `javac`
- Ejecución: `java -cp build/classes com.techlab.articulo.App`
- Carpeta fuente: `src`

### Desde un IDE

1. Abrir el proyecto como proyecto Java.
2. Marcar `src` como directorio de código fuente.
3. Ejecutar `com.techlab.articulo.App`.

## Flujo operativo

1. Iniciar la aplicación.
2. Acceder al menú de categorías y registrar al menos una categoría.
3. Acceder al menú de artículos y dar de alta productos asociados.
4. Consultar, modificar o eliminar registros según necesidad.

## Consideraciones de diseño

- La persistencia es temporal y reside exclusivamente en memoria.
- La lógica de interacción se centraliza en los menús.
- El `main` actúa como orquestador y no concentra la lógica de negocio.
- El proyecto mantiene una estructura simple para facilitar su extensión futura.

> Nota: este enfoque favorece una implementación didáctica y fácilmente mantenible.

## Buenas prácticas aplicadas y recomendadas

- Separación por responsabilidad.
- Reutilización de validaciones.
- Evitar duplicación de reglas.
- Encapsulamiento del acceso a datos.
- Nomenclatura consistente de paquetes y clases.
- Documentación clara de entradas, salidas y restricciones.
- Inclusión de un `.gitignore` para excluir artefactos compilados.

## Evolución sugerida

Para un escenario productivo o de mayor alcance, se recomienda incorporar:

- Persistencia en base de datos.
- Capa de servicios formal.
- Tests unitarios con JUnit.
- Manejo de logs.
- Exportación e importación de datos.
- Filtros, búsquedas y paginación.

## Mantenimiento

Si el proyecto crece, conviene mantener este archivo actualizado con:

- nuevas funcionalidades,
- cambios en la estructura,
- instrucciones de compilación,
- restricciones del modelo,
- y criterios de uso vigentes.

### Recomendación operativa

Cuando agregues nuevas funcionalidades, actualizá también:

- el índice,
- la tabla de estado,
- los ejemplos de ejecución,
- y las reglas de negocio que cambien.

