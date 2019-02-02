package ru.argos.fuzzycontroller.mf;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестовый класс, тестирующий триугольную функцую принадлежности.
 *
 * @author a.k.pohresnyi
 */
public class TriangularMembershipFunctionTest extends MembershipFunctionTest {

    @Override
    protected Map<Double, Double> getCorrectData() {
        Map<Double, Double> map = new HashMap<>();

        map.put(0.0, 0.0);
        map.put(10.0, 0.0);
        map.put(20.0, 0.0);
        map.put(30.0, 0.2);
        map.put(40.0, 0.6);
        map.put(50.0, 1.0);
        map.put(60.0, 0.6);
        map.put(70.0, 0.2);
        map.put(80.0, 0.0);
        map.put(90.0, 0.0);
        map.put(100.0, 0.0);

        return map;
    }

    @Override
    protected MembershipFunction getCorrectMF() {
        return MembershipFunctionBuilder.triangular(25, 50, 75);
    }

    @Override
    protected String getCorrectToString() {
        return "triangular[x, 25.0, 50.0, 75.0]";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTT(){
        MembershipFunctionBuilder.triangular(75, 50, 25);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersFT(){
        MembershipFunctionBuilder.triangular(75, 50, 75);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTF(){
        MembershipFunctionBuilder.triangular(50, 75, 25);
    }
}
