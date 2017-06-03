package ranoraraku.beans.options;

import oahu.financial.Derivative;
import oahu.financial.DerivativePrice;
import oahu.financial.OptionCalculator;
import oahu.financial.StockPrice;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * Created by rcs on 26.09.14.
 *
 */

public class DerivativePriceBean implements DerivativePrice {
    private Derivative derivative;
    private StockPrice stockPrice;
    private double buy;
    private double sell;
    private int oid;
    protected OptionCalculator calculator;

    public DerivativePriceBean() {
    }

    public DerivativePriceBean(StockPrice stockPrice,
                               Derivative derivative,
                               double buy,
                               double sell,
                               OptionCalculator calculator) {
        this.stockPrice = stockPrice;
        this.derivative = derivative;
        this.buy = buy;
        this.sell = sell;
        this.calculator = calculator;
    }

    public DerivativePriceBean(Derivative derivative,
                               double buy,
                               double sell,
                               OptionCalculator calculator) {
        this.derivative = derivative;
        this.buy = buy;
        this.sell = sell;
        this.calculator = calculator;
    }
    @Override
    public double getBuy() {
        return buy;
    }

    @Override
    public double getSell() {
        return sell;
    }


    private Double _breakEven = null;
    @Override
    public double getBreakEven() {
        if (_breakEven == null) {
            _breakEven = calculator.stockPriceFor(getSell(),this);
        }
        return _breakEven;
    }

    @Override
    public double calcRisc(double value) {
        return calculator.stockPriceFor(getSell() - value,this);
    }

    @Override
    public int getOid() {
        return oid;
    }

    @Override
    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public String getTicker() {
        return derivative == null ? null : derivative.getTicker();
    }

    @Override
    public Derivative getDerivative() {
        return derivative;
    }

    public void setDerivative(Derivative derivative) {
        this.derivative = derivative;
    }

    public int getDerivativeId() {
        return derivative == null ? -1 : derivative.getOid();
    }

    @Override
    public StockPrice getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(StockPrice stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getStockPriceId() {
        return stockPrice == null ? -1 : stockPrice.getOid();
    }

    @Override
    public double getDays() {
        LocalDate dx = getStockPrice().getLocalDx();

        //LocalDate x = getDerivative().getExpiry().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate x = getDerivative().getExpiry();

        return ChronoUnit.DAYS.between(dx,x);
    }

    @Override
    public double getIvBuy() {
        return calculator.iv(this,Derivative.BUY);
    }

    @Override
    public double getIvSell() {
        return calculator.iv(this,Derivative.SELL);
    }


    public void setBuy(double buy) {
        this.buy = buy;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
}
