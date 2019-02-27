package ru.argos.fuzzycontroller.variables;

import org.junit.Assert;
import org.junit.Test;
import ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder;

import java.util.Map;

/**
 * Тестирование терма лингвистической переменной.
 *
 * @author Anatoliy Pokhresnyi
 */
public class TermBuilderTest {

    @Test
    public void testOf() {
        Variable variable = VariableBuilder.of(
                "Variable",
                Map.of("term", MembershipFunctionBuilder.triangular(20, 40, 60)));

        Term term = variable.get("term");

        Assert.assertNotNull(term);
        Assert.assertEquals("term", term.name());
        Assert.assertEquals("term triangular[x, 20.0, 40.0, 60.0]", term.toString());
        Assert.assertEquals(variable, term.variable());
        Assert.assertEquals(0.00, term.calc(20), 0.0001);
        Assert.assertEquals(0.25, term.calc(25), 0.0001);
        Assert.assertEquals(0.50, term.calc(30), 0.0001);
        Assert.assertEquals(0.75, term.calc(35), 0.0001);
        Assert.assertEquals(1.00, term.calc(40), 0.0001);
        Assert.assertEquals(0.75, term.calc(45), 0.0001);
        Assert.assertEquals(0.50, term.calc(50), 0.0001);
        Assert.assertEquals(0.25, term.calc(55), 0.0001);
        Assert.assertEquals(0.00, term.calc(60), 0.0001);
    }
}
