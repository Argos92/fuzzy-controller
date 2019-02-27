package ru.argos.fuzzycontroller.algorithms;

import org.junit.Assert;
import org.junit.Test;
import ru.argos.fuzzycontroller.variables.Variable;
import ru.argos.fuzzycontroller.variables.VariableBuilder;

import java.util.List;
import java.util.Map;

import static ru.argos.fuzzycontroller.algorithms.TsukamotoBuilder.rule;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.bell;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.sLine;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.zLine;
import static ru.argos.fuzzycontroller.operators.OperatorBuilder.and;

/**
 * Тестирование нечеткого алгоритма логического вывода. Алгоритм Цукамото.
 *
 * @author Anatoliy Pokhresnyi
 */
public class TsukamotoBuilderTest {

    @Test
    public void test() {
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

        Variable tip = VariableBuilder.of(
                "tip", Map.of(
                        "tip_025", zLine(0.00, 0.25),
                        "tip_050", zLine(0.00, 0.75),
                        "tip_075", sLine(0.50, 1.00),
                        "tip_100", sLine(0.75, 1.00)));


        List<Tsukamoto.Rule> rules = List.of(
                rule(and(food.is("food_000"), service.is("service_000")), tip.get("tip_025")),
                rule(and(food.is("food_000"), service.is("service_025")), tip.get("tip_025")),
                rule(and(food.is("food_000"), service.is("service_050")), tip.get("tip_025")),
                rule(and(food.is("food_000"), service.is("service_075")), tip.get("tip_050")),
                rule(and(food.is("food_000"), service.is("service_100")), tip.get("tip_050")),

                rule(and(food.is("food_050"), service.is("service_000")), tip.get("tip_025")),
                rule(and(food.is("food_050"), service.is("service_025")), tip.get("tip_050")),
                rule(and(food.is("food_050"), service.is("service_050")), tip.get("tip_075")),
                rule(and(food.is("food_050"), service.is("service_075")), tip.get("tip_075")),
                rule(and(food.is("food_050"), service.is("service_100")), tip.get("tip_075")),

                rule(and(food.is("food_100"), service.is("service_000")), tip.get("tip_050")),
                rule(and(food.is("food_100"), service.is("service_025")), tip.get("tip_075")),
                rule(and(food.is("food_100"), service.is("service_050")), tip.get("tip_100")),
                rule(and(food.is("food_100"), service.is("service_075")), tip.get("tip_100")),
                rule(and(food.is("food_100"), service.is("service_100")), tip.get("tip_100")));

        Tsukamoto tsukamoto = TsukamotoBuilder.of(rules);

        calc(0.25096254243293210, tsukamoto, Map.of("food", 0.00, "service", 0.25));
        calc(0.38015348029377466, tsukamoto, Map.of("food", 0.25, "service", 0.25));
        calc(0.34306427110899024, tsukamoto, Map.of("food", 0.50, "service", 0.25));
        calc(0.55227598242744810, tsukamoto, Map.of("food", 0.75, "service", 0.25));
        calc(0.73778155205428950, tsukamoto, Map.of("food", 1.00, "service", 0.25));
        calc(0.76128958822143190, tsukamoto, Map.of("food", 1.00, "service", 0.50));
    }

    private void calc(double expected, Tsukamoto tsukamoto, Map<String, Double> parameters) {
        Assert.assertEquals(expected, tsukamoto.clarity(parameters), 0.0001);
    }
}
