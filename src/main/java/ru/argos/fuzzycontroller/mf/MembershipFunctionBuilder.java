package ru.argos.fuzzycontroller.mf;

/**
 * Статический класс в котором реализованы стандартные функции принадлежности.
 *
 * @author a.k.pohresnyi
 */
public final class MembershipFunctionBuilder {

    /**
     * Приватный конструктор.
     */
    private MembershipFunctionBuilder() {

    }

    /**
     * Треугольная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/triangular.png" alt="Треугольная функция принадлежности" height="100">,
     * <p>
     * где a, b, c - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt;= b &lt;= c.
     *
     * @param a Характерезует основание треуголинка.
     * @param b Характерезует вершину треуголинка.
     * @param c Характерезует основание треуголинка.
     * @return Функция принадлежности.
     */
    public static MembershipFunction triangular(final double a, final double b, final double c) {
        if (!(a <= b) || !(b <= c)) {
            throw new IllegalArgumentException();
        }

        return pieceLinear(a, b, b, c, "triangular[x, " + a + ", " + b + ", " + c + "]");
    }

    /**
     * Трацепевидная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/trapezius.png" alt="Трацепевидная функция принадлежности" height="100">,
     * <p>
     * где a, b, c, d - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt;= b &lt;= c &lt;= d.
     *
     * @param a Характерезует нижнее основание трапеции.
     * @param b Характерезует верхнее основание трапеции.
     * @param c Характерезует верхнее основание трапеции.
     * @param d Характерезует нижнее основание трапеции.
     * @return Функция принадлежности.
     */
    public static MembershipFunction trapezius(final double a, final double b, final double c, final double d) {
        if (!(a <= b) || !(b <= c) || !(c <= d)) {
            throw new IllegalArgumentException();
        }

        return pieceLinear(a, b, c, d, "trapezius[x, " + a + ", " + b + ", " + c + ", " + d + "]");
    }

    /**
     * Z-образная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/z.png" alt="Z-образная функция принадлежности" height="100">,
     * <p>
     * где a, b - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt; b.
     *
     * @param a Левая точка излома.
     * @param b Правай точка излома.
     * @return Функция принадлежности.
     */
    public static MembershipFunction z(final double a, final double b) {
        return spline(a, b, false);
    }

    /**
     * S-образная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/s.png" alt="S-образная функция принадлежности" height="100">,
     * <p>
     * где a, b - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt; b.
     *
     * @param a Левая точка излома.
     * @param b Правай точка излома.
     * @return Функция принадлежности.
     */
    public static MembershipFunction s(final double a, final double b) {
        return spline(a, b, true);
    }

    /**
     * Сигмоидальная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/sigmoid.png" alt="Сигмоидальная функция принадлежности" height="100">,
     * <p>
     * где a, b - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt; b, а e - основание натуральных логарифмов, которое инициирут задание соответсвующей экспоненциальной функции. При этом в случае a &gt; 0 может быть получена S-образная функция принадлежности, а в случае a &lt; 0 - Z-образная функция принадлежности.
     *
     * @param a Характеризует наклон графика.
     * @param b Координата точки перегиба.
     * @return Функция принадлежности.
     */
    public static MembershipFunction sigmoid(final double a, final double b) {
        return new MembershipFunction() {
            @Override
            public double calc(double x) {
                return 1.0 / (1.0 + Math.exp((0 - a) * (x - b)));
            }

            @Override
            public String toString() {
                return "sigmoid[x, " + a + ", " + b + "]";
            }
        };
    }

    /**
     * Линейная Z-образная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/z-line.png" alt="Линейная Z-образная функция принадлежности" height="100">,
     * <p>
     * где a, b - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt; b.
     *
     * @param a Левая точка излома.
     * @param b Правай точка излома.
     * @return Функция принадлежности.
     */
    public static MembershipFunction zLine(final double a, final double b) {
        return line(a, b, false);
    }

    /**
     * Линейная S-образная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/s-line.png" alt="Линейная S-образная функция принадлежности" height="100">,
     * <p>
     * где a, b - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt; b.
     *
     * @param a Левая точка излома.
     * @param b Правай точка излома.
     * @return Функция принадлежности.
     */
    public static MembershipFunction sLine(final double a, final double b) {
        return line(a, b, true);
    }

