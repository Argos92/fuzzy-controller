package com.github.onotoliy.fuzzycontroller.operators;

import com.github.onotoliy.fuzzycontroller.variables.Term;

import java.util.List;
import java.util.Map;

import static com.github.onotoliy.fuzzycontroller.utils.Utils.unmodifiableList;

/**
 * Статический класс, в котором реализованы логические операторы.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class OperatorBuilder {

    /**
     * Приватный конструктор.
     */
    private OperatorBuilder() {

    }

    /**
     * Логический оператор <strong>И</strong>.
     *
     * @param o1 Логический оператор.
     * @param o2 Логический оператор.
     * @return Логический оператор <strong>И</strong>.
     */
    public static Operator and(final Operator o1, final Operator o2) {
        return new Operator() {
            @Override
            public double calc(final Map<String, Double> parameters) {
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
     * Логический оператор <strong>Или</strong>.
     *
     * @param o1 Логический оператор.
     * @param o2 Логический оператор.
     * @return Логический оператор <strong>Или</strong>.
     */
    public static Operator or(final Operator o1, final Operator o2) {
        return new Operator() {
            @Override
            public double calc(final Map<String, Double> parameters) {
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
     * Логический оператор <strong>Не</strong>.
     *
     * @param operator Логический оператор.
     * @return Логический оператор <strong>Не</strong>.
     */
    public static Operator not(final Operator operator) {
        return new Operator() {
            @Override
            public double calc(final Map<String, Double> parameters) {
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
