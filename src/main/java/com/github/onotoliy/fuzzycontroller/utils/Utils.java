package com.github.onotoliy.fuzzycontroller.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Вспомогательный класс.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class Utils {

    /**
     * Приватный конструктор.
     */
    private Utils() {

    }

    /**
     * Получает значение по ключу из {@link Map}.
     *
     * @param map {@link Map}, из которого необходимо получить значение.
     * @param key Ключ.
     * @param <K> Тип ключа.
     * @param <V> Тип Значения.
     * @return Значение из {@link Map}.
     * @throws IllegalArgumentException Если значения по заданному ключу нет в
     *                                 {@link Map}.
     */
    public static <K, V> V getOrThrow(final Map<K, V> map, final K key) {
        V value = map.get(key);

        if (value == null) {
            throw new IllegalArgumentException();
        }

        return value;
    }

    /**
     * Объединяет два списка и создает из него неизменяемый.
     *
     * @param v1 Список.
     * @param v2 Список.
     * @param <V> Тип.
     * @return Неизменяемый список.
     */
    public static <V> List<V> unmodifiableList(final List<V> v1,
                                               final List<V> v2) {
        return unmodifiableList(
                new ArrayList<>(v1.size() + v1.size()) {{
                    addAll(v1);
                    addAll(v2);
                }});
    }

    /**
     * Создает неизменяемый список.
     *
     * @param v Список.
     * @param <V> Тип.
     * @return Неизменяемый список.
     */
    public static <V> List<V> unmodifiableList(final List<V> v) {
        return Collections.unmodifiableList(v);
    }

    /**
     * Создает неизменяемый список из одного элемента.
     *
     * @param v Элемент.
     * @param <V> Тип.
     * @return Неизменяемый список.
     */
    public static <V> List<V> unmodifiableList(final V v) {
        return unmodifiableList(Collections.singletonList(v));
    }
}
