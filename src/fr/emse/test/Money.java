
package fr.emse.test;

public class Money implements IMoney {
    private int fAmount;
    private String fCurrency;

    public Money(int amount, String currency) {
        fAmount = amount;
        fCurrency = currency;
    }

    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    /*  On n’expose plus add(Money) ; on passe par l’interface IMoney
        Si tu veux le garder en interne, rends-le private ou supprime-le.
    */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return fAmount == money.fAmount &&
               fCurrency.equals(money.fCurrency);
    }

    @Override
    public int hashCode() {
        return 31 * fAmount + fCurrency.hashCode();
    }

    /* ========== Implémentation de IMoney ========== */

    @Override
    public IMoney add(IMoney m) {
        if (m instanceof Money) {
            Money other = (Money) m;
            if (other.currency().equals(this.currency())) {
                return new Money(this.amount() + other.amount(), this.currency());
            }
        }
        return m.addMoney(this);   // double-dispatch
    }

    /* Méthodes de double-dispatch : doivent être publiques pour être appelées via l’interface */
    @Override
    public IMoney addMoney(Money m) {
        return new MoneyBag(m, this);
    }

    @Override
    public IMoney addMoneyBag(MoneyBag mb) {
        return mb.add(this);
    }
}