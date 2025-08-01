
package fr.emse.test;

public interface IMoney {
    IMoney add(IMoney aMoney);          // appel général
    IMoney addMoney(Money m);           // quand l’argument est un Money
    IMoney addMoneyBag(MoneyBag mb);    // quand l’argument est un MoneyBag
}