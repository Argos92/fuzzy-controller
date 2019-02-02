package ru.argos.fuzzycontroller.algorithms;

import org.junit.Assert;
import org.junit.Test;
import ru.argos.fuzzycontroller.variables.Variable;
import ru.argos.fuzzycontroller.variables.VariableBuilder;

import java.util.List;
import java.util.Map;

import static ru.argos.fuzzycontroller.algorithms.SugenoBuilder.rule;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.bell;
import static ru.argos.fuzzycontroller.operators.OperatorBuilder.and;

public class SugenoBuilderTest {

    @Test
    public void testOf() {
        Variable service = VariableBuilder.of(
                "service", Map.of(
                        "service_000", bell(0.2, 1, 0.00),
                        "service_025", bell(0.2, 1, 0.25),
                        "service_050", bell(0.2, 1, 0.50),
                        "service_075", bell(0.2, 1, 0.75),
                        "service_100", bell(0.2, 1, 1.00)));

        Variable food = VariableBuilder.of(
                "food", Map.of(
                        "food_000", bell(0.2, 1, 0.00),
                        "food_050", bell(0.2, 1, 0.50),
                        "food_100", bell(0.2, 1, 1.00)));

        List<Sugeno.Rule> rules = List.of(
                rule(and(food.is("food_000"), service.is("service_000"))),
                rule(and(food.is("food_000"), service.is("service_025"))),
                rule(and(food.is("food_000"), service.is("service_050"))),
                rule(and(food.is("food_000"), service.is("service_075"))),
                rule(and(food.is("food_000"), service.is("service_100"))),

                rule(and(food.is("food_050"), service.is("service_000"))),
                rule(and(food.is("food_050"), service.is("service_025"))),
                rule(and(food.is("food_050"), service.is("service_050"))),
                rule(and(food.is("food_050"), service.is("service_075"))),
                rule(and(food.is("food_050"), service.is("service_100"))),

                rule(and(food.is("food_100"), service.is("service_000"))),
                rule(and(food.is("food_100"), service.is("service_025"))),
                rule(and(food.is("food_100"), service.is("service_050"))),
                rule(and(food.is("food_100"), service.is("service_075"))),
                rule(and(food.is("food_100"), service.is("service_100"))));

        Sugeno sugeno = SugenoBuilder.of(rules);

        calc(0.28102943422068990, sugeno, Map.of("food", 0.00, "service", 0.25));
        calc(0.26141761000146560, sugeno, Map.of("food", 0.25, "service", 0.25));
        calc(0.33007418005687045, sugeno, Map.of("food", 0.50, "service", 0.25));
        calc(0.26016409402029280, sugeno, Map.of("food", 0.75, "service", 0.25));
        calc(0.33861762483674573, sugeno, Map.of("food", 1.00, "service", 0.25));
    }

    private void calc(double expected, Sugeno sugeno, Map<String, Double> parameters) {
        Assert.assertEquals(expected, sugeno.clarity(parameters), 0.0001);
    }
}
