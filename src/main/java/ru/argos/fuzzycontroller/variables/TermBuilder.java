package ru.argos.fuzzycontroller.variables;

import ru.argos.fuzzycontroller.mf.MembershipFunction;

/**
 * Статический класс в котором реализовано создание терма лингвистической
 * переменной.
 *
 * @author a.k.pohresnyi
 */
public final class TermBuilder {

    /**
     * Приветный конструктор.
     */
    private TermBuilder() {

    }

    /**
     * Создает терм лингвистической переменной.
     *
     * @param variable Лингвистическая переменная.
     * @param name Название терма.
     * @param mf Функция принадлежности терма.
     * @return Терм лингвистичской переменной.
     */
    public static Term of(final Variable variable,
                          final String name,
                          final MembershipFunction mf) {
        return new Term() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public double calc(final double x) {
                return mf.calc(x);
            }

            @Override
            public Variable variable() {
                return variable;
            }

            @Override
            public String toString() {
                return name + " " + mf.toString();
            }
        };
    }
}
