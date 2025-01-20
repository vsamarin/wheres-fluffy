package ru.mos.it.platform.dit.id.backend_test.controller.examples;

public class HealthCheckExamples {

    public static final String ok200 = """
            {
              "status": "OK"
            }
            """;

    public static final String internalError500 = """
            {
              "status": "INTERNAL_ERROR"
            }
            """;
}
