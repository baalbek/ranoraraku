package ranoraraku.beans.options;

import oahu.exceptions.BinarySearchException;
import oahu.financial.Derivative;
import oahu.financial.DerivativePrice;
import oahu.financial.OptionCalculator;
import oahu.financial.StockPrice;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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


    private Optional<Double> _breakEven = null;
    @Override
    public Optional<Double> getBreakEven() {
        try {
            if (_breakEven == null) {
                _breakEven = Optional.of(calculator.stockPriceFor(getSell(), this));
            }
        }
        catch (BinarySearchException ex) {
            System.out.println(String.format("[%s] %s",getTicker(),ex.getMessage()));
            _breakEven = Optional.empty();
        }
        return _breakEven;
    }

    private double _currentRiscOptionValue;
    private Optional<Double> _currentRiscStockPrice;
    @Override
    public Optional<Double> stockPriceFor(double optionValue) {
        try {
            //Double result = calculator.stockPriceFor(getSell() - optionValue,this);
            Double result = calculator.stockPriceFor(optionValue,this);
            _currentRiscOptionValue = optionValue;
            _currentRiscStockPrice = Optional.of(result);
            return _currentRiscStockPrice;
        }
        catch (BinarySearchException ex) {
            System.out.println(String.format("[%s] %s",getTicker(),ex.getMessage()));
            return Optional.empty();
        }
    }

    @Override
    public double optionPriceFor(double stockPrice) {
        double strike = derivative.getX();
        double expiry = getDays()/365.0;
        return derivative.getOpType() == Derivative.OptionType.CALL ?
                calculator.callPrice(stockPrice,strike,expiry,_ivBuy.get()) :
                calculator.putPrice(stockPrice,strike,expiry,_ivBuy.get());
    }

    public double getCurrentRiscOptionValue() {
        return _currentRiscOptionValue;
    }

    @Override
    public double getCurrentRisc() {
        return sell - _currentRiscOptionValue;
    }

    public Optional<Double> getCurrentRiscStockPrice(){
        if (_currentRiscStockPrice == null) {
            _currentRiscStockPrice = Optional.empty();
        }
        return _currentRiscStockPrice;
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

    private Optional<Double> _ivBuy = null;
    @Override
    public Optional<Double> getIvBuy() {
        if (_ivBuy == null) {
            try {
                _ivBuy = Optional.of(calculator.iv(this,Derivative.BUY));
            }
            catch (BinarySearchException ex) {
                System.out.println(String.format("[%s] %s",getTicker(),ex.getMessage()));
                _ivBuy = Optional.empty();
            }
        }
        return _ivBuy;
    }

    private Optional<Double> _ivSell = null;
    @Override
    public Optional<Double> getIvSell() {
        if (_ivSell == null) {
            try {
                _ivSell = Optional.of(calculator.iv(this,Derivative.SELL));
            }
            catch (BinarySearchException ex) {
                System.out.println(String.format("[%s] %s",getTicker(),ex.getMessage()));
                _ivSell = Optional.empty();
            }
        }
        return _ivSell;
    }


    public void setBuy(double buy) {
        this.buy = buy;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
}
