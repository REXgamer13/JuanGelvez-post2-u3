# Tienda - Patrones Estructurales 2

Proyecto que implementa dos patrones de diseño estructurales: **Decorator** y **Facade**.

## 📋 Descripción General

Este proyecto demuestra cómo los patrones de diseño estructurales permiten organizar el código de forma flexible y mantenible, extendiendo funcionalidades sin modificar clases existentes.

## 🏗️ Patrones Implementados

### 1. Patrón Decorator

El patrón Decorator permite agregar responsabilidades dinámicamente a objetos sin modificar su estructura.

#### Composición de la Cadena de Decoradores

La cadena se construye en `DecoratorConfig` en el siguiente orden:

```
AUDITORIA → VALIDACION → LOGGING → BASE
```


#### Flujo de Ejecución

Cuando se procesa una orden, el flujo es:

1. **AUDITORIA**: Registra la auditoría de la orden y el resultado final
2. **VALIDACION**: Valida que el ID de la orden y el monto sean válidos
3. **LOGGING**: Registra el inicio y fin del procesamiento, incluyendo el tiempo
4. **BASE**: Procesa efectivamente la orden

#### Clases Implementadas

- **`OrdenServicio.java`**: Interfaz que define el contrato para procesar órdenes
- **`OrdenServicioBase.java`**: Implementación base que procesa las órdenes
- **`OrdenServicioDecorator.java`**: Clase abstracta base para todos los decoradores
- **`LoggingDecorator.java`**: Registra logs del procesamiento
- **`ValidacionDecorator.java`**: Valida los parámetros de la orden
- **`AuditoriaDecorator.java`**: Registra auditoría de las operaciones
- **`DecoratorConfig.java`**: Configuración que construye la cadena de decoradores

#### Ejemplo de Salida

```
[VALIDACION] Orden ORD-001 validada correctamente.
[LOG] Iniciando procesamiento: ORD-001
BASE: Procesando orden ORD-001 por $50000.0
[LOG] Completado en 0ms. Resultado: PROCESADA:ORD-001
[AUDITORIA] 2026-03-23T10:29:14.872390700 — Orden: ORD-001 — Resultado: PROCESADA:ORD-001
```

#### Reglas de Validación

- **ID de Orden**: No puede ser nulo o vacío
- **Monto**: Debe estar entre $1,000 y $50,000,000

### 2. Patrón Facade

El patrón Facade proporciona una interfaz simplificada para un conjunto de subsistemas complejos.

#### Composición de NotificacionFacade

`NotificacionFacade` encapsula tres servicios de notificación:

- **EmailService**: Envía correos electrónicos
- **SMSService**: Envía mensajes SMS
- **PushService**: Envía notificaciones push

#### Operaciones Simplificadas

El cliente solo necesita una llamada a `notificacionFacade` para notificar por los tres canales:

```java
notificacionFacade.notificarCompraExitosa(
    "cliente@email.com",
    "+57 300 1234567",
    "push_token_xyz",
    "ORD-001"
);
```

Esto internamente ejecuta:
- `emailService.enviar(...)`
- `smsService.enviar(...)`
- `pushService.enviar(...)`

## 🧪 Tests

El proyecto incluye 4 tests en `DecoratorTest.java`:

1. **`testOrdenValida`**: Verifica que una orden con parámetros válidos se procesa correctamente
2. **`testOrdenMontoInvalido`**: Verifica que una orden con monto inválido lanza `IllegalArgumentException`
3. **`testOrdenIdVacio`**: Verifica que una orden con ID vacío lanza `IllegalArgumentException`
4. **`testDecoradorIndividualLogging`**: Verifica que el decorador de logging puede usarse independientemente

### Ejecutar los Tests

```bash
./mvnw.cmd test
```

**Resultado esperado**: `BUILD SUCCESS` - 5 tests ejecutados, 0 fallos

## 🚀 Tecnologías

- **Java 17**
- **Spring Boot 4.0.4**
- **Maven**
- **JUnit 5**

## 📁 Estructura del Proyecto

```
src/
├── main/java/com/universidad/tienda/
│   ├── DecoratorConfig.java                 (Configuración de la cadena)
│   ├── TiendaPatronesEstructurales2Application.java
│   ├── decorator/
│   │   ├── OrdenServicio.java               (Interfaz)
│   │   ├── OrdenServicioBase.java           (Implementación base)
│   │   ├── OrdenServicioDecorator.java      (Clase abstracta decorador)
│   │   ├── AuditoriaDecorator.java
│   │   ├── ValidacionDecorator.java
│   │   └── LoggingDecorator.java
│   └── facade/
│       ├── NotificacionFacade.java
│       ├── EmailService.java
│       ├── SMSService.java
│       └── PushService.java
└── test/java/com/universidad/tienda/
    └── DecoratorTest.java
```

## 🔑 Puntos Clave

✓ **Decorator sin modificación**: La cadena se construye en `DecoratorConfig`, `OrdenServicioBase` permanece sin cambios
✓ **Orden de ejecución verificable**: Los logs muestran claramente: AUDITORIA → VALIDACION → LOG → BASE
✓ **Propagación de excepciones**: Las excepciones de validación se propagan correctamente hacia el cliente
✓ **Facade funcional**: Un único método notifica por tres canales diferentes
✓ **Tests pasando**: Los 4 tests de decorador + 1 de aplicación pasan exitosamente

## 📝 Notas de Implementación

### Decisiones Arquitectónicas

1. **Separación de responsabilidades**: Cada decorador tiene una única responsabilidad:
   - LoggingDecorator: Solo registra tiempos
   - ValidacionDecorator: Solo valida datos
   - AuditoriaDecorator: Solo audita operaciones

2. **Inyección de dependencias**: Se usa Spring Framework para inyectar servicios y configurar la cadena de decoradores

3. **Facade pattern**: NotificacionFacade simplifica múltiples integraciones de comunicación

### Ventajas de este Diseño

- ✅ Flexibilidad: Nuevos decoradores se pueden agregar fácilmente
- ✅ Reutilización: Los decoradores pueden combinarse de diferentes formas
- ✅ Testabilidad: Cada componente puede testearse independientemente
- ✅ Mantenibilidad: Cambios en un decorador no afectan a otros

## 👨‍💻 Autor

Universidad - Proyecto de Patrones de Diseño Estructurales


