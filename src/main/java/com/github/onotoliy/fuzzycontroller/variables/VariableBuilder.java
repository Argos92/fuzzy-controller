package com.github.onotoliy.fuzzycontroller.variables;

import com.github.onotoliy.fuzzycontroller.mf.MembershipFunction;
import com.github.onotoliy.fuzzycontroller.operators.Operator;

import java.util.List;
import java.util.Map;

import static com.github.onotoliy.fuzzycontroller.utils.Utils.getOrThrow;
import static com.github.onotoliy.fuzzycontroller.utils.Utils.unmodifiableList;

/**
 * Статический класс, в котором реализовано создание лингвистической переменной.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class VariableBuilder {

    /**
     * Начало интервала.
     */
    private static final double START_WITH = 0.0;

    /**
     * Конец интервала.
     */
    private static final double END_WITH = 1.0;

    /**
     * Шаг.
     */
    private static final double STEP = 0.0001;

    /**
     * Приватный конструктор.
     */
    private VariableBuilder() {

    }

    /**
     * Лингвистическая переменная.
     *
     * @param name Название лингвистической переменной
     * @param terms Список термов, где {@link Map.Entry#getKey()} - название
     *             терма, а {@link Map.Entry#getValue()} - функция
     *             принадлежности.
     * @return Лингвистическая переменная.
     */
    public static Variable of(final String name,
                              final Map<String, MembershipFunction> terms) {
        return of(name, START_WITH, END_WITH, STEP, terms);
    }

    /**
     * Лингвистическая переменная.
     *
     * @param name Название лингвистической переменной
     * @param startWith Начало интервала.
     * @param endWith Конец интервала.
     * @param step Шаг.
     * @param terms Список термов, где {@link Map.Entry#getKey()} - название
     *              терма, а {@link Map.Entry#getValue()} - функция
     *              принадлежности.
     * @return Лингвистическая переменная.
     */
    public static Variable of(final String name,
                              final double startWith,
                              final double endWith,
                              final double step,
                              final Map<String, MembershipFunction> terms) {
        return new Variable() {
            @Override
            public Operator is(final String term) {
                return new Operator() {
                    @Override
                    public double calc(final Map<String, Double> map) {
                        return getOrThrow(terms, term)
                                .calc(getOrThrow(map, name));
                    }

                    @Override
                    public List<Term> getTerms() {
                        return unmodifiableList(get(term));
                    }

                    @Override
                    public String toString() {
                        return name + " IS " + term;
                    }
                };
            }

            @Override
            public Term get(final String term) {
                return TermBuilder.of(this, term, getOrThrow(terms, term));
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public double startWith() {
                return startWith;
            }

            @Override
            public double endWith() {
                return endWith;
            }

            @Override
            public double step() {
                return step;
            }
        };
    }
}
