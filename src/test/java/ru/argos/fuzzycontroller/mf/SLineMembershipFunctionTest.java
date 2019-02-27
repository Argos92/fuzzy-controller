package ru.argos.fuzzycontroller.mf;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестирование линейной S-образной функции принадлежности.
 *
 * @author Anatoliy Pokhresnyi
 */
public class SLineMembershipFunctionTest extends MembershipFunctionTest {

    @Override
    protected Map<Double, Double> getCorrectData() {
        Map<Double, Double> map = new HashMap<>();

        map.put(0.0, 0.0);
        map.put(10.0, 0.0);
        map.put(20.0, 0.0);
        map.put(30.0, 0.1);
        map.put(40.0, 0.3);
        map.put(50.0, 0.5);
        map.put(60.0, 0.7);
        map.put(70.0, 0.9);
        map.put(80.0, 1.0);
        map.put(90.0, 1.0);
        map.put(100.0, 1.0);

        return map;
    }

    @Override
    protected MembershipFunction getCorrectMF() {
        return MembershipFunctionBuilder.sLine(25, 75);
    }

    @Override
    protected String getCorrectToString() {
        return "s-line[x, 25.0, 75.0]";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTT(){
        MembershipFunctionBuilder.sLine(75, 25);
    }
}
