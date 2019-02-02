package ru.argos.fuzzycontroller.mf;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.bell;

/**
 * Тестовый класс, тестирующий колокообразную функцию принадлежности.
 *
 * @author a.k.pohresnyi
 */
public class BellMembershipFunctionTest extends MembershipFunctionTest {

    @Override
    protected Map<Double, Double> getCorrectData() {
        Map<Double, Double> map = new HashMap<>();

        map.put(0.0, 0.00000000028679720);
        map.put(10.0, 0.00000001099511616);
        map.put(20.0, 0.00000095367340691);
        map.put(30.0, 0.00030063824928392);
        map.put(35.0, 0.01139780727494200);
        map.put(36.0, 0.02542097133327720);
        map.put(37.0, 0.05758200252435920);
        map.put(38.0, 0.12940795943806800);
        map.put(39.0, 0.27372529717242600);
        map.put(40.0, 0.50000000000000000);
        map.put(41.0, 0.73611362731462100);
        map.put(42.0, 0.89160201035483300);
        map.put(43.0, 0.96268671436973700);
        map.put(44.0, 0.98860219272505800);
        map.put(45.0, 0.99683881285548000);
        map.put(46.0, 0.99920271350998300);
        map.put(47.0, 0.99981878738560000);
        map.put(48.0, 0.99996343975230000);
        map.put(49.0, 0.99999358419724700);
        map.put(50.0, 0.99999904632659300);
        map.put(60.0, 1.00000000000000000);
        map.put(70.0, 0.99999904632659300);
        map.put(75.0, 0.99683881285548000);
        map.put(80.0, 0.50000000000000000);
        map.put(85.0, 0.01139780727494200);
        map.put(90.0, 0.00030063824928392);
        map.put(100.0, 0.00000095367340691);

        return map;
    }

    @Override
    protected MembershipFunction getCorrectMF() {
        return bell(20.0, 10.0, 60.0);
    }

    @Override
    protected String getCorrectToString() {
        return "bell[x, 20.0, 10.0, 60.0]";
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTT(){
        bell(-20.0, -10.0, 60.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersFT(){
        bell(-20.0, 10.0, 60.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongParametersTF(){
        bell(20.0, -10.0, 60.0);
    }
}
