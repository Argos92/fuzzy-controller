package ru.argos.fuzzycontroller.variables;

import ru.argos.fuzzycontroller.mf.MembershipFunction;
import ru.argos.fuzzycontroller.operators.Operator;

import java.util.List;
import java.util.Map;

import static ru.argos.fuzzycontroller.utils.Utils.getOrThrow;
import static ru.argos.fuzzycontroller.utils.Utils.unmodifiableList;

/**
 * Статический класс в котором реализовано создание лингвистической переменной.
 *
 * @author a.k.pohresnyi
 */
public final class VariableBuilder {

    /**
     * Приветный конструктор.
     */
    private VariableBuilder() {

    }

    /**
     * Создает лингвистическую переменную
     *
     * @param name Название лингвистической переменной
     * @param terms Список термов, где {@link Map.Entry#getKey()} - название терма, а {@link Map.Entry#getValue()} - функция
     *              принадлежности.
     * @return Лингвистическая переменная.
     */
    public static Variable of(String name, Map<String, MembershipFunction> terms) {
        return of(name, 0.0, 1.0, 0.0001, terms);
    }

    /**
     * Создает лингвистическую переменную
     *
     * @param name Название лингвистической переменной
     * @param startWith Начала интервала.
     * @param endWith Начала интервала.
     * @param step Шаг.
     * @param terms Список термов, где {@link Map.Entry#getKey()} - название терма, а {@link Map.Entry#getValue()} - функция
     *              принадлежности.
     * @return Лингвистическая переменная.
     */
    public static Variable of(String name, double startWith, double endWith, double step, Map<String, MembershipFunction> terms) {
        return new Variable() {
            @Override
            public Operator is(String term) {
                return new Operator() {
                    @Override
                    public double calc(Map<String, Double> map) {
                        return getOrThrow(terms, term).calc(getOrThrow(map, name));
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
            public Term get(String term) {
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
