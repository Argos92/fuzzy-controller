package ru.argos.fuzzycontroller.cm;

import org.junit.Assert;
import org.junit.Test;
import ru.argos.fuzzycontroller.mf.MembershipFunction;

import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.bell;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.sLine;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.trapezius;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.triangular;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.zLine;

public class ClarityMethodBuilderTest {

    private static MembershipFunction MF_1 = trapezius(0.2, 0.4, 0.6, 0.8);
    private static MembershipFunction MF_2 = trapezius(0.2, 0.6, 0.8, 1.0);
    private static MembershipFunction MF_3 = triangular(0.25, 0.5, 0.75);
    private static MembershipFunction MF_4 = bell(0.1, 0.2, 0.02);
    private static MembershipFunction MF_5 = zLine(0.0, 0.00001);
    private static MembershipFunction MF_6 = sLine(0.99999, 1.0);

    @Test
    public void testCenterOfGravity() {
        ClarityMethod cm = ClarityMethodBuilder.centerOfGravity();

        calc(0.39996000399967596, cm, MF_1);
        calc(0.6399360063994821, cm, MF_2);
        calc(0.24997500249979646, cm, MF_3);
        calc(0.33220445643338403, cm, MF_4);
        calc(0.00, cm, MF_5);
        calc(0.000199980000123708, cm, MF_6);
    }

    @Test
    public void testAverageMaximum() {
        ClarityMethod cm = ClarityMethodBuilder.averageMaximum();

        calc(0.50, cm, MF_1);
        calc(0.70, cm, MF_2);
        calc(0.50, cm, MF_3);
        calc(0.02, cm, MF_4);
        calc(0.00, cm, MF_5);
        calc(0.99999, cm, MF_6);
    }

    @Test
    public void testLeftMaximum() {
        ClarityMethod cm = ClarityMethodBuilder.leftMaximum();

        calc(0.40, cm, MF_1);
        calc(0.60, cm, MF_2);
        calc(0.50, cm, MF_3);
        calc(0.02, cm, MF_4);
        calc(0.00, cm, MF_5);
        calc(0.99999, cm, MF_6);
    }

    @Test
    public void testRightMaximum() {
        ClarityMethod cm = ClarityMethodBuilder.rightMaximum();

        calc(0.60, cm, MF_1);
        calc(0.80, cm, MF_2);
        calc(0.50, cm, MF_3);
        calc(0.02, cm, MF_4);
        calc(0.00, cm, MF_5);
        calc(1.00, cm, MF_6);
    }

    private void calc(double expected, ClarityMethod cm, MembershipFunction mf) {
        Assert.assertEquals(expected, cm.calc(mf), 0.0001);
    }
}