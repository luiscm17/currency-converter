// package com.luiscm.currencyexchange.service;

// class CurrencyServiceTest {
    
//     @Test
//     void convertCurrency_WithNullCurrencyCode_ThrowsException() {
//         Exception ex1 = assertThrows(InvalidCurrencyException.class, () -> {
//             CurrencyService.convertCurrency(100, null, "EUR");
//         });
//         assertTrue(ex1.getMessage().contains("Formato de moneda inválido"));
        
//         Exception ex2 = assertThrows(InvalidCurrencyException.class, () -> {
//             CurrencyService.convertCurrency(100, "USD", null);
//         });
//         assertTrue(ex2.getMessage().contains("Formato de moneda inválido"));
//     }

//     @Test
//     void convertCurrency_WithMixedCaseCurrency_ThrowsException() {
//         Exception exception = assertThrows(InvalidCurrencyException.class, () -> {
//             CurrencyService.convertCurrency(100, "usd", "eur");
//         });
//         assertTrue(exception.getMessage().contains("Formato de moneda inválido"));
//     }

//     @Test
//     void convertCurrency_WithUnavailableCurrency_ThrowsException() {
//         Exception ex = assertThrows(InvalidCurrencyException.class, () -> 
//             CurrencyService.convertCurrency(100, "USD", "XYZ"));
        
//         assertAll(
//             () -> assertEquals("Moneda no soportada: XYZ", ex.getMessage()),
//             () -> assertEquals("XYZ", ((InvalidCurrencyException) ex).getInvalidCode())
//         );
//     }

//     @Test
//     void convertCurrency_WithSameCurrencies_UsesRateOne() throws Exception {
//         double result = CurrencyService.convertCurrency(100, "USD", "USD");
//         assertEquals(100.0, result, 0.001);
//     }

//     // Eliminar duplicación en test de códigos nulos usando ParameterizedTest
//     @ParameterizedTest
//     @MethodSource("nullOrEmptyCurrencyCodes")
//     void convertCurrency_WithInvalidCurrencyCodes_ThrowsException(String from, String to) {
//         Exception ex = assertThrows(InvalidCurrencyException.class, () -> 
//             CurrencyService.convertCurrency(100, from, to));
//         assertTrue(ex.getMessage().contains("Formato de moneda inválido"));
//     }
    
//     @Test
//     void convertCurrency_WithInvalidCurrencyFormat_ThrowsException() {
//         assertAll(
//             () -> assertInvalidFormat("US", "EUR"), // Código demasiado corto
//             () -> assertInvalidFormat("USD", "EURO"), // Código demasiado largo
//             () -> assertInvalidFormat("US1", "EUR"), // Caracter numérico
//             () -> assertInvalidFormat("USD", "E#R") // Símbolo especial
//         );
//     }

//     private void assertInvalidFormat(String from, String to) {
//         Exception ex = assertThrows(InvalidCurrencyException.class, () -> 
//             CurrencyService.convertCurrency(100, from, to));
//         assertEquals("El formato de moneda debe ser 3 letras mayúsculas", ex.getMessage());
//     }
    
//     private static Stream<Arguments> nullOrEmptyCurrencyCodes() {
//         return Stream.of(
//             Arguments.of(null, "EUR"),
//             Arguments.of("USD", null),
//             Arguments.of("", "EUR"),
//             Arguments.of("USD", "")
//         );
//     }
// }