package com.github.onotoliy.fuzzycontroller.algorithms;

import org.junit.Assert;
import org.junit.Test;
import com.github.onotoliy.fuzzycontroller.variables.Variable;
import com.github.onotoliy.fuzzycontroller.variables.VariableBuilder;

import java.util.List;
import java.util.Map;

import static com.github.onotoliy.fuzzycontroller.algorithms.SimplifiedAlgorithmBuilder.rule;
import static com.github.onotoliy.fuzzycontroller.mf.MembershipFunctionBuilder.bell;
import static com.github.onotoliy.fuzzycontroller.operators.OperatorBuilder.and;

/**
 * Тестирование упрощенного алгоритма нечеткого вывода.
 *
 * @author Anatoliy Pokhresnyi
 */
public class SimplifiedAlgorithmBuilderTest {

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

        List<SimplifiedAlgorithm.Rule> rules = List.of(
                rule(and(food.is("food_000"), service.is("service_000")), 0.00),
                rule(and(food.is("food_000"), service.is("service_025")), 0.00),
                rule(and(food.is("food_000"), service.is("service_050")), 0.00),
                rule(and(food.is("food_000"), service.is("service_075")), 0.25),
                rule(and(food.is("food_000"), service.is("service_100")), 0.25),

                rule(and(food.is("food_050"), service.is("service_000")), 0.00),
                rule(and(food.is("food_050"), service.is("service_025")), 0.25),
                rule(and(food.is("food_050"), service.is("service_050")), 0.50),
                rule(and(food.is("food_050"), service.is("service_075")), 0.50),
                rule(and(food.is("food_050"), service.is("service_100")), 0.50),

                rule(and(food.is("food_100"), service.is("service_000")), 0.25),
                rule(and(food.is("food_100"), service.is("service_025")), 0.50),
                rule(and(food.is("food_100"), service.is("service_050")), 1.00),
                rule(and(food.is("food_100"), service.is("service_075")), 1.00),
                rule(and(food.is("food_100"), service.is("service_100")), 1.00));

        SimplifiedAlgorithm algorithm = SimplifiedAlgorithmBuilder.of(rules);

        calc(0.06681997534587318, algorithm, Map.of("food", 0.00, "service", 0.25));
        calc(0.11581439823555482, algorithm, Map.of("food", 0.25, "service", 0.25));
        calc(0.17401052408721254, algorithm, Map.of("food", 0.50, "service", 0.25));
        calc(0.21921439846820845, algorithm, Map.of("food", 0.75, "service", 0.25));
        calc(0.23616092285107007, algorithm, Map.of("food", 1.00, "service", 0.25));
    }

    private void calc(double expected, SimplifiedAlgorithm sa, Map<String, Double> parameters) {
        Assert.assertEquals(expected, sa.clarity(parameters), 0.0001);
    }
}
