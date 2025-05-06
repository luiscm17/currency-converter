# Lineamientos Arquitectónicos del Proyecto

## Estructura MVC
1. **Modelo**: Clases de dominio y DTOs (`ExchangeRateResponse`)
2. **Vista**: Consola (`CLIHandler` para interacción por terminal)
3. **Controlador**: `CurrencyConverter` (orquestador de la lógica)

## Capas de la Aplicación
- **Capa Client**: `ApiClient` (comunicación HTTP)
- **Capa Service**: Lógica de negocio (`CurrencyConverter`)
- **Capa Model**: Representación de datos

## Buenas Prácticas
✅ Inyección de dependencias en constructores
✅ Separación clara de responsabilidades
✅ Manejo centralizado de credenciales (API_KEY)
✅ Validación de datos en modelos

## Próximos Pasos
1. Implementar patrón Repository para acceso a datos
2. Añadir manejo de errores personalizados
3. Crear tests unitarios para cada capa

src/
└── main/
    └── java/
        └── com/
            └── currencyexchange/
                ├── client/       # Comunicación HTTP con APIs externas
                ├── model/        # POJOs para respuestas JSON (ej: ExchangeRateResponse)
                ├── service/      # Lógica de conversión y validaciones
                └── App.java      # Punto de entrada principal
                ├── config/      # Configuración de API y parámetros
                ├── exception/   # Errores personalizados (ej: InvalidCurrencyException)
                ├── dto/         # Objetos para transferencia entre capas
                └── util/        # Herramientas compartidas (formateadores, validadores)

## Explicación de la estructura:

1. client : Aquí irían las clases que manejan la conexión HTTP (como ExchangeRateClient ), siguiendo el principio de responsabilidad única
2. model : Contendría las clases DTO (Data Transfer Object) para mapear la respuesta JSON de la API
3. service : Albergaría la lógica de negocio para los cálculos de conversión y validaciones
4. App.java : Coordinaría la inicialización e interacción entre componentes


## Checklist de funcionalidades principales:

1. [x] Consumo de API de tasas de cambio (ya implementado parcialmente)
2. [x] Conversión entre monedas (implementado en CurrencyConverter)
3. [ ] Validación de códigos ISO (CurrencyValidator)
4. [ ] Validación de entradas en servicio (CurrencyConverter)
5. [ ] Manejo de errores personalizados (InvalidCurrencyException)
6. [ ] Historial de conversiones (ConversionHistoryService)
7. [x] Interfaz de usuario básica (CLI) - Implementada (clase CLIHandler en funcionamiento)