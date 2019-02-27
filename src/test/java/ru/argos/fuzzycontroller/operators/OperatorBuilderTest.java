package ru.argos.fuzzycontroller.operators;

import org.junit.Assert;
import org.junit.Test;
import ru.argos.fuzzycontroller.variables.Term;
import ru.argos.fuzzycontroller.variables.Variable;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.bell;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.sigmoid;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.trapezius;
import static ru.argos.fuzzycontroller.mf.MembershipFunctionBuilder.triangular;
import static ru.argos.fuzzycontroller.operators.OperatorBuilder.and;
import static ru.argos.fuzzycontroller.operators.OperatorBuilder.not;
import static ru.argos.fuzzycontroller.operators.OperatorBuilder.or;
import static ru.argos.fuzzycontroller.utils.Utils.unmodifiableList;
import static ru.argos.fuzzycontroller.variables.VariableBuilder.of;

/**
 * Тестирование логических операторов.
 *
 * @author Anatoliy Pokhresnyi
 */
public class OperatorBuilderTest {

    /**
     * Лингвистическая переменная.
     */
    private static final Variable A = of("A", Map.of("a", trapezius(10, 20, 30, 40)));

    /**
     * Лингвистическая переменная.
     */
    private static final Variable B = of("B", Map.of("b", triangular(10, 20, 30)));

    /**
     * Лингвистическая переменная.
     */
    private static final Variable D = of("D", Map.of("d", sigmoid(10, 20)));

    /**
     * Лингвистическая переменная.
     */
    private static final Variable C = of("C", Map.of("c", bell(10, 20, 30)));

    @Test
    public void testGetTerms() {
        BiConsumer<List<Term>, Operator> assertEquals = (o1, o2) -> Assert.assertEquals(
                unmodifiableList(o1).toString(),
                unmodifiableList(o2.getTerms()).toString());

        assertEquals.accept(List.of(B.get("b")), not(B.is("b")));
        assertEquals.accept(List.of(B.get("b")), not(not(B.is("b"))));
        assertEquals.accept(
                List.of(A.get("a"), B.get("b")),
                and(A.is("a"), B.is("b")));
        assertEquals.accept(
                List.of(A.get("a"), B.get("b")),
                not(and(A.is("a"), B.is("b"))));
        assertEquals.accept(
                List.of(A.get("a"), B.get("b"), C.get("c")),
                not(and(A.is("a"), or(B.is("b"), C.is("c")))));
        assertEquals.accept(
                List.of(A.get("a"), B.get("b"), D.get("d"), C.get("c")),
                or(A.is("a"), or(B.is("b"), and(D.is("d"), C.is("c")))));
        assertEquals.accept(
                List.of(A.get("a"), B.get("b"), D.get("d"), C.get("c")),
                or(A.is("a"), or(B.is("b"), and(D.is("d"), not(C.is("c"))))));
    }

    @Test
    public void testToString() {
        Assert.assertEquals(
                "NOT(B IS b)",
                not(B.is("b")).toString());
        Assert.assertEquals(
                "NOT(NOT(B IS b))",
                not(not(B.is("b"))).toString());
        Assert.assertEquals(
                "(A IS a AND B IS b)",
                and(A.is("a"), B.is("b")).toString());
        Assert.assertEquals(
                "NOT((A IS a AND B IS b))",
                not(and(A.is("a"), B.is("b"))).toString());
        Assert.assertEquals(
                "NOT((A IS a AND (B IS b OR C IS c)))",
                not(and(A.is("a"), or(B.is("b"), C.is("c")))).toString());
        Assert.assertEquals(
                "(A IS a OR (B IS b OR (D IS d AND C IS c)))",
                or(A.is("a"), or(B.is("b"), and(D.is("d"), C.is("c")))).toString());
        Assert.assertEquals(
                "(A IS a OR (B IS b OR (D IS d AND NOT(C IS c))))",
                or(A.is("a"), or(B.is("b"), and(D.is("d"), not(C.is("c"))))).toString());
    }

    @Test
    public void testCalc() {
        Assert.assertEquals(
                0.5,
                not(B.is("b")).calc(Map.of("B", 15.0)),
                0.00001);
        Assert.assertEquals(
                0.5,
                not(not(B.is("b"))).calc(Map.of("B", 15.0)),
                0.00001);
        Assert.assertEquals(
                0.0,
                and(A.is("a"), B.is("b")).calc(Map.of("B", 15.0, "A", 10.0)),
                0.00001);
        Assert.assertEquals(
                1.0,
                not(and(A.is("a"), B.is("b"))).calc(Map.of("B", 15.0, "A", 10.0)),
                0.00001);
        Assert.assertEquals(
                1.0,
                not(and(A.is("a"), or(B.is("b"), C.is("c")))).calc(Map.of("B", 15.0, "A", 10.0, "C", 20.5)),
                0.00001);
        Assert.assertEquals(
                0.5,
                or(A.is("a"), or(B.is("b"), and(D.is("d"), C.is("c")))).calc(Map.of("B", 15.0, "A", 10.0, "C", 10.0, "D", 10.0)),
                0.00001);
        Assert.assertEquals(
                0.5,
                or(A.is("a"), or(B.is("b"), and(D.is("d"), not(C.is("c"))))).calc(Map.of("B", 15.0, "A", 10.0, "C", 10.0, "D", 10.0)),
                0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotSetParameter() {
        not(and(A.is("a"), B.is("b"))).calc(Map.of("B", 15.0, "C", 10.0));
    }
}
