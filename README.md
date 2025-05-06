# Conversor de Monedas API

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://openjdk.org/)

API de conversión de divisas que obtiene tasas actualizadas desde proveedores externos y realiza cálculos en tiempo real.

## 🚀 Comenzando

### Prerrequisitos
- Java 17+
- Maven 3.8+
- API Key de [exchangerate-api.com](https://www.exchangerate-api.com)

### Instalación
```bash
mvn clean install
mvn package
```

### Uso Básico
```java
ExchangeClient client = new ExchangeClient("TU_API_KEY");
BigDecimal amount = new BigDecimal("1000");
ConversionResult result = client.convert("USD", "EUR", amount);
System.out.println(result); // €920.00
```

## 📚 Documentación

### Endpoints Disponibles
- `/convert?from=USD&to=EUR&amount=100`
- `/rates?base=USD`
- `/currencies`

## 🤝 Contribución
1. Haz fork del proyecto
2. Crea tu branch (`git checkout -b feature/nueva-funcionalidad`)
3. Commit cambios (`git commit -m 'Agrega nueva funcionalidad'`)
4. Push al branch (`git push origin feature/nueva-funcionalidad`)
5. Abre Pull Request


---
⌨️ con ❤️ por LuisCM