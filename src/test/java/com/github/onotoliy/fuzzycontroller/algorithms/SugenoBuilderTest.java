package com.github.onotoliy.fuzzycontroller.algorithms;

import org.junit.Assert;
import org.junit.Test;
import com.github.onotoliy.fuzzycontroller.operators.Operator;
import com.github.onotoliy.fuzzycontroller.variables.Term;
import com.github.onotoliy.fuzzycontroller.variables.Variable;
import com.github.onotoliy.fuzzycontroller.variables.VariableBuilder;

import java.util.List;
import java.util.Map;

import static com.github.onotoliy.fuzzycontroller.algorithms.SugenoBuilder.rule;
import static com.github.onotoliy.fuzzycontroller.mf.MembershipFunctionBuilder.bell;
import static com.github.onotoliy.fuzzycontroller.operators.OperatorBuilder.and;
import static com.github.onotoliy.fuzzycontroller.utils.Utils.getOrThrow;
import static com.github.onotoliy.fuzzycontroller.utils.Utils.unmodifiableList;

/**
 * Тестирование нечеткого алгоритма логического вывода. Алгоритм Сугено.
 *
 * @author Anatoliy Pokhresnyi
 */
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
                rule(and(food.is("food_000"), service.is("service_000")), condition(0.00, food.get("food_000"), service.get("service_000"))),
                rule(and(food.is("food_000"), service.is("service_025")), condition(0.00, food.get("food_000"), service.get("service_025"))),
                rule(and(food.is("food_000"), service.is("service_050")), condition(0.00, food.get("food_000"), service.get("service_050"))),
                rule(and(food.is("food_000"), service.is("service_075")), condition(0.25, food.get("food_000"), service.get("service_075"))),
                rule(and(food.is("food_000"), service.is("service_100")), condition(0.25, food.get("food_000"), service.get("service_100"))),

                rule(and(food.is("food_050"), service.is("service_000")), condition(0.00, food.get("food_000"), service.get("service_000"))),
                rule(and(food.is("food_050"), service.is("service_025")), condition(0.25, food.get("food_000"), service.get("service_025"))),
                rule(and(food.is("food_050"), service.is("service_050")), condition(0.50, food.get("food_000"), service.get("service_050"))),
                rule(and(food.is("food_050"), service.is("service_075")), condition(0.50, food.get("food_000"), service.get("service_075"))),
                rule(and(food.is("food_050"), service.is("service_100")), condition(0.50, food.get("food_000"), service.get("service_100"))),

                rule(and(food.is("food_100"), service.is("service_000")), condition(0.25, food.get("food_000"), service.get("service_000"))),
                rule(and(food.is("food_100"), service.is("service_025")), condition(0.50, food.get("food_000"), service.get("service_025"))),
                rule(and(food.is("food_100"), service.is("service_050")), condition(1.00, food.get("food_000"), service.get("service_050"))),
                rule(and(food.is("food_100"), service.is("service_075")), condition(1.00, food.get("food_000"), service.get("service_075"))),
                rule(and(food.is("food_100"), service.is("service_100")), condition(1.00, food.get("food_000"), service.get("service_100"))));

        Sugeno sugeno = SugenoBuilder.of(rules);

        calc(0.06748503936560364, sugeno, Map.of("food", 0.00, "service", 0.25));
        calc(0.13107631320472626, sugeno, Map.of("food", 0.25, "service", 0.25));
        calc(0.24728470107845385, sugeno, Map.of("food", 0.50, "service", 0.25));
        calc(0.31297694839318674, sugeno, Map.of("food", 0.75, "service", 0.25));
        calc(0.41725066581563850, sugeno, Map.of("food", 1.00, "service", 0.25));
        calc(0.48747692799303977, sugeno, Map.of("food", 1.00, "service", 0.50));
    }

    private void calc(double expected, Sugeno sugeno, Map<String, Double> parameters) {
        Assert.assertEquals(expected, sugeno.clarity(parameters), 0.0001);
    }

    private Operator condition(double weight, Term food, Term service) {
        return new Operator() {
            @Override
            public double calc(Map<String, Double> parameters) {
                return weight * food.calc(getOrThrow(parameters, "food")) + weight * service.calc(getOrThrow(parameters, "service"));
            }

            @Override
            public List<Term> getTerms() {
                return unmodifiableList(unmodifiableList(food), unmodifiableList(service));
            }
        };
    }
}
