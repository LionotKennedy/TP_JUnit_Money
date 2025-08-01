
package fr.emse.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import org.junit.Before;

public class MoneyTest {
    private Money m12CHF;
    private Money m14CHF;

    @Before
    public void setUp() {
        m12CHF = new Money(12, "CHF");
        m14CHF = new Money(14, "CHF");
    }

    @Test
    public void testSimpleAdd() {
        assertEquals(new Money(26, "CHF"), m12CHF.add(m14CHF));
    }

    @Test
    public void testEquals() {
        assertTrue(!m12CHF.equals(null));
        assertEquals(m12CHF, m12CHF);
        assertEquals(m12CHF, new Money(12, "CHF"));
        assertTrue(!m12CHF.equals(m14CHF));
    }
    
    @Test
    public void testMixedSimpleAdd() {
        Money bag[] = { new Money(12, "CHF"), new Money(7, "USD") };
        MoneyBag expected = new MoneyBag(bag);
        assertEquals(expected, new Money(12, "CHF").add(new Money(7, "USD")));
    }

    @Test
    public void testBagSimpleAdd() {
        MoneyBag bag = new MoneyBag(new Money(12, "CHF"), new Money(7, "USD"));
        IMoney result = bag.add(new Money(14, "CHF"));
        MoneyBag expected = new MoneyBag(
                new Money[]{ new Money(26, "CHF"), new Money(7, "USD") });
        assertEquals(expected, result);
    }

    @Test
    public void testSimpleBagAdd() {
        Money m = new Money(12, "CHF");
        MoneyBag bag = new MoneyBag(new Money(14, "CHF"), new Money(7, "USD"));
        IMoney result = m.add(bag);
        MoneyBag expected = new MoneyBag(
                new Money[]{ new Money(26, "CHF"), new Money(7, "USD") });
        assertEquals(expected, result);
    }

    @Test
    public void testBagBagAdd() {
        MoneyBag b1 = new MoneyBag(new Money(12, "CHF"), new Money(7, "USD"));
        MoneyBag b2 = new MoneyBag(new Money(14, "CHF"), new Money(21, "USD"));
        IMoney result = b1.add(b2);
        MoneyBag expected = new MoneyBag(
                new Money[]{ new Money(26, "CHF"), new Money(28, "USD") });
        assertEquals(expected, result);
    }
    
    @Test
    public void testSimplification() {
        Money result = (Money) new MoneyBag(
                new Money[]{ new Money(12, "CHF"), new Money(-12, "CHF"), new Money(7, "USD") }
        ).simplify();
        assertEquals(new Money(7, "USD"), result);
    }
}