    /**
     * Колоколообразная функция принадлежности.
     * <p>
     * Функция принаждежности задана аналитически следующим выражением:
     * <p>
     * <img src="doc-files/bell.png" alt="Колоколообразная функция принадлежности" height="100">,
     * <p>
     * где a, b, c - некоторые числовые параметры, принимающие произвольные действительные значения и упорядоченые отношением a &lt; b &lt; c.
     *
     * @param a Расстояние от пика до точек перехода.
     * @param b Характеризует наклон графика, с увеличением увеличивается крутизна графика.
     * @param c Характерезует основание колокола.
     * @return Функция принадлежности.
     */
    public static MembershipFunction bell(final double a, final double b, final double c) {
        if (!(a > 0) || !(b >= 0)) {
            throw new IllegalArgumentException();
        }

        return new MembershipFunction() {
            @Override
            public double calc(double x) {
                return 1.0 / (1.0 + Math.pow(Math.abs((x - c) / a), 2.0 * b));
            }

            @Override
            public String toString() {
                return "bell[x, " + a + ", " + b + ", " + c + "]";
            }
        };
    }

    /**
     * S-образная или Z-образная функция принадлежности.
     *
     * @param a Левая точка излома.
     * @param b Правай точка излома.
     * @param flag S-образная функция принадлежности.
     * @return Функция принадлежности.
     */
    private static MembershipFunction spline(final double a, final double b, final boolean flag) {
        if (!(a < b)) {
            throw new IllegalArgumentException();
        }

        return new MembershipFunction() {

            @Override
            public double calc(double x) {
                if (x <= a) {
                    return flag ? 0.0 : 1.0;
                }

                if (x <= (a + b) / 2.0) {
                    double probability = 2.0 * Math.pow((x - a) / (b - a), 2);
                    return flag ? probability : 1.0 - probability;
                }

                if (x < b) {
                    double probability = 2.0 * Math.pow((b - x) / (b - a), 2);
                    return flag ? 1.0 - probability : probability;
                }

                return flag ? 1.0 : 0.0;
            }

            @Override
            public String toString() {
                return (flag ? "s" : "z") + "[x, " + a + ", " + b + "]";
            }
        };
    }

    /**
     * Линейная S-образная или Z-образная функция принадлежности.
     *
     * @param a Левая точка излома.
     * @param b Правай точка излома.
     * @param flag S-образная функция принадлежности.
     * @return Функция принадлежности.
     */
    private static MembershipFunction line(final double a, final double b, final boolean flag) {
        if (!(a < b)) {
            throw new IllegalArgumentException();
        }

        return new MembershipFunction() {

            @Override
            public double calc(double x) {
                if (x <= a) {
                    return flag ? 0.0 : 1.0;
                }

                if (x < b) {
                    return flag ? (x - a) / (b - a) : (b - x) / (b - a);
                }

                return flag ? 1.0 : 0.0;
            }

            @Override
            public String toString() {
                return (flag ? "s" : "z") + "-line[x, " + a + ", " + b + "]";
            }
        };
    }

    /**
     * Триугольная или трацепевидная функция принадлежности.
     *
     * @param a Характерезует нижнее основание трапеции.
     * @param b Характерезует верхнее основание трапеции.
     * @param c Характерезует верхнее основание трапеции.
     * @param d Характерезует нижнее основание трапеции.
     * @param toString Значение для метода {@see MembershipFunction#toString}.
     * @return Функция принадлежности.
     */
    private static MembershipFunction pieceLinear(final double a, final double b, final double c, final double d, final String toString) {
        return new MembershipFunction() {
            @Override
            public double calc(double x) {
                if (x <= a) {
                    return 0.0;
                }

                if (x <= b) {
                    return (x - a) / (b - a);
                }

                if (x <= c) {
                    return 1d;
                }

                if (x <= d) {
                    return (d - x) / (d - c);
                }

                return 0.0;
            }

            @Override
            public String toString() {
                return toString;
            }
        };
    }
}
