package ru.argos.fuzzycontroller.cm;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ClarityMethodBuilderTest {

    private static final List<ClarityVariable> VARIABLES_1 = List.of(
            new ClarityVariable(0.99, 0.67), new ClarityVariable(0.33, 0.32),
            new ClarityVariable(0.79, 0.66), new ClarityVariable(0.74, 0.75),
            new ClarityVariable(0.30, 0.35), new ClarityVariable(0.46, 0.46),
            new ClarityVariable(0.30, 0.68), new ClarityVariable(0.39, 0.33),
            new ClarityVariable(0.66, 0.45), new ClarityVariable(0.35, 0.32),
            new ClarityVariable(0.76, 0.68), new ClarityVariable(0.08, 0.81),
            new ClarityVariable(0.99, 0.36), new ClarityVariable(0.99, 0.23),
            new ClarityVariable(0.95, 0.51), new ClarityVariable(0.81, 0.07),
            new ClarityVariable(0.17, 0.26), new ClarityVariable(0.84, 0.70),
            new ClarityVariable(0.66, 0.41), new ClarityVariable(0.20, 0.19),
            new ClarityVariable(0.45, 0.03), new ClarityVariable(0.22, 0.01),
            new ClarityVariable(0.57, 0.74), new ClarityVariable(0.30, 0.34),
            new ClarityVariable(0.64, 0.87), new ClarityVariable(0.09, 0.21),
            new ClarityVariable(0.61, 0.92), new ClarityVariable(0.72, 0.48),
            new ClarityVariable(0.02, 0.15), new ClarityVariable(0.34, 0.39));

    private static final List<ClarityVariable> VARIABLES_2 = List.of(
            new ClarityVariable(0.79, 0.43), new ClarityVariable(0.01, 0.46),
            new ClarityVariable(0.19, 0.73), new ClarityVariable(0.53, 0.24),
            new ClarityVariable(0.67, 0.64), new ClarityVariable(0.79, 0.17),
            new ClarityVariable(0.88, 0.30), new ClarityVariable(0.58, 0.27),
            new ClarityVariable(1.00, 0.48), new ClarityVariable(0.61, 0.51),
            new ClarityVariable(0.21, 0.97), new ClarityVariable(0.18, 0.51),
            new ClarityVariable(0.68, 0.35), new ClarityVariable(0.32, 0.82),
            new ClarityVariable(0.74, 0.67), new ClarityVariable(0.58, 0.79));

    private static final List<ClarityVariable> VARIABLES_3 = List.of(
            new ClarityVariable(0.36, 0.42), new ClarityVariable(0.89, 0.65),
            new ClarityVariable(0.21, 0.97), new ClarityVariable(0.52, 0.43),
            new ClarityVariable(0.89, 0.30), new ClarityVariable(0.87, 0.98),
            new ClarityVariable(0.53, 0.57), new ClarityVariable(0.18, 0.50),
            new ClarityVariable(0.89, 0.05), new ClarityVariable(0.67, 0.18));

    private static final List<ClarityVariable> VARIABLES_4 = List.of(
            new ClarityVariable(0.00, 0.42), new ClarityVariable(0.89, 0.00));

    private static final List<ClarityVariable> VARIABLES_5 = List.of(new ClarityVariable(0.00, 0.00));

    @Test
    public void testCenterOfGravity() {
        ClarityMethod cm = ClarityMethodBuilder.centerOfGravity();

        calc(0.4903880407, cm, VARIABLES_1);
        calc(0.4720205479, cm, VARIABLES_2);
        calc(0.4715141431, cm, VARIABLES_3);
        calc(0.00, cm, VARIABLES_4);
        calc(Double.NaN, cm, VARIABLES_5);
    }

    @Test
    public void testAverageMaximum() {
        ClarityMethod cm = ClarityMethodBuilder.averageMaximum();

        calc(0.42, cm, VARIABLES_1);
        calc(0.48, cm, VARIABLES_2);
        calc(0.3333333333, cm, VARIABLES_3);
        calc(0.00, cm, VARIABLES_4);
        calc(0.00, cm, VARIABLES_5);
    }

    @Test
    public void testLeftMaximum() {
        ClarityMethod cm = ClarityMethodBuilder.leftMaximum();

        calc(0.23, cm, VARIABLES_1);
        calc(0.48, cm, VARIABLES_2);
        calc(0.05, cm, VARIABLES_3);
        calc(0.00, cm, VARIABLES_4);
        calc(0.00, cm, VARIABLES_5);
    }

    @Test
    public void testRightMaximum() {
        ClarityMethod cm = ClarityMethodBuilder.rightMaximum();

        calc(0.67, cm, VARIABLES_1);
        calc(0.48, cm, VARIABLES_2);
        calc(0.65, cm, VARIABLES_3);
        calc(0.00, cm, VARIABLES_4);
        calc(0.00, cm, VARIABLES_5);
    }

    private void calc(double expected, ClarityMethod cm, List<ClarityVariable> variables) {
        Assert.assertEquals(expected, cm.calc(variables), 0.00001);
    }


}