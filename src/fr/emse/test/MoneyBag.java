
package fr.emse.test;

import java.util.Vector;

public class MoneyBag implements IMoney {
    private Vector<Money> fMonies = new Vector<>();

    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    public MoneyBag(Money[] bag) {
        for (Money m : bag) appendMoney(m);
    }

    private void appendMoney(Money m) {
        if (fMonies.isEmpty()) {
            fMonies.add(m);
            return;
        }
        int i = 0;
        while (i < fMonies.size() &&
               !fMonies.get(i).currency().equals(m.currency())) {
            i++;
        }
        if (i >= fMonies.size()) {
            fMonies.add(m);
        } else {
            Money old = fMonies.get(i);
            fMonies.set(i, new Money(old.amount() + m.amount(), old.currency()));
        }
    }

    public Vector<Money> getMonies() {
        return fMonies;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MoneyBag other = (MoneyBag) obj;
        return fMonies.equals(other.fMonies);
    }

    @Override
    public int hashCode() {
        return fMonies.hashCode();
    }
    
    
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    public IMoney addMoney(Money m) {
        Money[] arr = fMonies.toArray(new Money[0]);
        MoneyBag copy = new MoneyBag(arr);
        copy.appendMoney(m);
        return copy.simplify();
    }

    public IMoney addMoneyBag(MoneyBag mb) {
        MoneyBag merged = new MoneyBag(new Money[0]);
        for (Money m : fMonies) merged.appendMoney(m);
        for (Money m : mb.fMonies) merged.appendMoney(m);
        return merged.simplify();
    }

    IMoney simplify() {
        if (fMonies.size() == 1) return fMonies.get(0);
        return this;
    }
    
}