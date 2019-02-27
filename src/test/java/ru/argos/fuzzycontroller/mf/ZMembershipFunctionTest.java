package ru.argos.fuzzycontroller.mf;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестирование Z-образной функции принадлежности.
 *
 * @author Anatoliy Pokhresnyi
 */
public class ZMembershipFunctionTest extends MembershipFunctionTest {

    @Override
    protected Map<Double, Double> getCorrectData() {
        Map<Double, Double> map = new HashMap<>();

        map.put(0.0, 1.0);
        map.put(10.0, 1.0);
        map.put(20.0, 1.0);
        map.put(30.0, 0.98);
        map.put(40.0, 0.82);
        map.put(50.0, 0.5);
        map.put(60.0, 0.18);
        map.put(70.0, 0.02);
        map.put(80.0, 0.0);
        map.put(90.0, 0.0);
        map.put(100.0, 0.0);

        return map;
    }

    @Override
    protected MembershipFunction getCorrectMF() {
        return MembershipFunctionBuilder.z(25, 75);
    }

    @Override
    protected String getCorrectToString() {
        return "z[x, 25.0, 75.0]";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTT(){
        MembershipFunctionBuilder.z(75, 25);
    }
}
