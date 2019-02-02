package ru.argos.fuzzycontroller.mf;

import java.util.HashMap;
import java.util.Map;

/**
 * Тестовый класс, тестирующий сигмоидальную функцию принадлежности.
 *
 * @author a.k.pohresnyi
 */
public class SigmoidMembershipFunctionTest extends MembershipFunctionTest {

    @Override
    protected Map<Double, Double> getCorrectData() {
        Map<Double, Double> map = new HashMap<>();

        map.put(0.0, 0.0066928509242849);
        map.put(10.0, 0.0179862099620916);
        map.put(20.0, 0.0474258731775668);
        map.put(30.0, 0.1192029220221180);
        map.put(40.0, 0.2689414213699950);
        map.put(50.0, 0.5000000000000000);
        map.put(60.0, 0.7310585786300050);
        map.put(70.0, 0.8807970779778820);
        map.put(71.0, 0.8909031788043870);
        map.put(72.0, 0.9002495108803150);
        map.put(73.0, 0.9088770389851440);
        map.put(74.0, 0.9168273035060780);
        map.put(75.0, 0.9241418199787570);
        map.put(80.0, 0.9525741268224330);
        map.put(90.0, 0.9820137900379080);
        map.put(100.0, 0.9933071490757150);

        return map;
    }

    @Override
    protected MembershipFunction getCorrectMF() {
        return MembershipFunctionBuilder.sigmoid(0.1, 50);
    }

    @Override
    protected String getCorrectToString() {
        return "sigmoid[x, 0.1, 50.0]";
    }
}
