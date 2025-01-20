package ru.mos.it.platform.dit.id.backend_test.controller.examples;

public class PetExamples {

    public static final String list200 = """
            {
              "totalElements": 2,
              "totalPages": 1,
              "size": 10,
              "numberOfElements": 2,
              "first": true,
              "content": [
                {
                  "id": 1,
                  "name": "Майло",
                  "species": "Собака, Джек-Рассел-Терьер",
                  "description": "Последний раз видели с человеком в желтом костюме и широкополой шляпе в странной зеленой маске.",
                  "status": "MISSING"
                },
                {
                  "id": 2,
                  "name": "Бетховен",
                  "species": "Собака породы Сенбернар",
                  "description": "Последний раз был замечен рядом с местной Филармонией",
                  "status": "MISSING"
                }
              ]
            }
            """;

    public static final String findById200 = """
            {
              "name": "Императрица",
              "species": "Кошка, Шотландская вислоухая",
              "description": "Кошка была потеряна в городе Москва, в районе Северное Тушино, ул. Свободы 67к3, во дворе, отличительные черты: красный ошейник с позолоченным кулоном",
              "status": "MISSING"
            }
            """;

    public static final String findById404 = """
            {
              "errors": [
                {
                  "errorMessage": "Питомец с идентификатором 2 не найден."
                }
              ]
            }
            """;

    public static final String list500 = """
            {
               "errors": [
                 {
                   "errorMessage": "Could not open JPA EntityManager for transaction"
                 }
               ]
             }
            """;

    public static final String create200 = """
            {
              "name": "Императрица",
              "species": "Кошка, Шотландская вислоухая",
              "description": "Кошка была потеряна в городе Москва, в районе Северное Тушино, ул. Свободы 67к3, во дворе, отличительные черты: красный ошейник с позолоченным кулоном",
              "status": "MISSING"
            }
            """;

    public static final String createOrEdit400 = """
            {
              "errors": [
                {
                  "errorMessage": "petDto: \\"name\\" must be unique"
                }
              ]
            }
            """;

}
