package ru.argos.fuzzycontroller.mf;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестовый класс, тестирующий трапецивидную функцую принадлежности.
 *
 * @author a.k.pohresnyi
 */
public class TrapeziusMembershipFunctionTest extends MembershipFunctionTest {

    @Override
    protected Map<Double, Double> getCorrectData() {
        Map<Double, Double> map = new HashMap<>();

        map.put(0.0, 0.0);
        map.put(10.0, 0.0);
        map.put(20.0, 0.0);
        map.put(25.0, 0.25);
        map.put(30.0, 0.5);
        map.put(35.0, 0.75);
        map.put(40.0, 1.0);
        map.put(50.0, 1.0);
        map.put(60.0, 1.0);
        map.put(65.0, 0.75);
        map.put(70.0, 0.5);
        map.put(75.0, 0.25);
        map.put(80.0, 0.0);
        map.put(90.0, 0.0);
        map.put(100.0, 0.0);

        return map;
    }

    @Override
    protected MembershipFunction getCorrectMF() {
        return MembershipFunctionBuilder.trapezius(20, 40, 60, 80);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTTT(){
        MembershipFunctionBuilder.trapezius(80, 60 , 40, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTTF(){
        MembershipFunctionBuilder.trapezius(60, 40 , 20, 80);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTFT(){
        MembershipFunctionBuilder.trapezius(60, 40 , 80, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTFF(){
        MembershipFunctionBuilder.trapezius(40, 20 , 60, 80);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersFTT(){
        MembershipFunctionBuilder.trapezius(60, 80 , 40, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersFTF(){
        MembershipFunctionBuilder.trapezius(20, 80 , 40, 60);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersFFT(){
        MembershipFunctionBuilder.trapezius(40, 60 , 80, 20);
    }
}
