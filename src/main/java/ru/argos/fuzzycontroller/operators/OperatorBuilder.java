package ru.argos.fuzzycontroller.operators;

import ru.argos.fuzzycontroller.variables.Term;

import java.util.List;
import java.util.Map;

import static ru.argos.fuzzycontroller.utils.Utils.unmodifiableList;

/**
 * Статический класс в котором реализованы логические операторы.
 *
 * @author a.k.pohresnyi
 */
public final class OperatorBuilder {

    /**
     * Приветный конструктор.
     */
    private OperatorBuilder() {

    }

    /**
     * Создает логический оператор <strong>И</strong>.
     *
     * @param o1 Логический оператор.
     * @param o2 Логический оператор.
     * @return Логический оператор <strong>И</strong>.
     */
    public static Operator and(Operator o1, Operator o2) {
        return new Operator() {
            @Override
            public double calc(Map<String, Double> parameters) {
                return Double.min(o1.calc(parameters), o2.calc(parameters));
            }

            @Override
            public List<Term> getTerms() {
                return unmodifiableList(o1.getTerms(), o2.getTerms());
            }

            @Override
            public String toString() {
                return "(" + o1.toString() + " AND " + o2.toString() + ")";
            }
        };
    }

    /**
     * Создает логический оператор <strong>Или</strong>.
     *
     * @param o1 Логический оператор.
     * @param o2 Логический оператор.
     * @return Логический оператор <strong>Или</strong>.
     */
    public static Operator or(Operator o1, Operator o2) {
        return new Operator() {
            @Override
            public double calc(Map<String, Double> parameters) {
                return Double.max(o1.calc(parameters), o2.calc(parameters));
            }

            @Override
            public List<Term> getTerms() {
                return unmodifiableList(o1.getTerms(), o2.getTerms());
            }
            @Override
            public String toString() {
                return "(" + o1.toString() + " OR " + o2.toString() + ")";
            }
        };
    }

    /**
     * Создает логический оператор <strong>Не</strong>.
     *
     * @param operator Логический оператор.
     * @return Логический оператор <strong>Не</strong>.
     */
    public static Operator not(Operator operator) {
        return new Operator() {
            @Override
            public double calc(Map<String, Double> parameters) {
                return 1.0 - operator.calc(parameters);
            }

            @Override
            public List<Term> getTerms() {
                return unmodifiableList(operator.getTerms());
            }

            @Override
            public String toString() {
                return "NOT(" + operator.toString() + ")";
            }
        };
    }
}
