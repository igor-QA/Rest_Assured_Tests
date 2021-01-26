package tests;

import org.junit.jupiter.api.BeforeAll;
import steps.Steps;

public class TestBase {
    static Steps steps;

    @BeforeAll
    public static void init() {
        steps = new Steps();
    }
}
