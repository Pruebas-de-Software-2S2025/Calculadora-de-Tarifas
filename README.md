# Proyecto: Calculadora de Tarifas de Estacionamiento  
Programa de línea de comandos en Java con pruebas unitarias JUnit 5  

Momento de para hacer uso de **TDD**  
**Tarea individual**
**Prohibido el vibe coding

## 1. Enunciado

Desarrollar un sistema que gestione el **cobro de un estacionamiento** para distintos tipos de vehículos.

El sistema debe:

- Registrar la **entrada** de vehículos al estacionamiento.  
- Registrar la **salida** y calcular el monto a pagar según reglas de negocio.  
- Manejar distintos **tipos de vehículo** con tarifas diferentes.  
- Calcular el cobro por **bloques de tiempo** (tramos de 30 minutos).  
- Entregar algunas consultas básicas (tickets abiertos, tickets cerrados, total recaudado del día).

Todo se opera por **consola** mediante un menú de texto.

El código debe venir acompañado de:

- **Pruebas unitarias JUnit 5**.  
- **Medición de cobertura de código** con alguna de estas herramientas:
  - EclEmma / JaCoCo  

---

## 2. Requisitos( funcionales)

### 2.1. Gestión de Tickets de Estacionamiento

| Operación           | Detalles                                                                                  |
|---------------------|-------------------------------------------------------------------------------------------|
| Registrar entrada   | Crea un ticket de estacionamiento con: `idTicket`, `patente`, `tipoVehiculo` (AUTO, MOTO, CAMIONETA), `fechaHoraEntrada`, `estado` (ABIERTO). |
| Registrar salida    | Completa un ticket **abierto** agregando: `fechaHoraSalida`, `montoCobrado`, cambio de `estado` a "Cerrado" |
| Listar tickets abiertos | Muestra todos los tickets cuyo estado es "Abierto"                                 |
| Listar tickets cerrados | Muestra el histórico de tickets con estado "Cerrado".                                  |
| Restricciones       | No se puede registrar salida de un ticket inexistente o ya cerrado.                      |

> Puedes modelar tickets y vehículos como una o varias clases, pero el sistema debe tener clara la idea de un **ticket abierto/cerrado**.

---

### 2.2. Cálculo de tarifas

Cuando se registra la "salida" de un vehículo, el sistema debe calcular el valor a pagar según estas reglas:

1. **Duración del estacionamiento**

   - Se calcula la duración en minutos entre `fechaHoraEntrada` y `fechaHoraSalida`.
   - Si la duración es menor o igual a 0 minutos, la operación debe considerarse inválida (no se cobrar).

2. **Bloques de tiempo**

   - El cobro se hace por **bloques de 30 minutos**, redondeando **hacia arriba**.  
     - Ejemplos:
       - 1 a 30 min → 1 bloque  
       - 31 a 60 min → 2 bloques  
       - 61 a 90 min → 3 bloques, etc.

3. **Tarifa por tipo de vehículo (por bloque de 30 minutos)**

| Tipo de vehículo | Tarifa por bloque (ejemplo) |
|------------------|-----------------------------|
| AUTO             | $800                        |
| MOTO             | $500                        |
| CAMIONETA        | $1.000                      |

SE sugiere usar estos valores en la implementación

4. **Tope diario**

   - El monto total a pagar por un ticket **no puede exceder** un máximo diario (por día calendario).  
   - Para simplificar, usa un "tope único" para todos los vehículos:
     - **Tope diario**: $15.000  
   - Si el cálculo por bloques supera este monto, se cobra $15.000 (no hay un teximetro eterno).

5. **Descuento fin de semana**

   - Si la **fecha de entrada** del ticket corresponde a **sábado o domingo**, se aplica un "10 % de descuento" al valor final (después de aplicar el tope diario, si corresponde).
   - El descuento debe redondearse hacia abajo al entero más cercano.

Toda esta lógica de duración, bloques, tope y descuento debe ser fácilmente testeable con pruebas unitarias.


### 2.3. Consultas y reportes simples

El sistema debe permitir:

| Operación                    | Detalles                                                                                                              |
|------------------------------|-----------------------------------------------------------------------------------------------------------------------|
| Mostrar detalle de un ticket | Consultar por `idTicket` y mostrar patente, tipo, tiempo estacionado, monto cobrado (si está cerrado) o indicar que aún está abierto. |
| Mostrar total recaudado del día | Entregar la suma de `montoCobrado` de todos los tickets cerrados cuya fecha (entrada o salida, a definir en tu diseño) corresponde al **día actual**. |
| Validaciones básicas         | Manejar el caso en que no existan tickets para la consulta.                                                          |

---

## 3. Requisitos técnicos

| Ítem         | Detalle                                                                                   |
|-------------|--------------------------------------------------------------------------------------------|
| Tipo de app | Por consola (CLI)                                                                         |
| Lenguaje    | Java 21+                                                                                  |
| Build       | Maven o Gradle (indicar en el README cómo compilar/ejecutar)                              |
| Pruebas     | JUnit 5 + assertions estándar                                                             |
| Persistencia| En memoria (no se requieren archivos ni base de datos)                                    |
| Estilo      | Diseño OO limpio (clases para entidades, lógica de cálculo separada, etc.)               |
| Medir cobertura | Usar EclEmma (JaCoCo)                                                |
| TDD         | Se sugiere uso de TDD en el desarrollo (no obligatorio, pero lo recomendado)                 |
| Modalidad   | Trabajo individual                                                                        |

---

## 4. Menú principal (CLI)

El sistema debe ofrecer un menú similar a este:

1. Registrar entrada de vehículo  
2. Registrar salida de vehículo (calcular cobro)  
3. Listar tickets abiertos  
4. Listar tickets cerrados  
5. Mostrar detalle de un ticket  
6. Mostrar total recaudado del día  
7. Salir  

> Puedes reorganizar o subdividir el menú mientras mantengas estas funcionalidades.

---

## 5. Entregables
Repositorio GitHub (público) con:
- Código fuente organizado
- Suite de tests JUnit.
- README.md que incluya:
  - Descripción del diseño (diagrama UML o otro), no incluir enlaces a repositorios personales (por ejemplo en Sharepoint).
  - Instrucciones para compilar, ejecutar y probar.
  - Ejemplo de salida de tests.
  - Licencia
  - Otras onsideraciones vistas previamente en curso
  - Responde a pregunta: **¿Qué tipo de cobertura he medido y por qué?**

---

## 6. Dudas y preguntas

Cualquier duda o descubrimiento, publícalo en el **foro de la semana**, para que las respuestas queden visibles para todo el curso.
