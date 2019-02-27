package com.github.onotoliy.fuzzycontroller.variables;

import org.junit.Assert;
import org.junit.Test;
import com.github.onotoliy.fuzzycontroller.mf.MembershipFunctionBuilder;
import com.github.onotoliy.fuzzycontroller.operators.Operator;

import java.util.List;
import java.util.Map;

/**
 * Тестирование лингвистической переменной.
 *
 * @author Anatoliy Pokhresnyi
 */
public class VariableBuilderTest {

    @Test
    public void testOf() {
        Variable variable = VariableBuilder.of(
                "Variable",
                Map.of("term", MembershipFunctionBuilder.triangular(20, 40, 60)));

        Assert.assertNotNull(variable);
        Assert.assertEquals("Variable", variable.name());
        Assert.assertEquals(0.0, variable.startWith(), 0.0001);
        Assert.assertEquals(1.0, variable.endWith(), 0.0001);
        Assert.assertEquals(0.0001, variable.step(), 0.0001);

        Operator operator = variable.is("term");
        Assert.assertNotNull(operator);
        Assert.assertEquals("Variable IS term", operator.toString());
        Assert.assertEquals(List.of(variable.get("term")).toString(), operator.getTerms().toString());
        Assert.assertEquals(0.00, operator.calc(Map.of("Variable", 20.0)), 0.0001);
        Assert.assertEquals(0.25, operator.calc(Map.of("Variable", 25.0)), 0.0001);
        Assert.assertEquals(0.50, operator.calc(Map.of("Variable", 30.0)), 0.0001);
        Assert.assertEquals(0.75, operator.calc(Map.of("Variable", 35.0)), 0.0001);
        Assert.assertEquals(1.00, operator.calc(Map.of("Variable", 40.0)), 0.0001);
        Assert.assertEquals(0.75, operator.calc(Map.of("Variable", 45.0)), 0.0001);
        Assert.assertEquals(0.50, operator.calc(Map.of("Variable", 50.0)), 0.0001);
        Assert.assertEquals(0.25, operator.calc(Map.of("Variable", 55.0)), 0.0001);
        Assert.assertEquals(0.00, operator.calc(Map.of("Variable", 60.0)), 0.0001);
    }

}
