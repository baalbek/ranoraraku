package ranoraraku.beans.options;

import oahu.financial.DerivativePrice;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 6/22/13
 * Time: 11:39 AM
 */
public class OptionPurchaseWithDerivativeBean extends OptionPurchaseBean {
    private DerivativePrice derivativePrice;


    public Optional<DerivativePrice> getDerivativePrice() {
        return Optional.of(derivativePrice);
    }

    public void setDerivativePrice(DerivativePrice derivativePrice) {
        this.derivativePrice = derivativePrice;
    }

    @Override
    public String getOptionName() {
        return derivativePrice.getDerivative().getTicker();
    }

    @Override
    public int getOptionId() {
        return derivativePrice.getOid();
    }

    @Override
    public double getPrice() {
        return derivativePrice.getSell();
    }
    @Override
    public double getBuyAtPurchase() {
        return derivativePrice.getBuy();
    }

    @Override
    public String toString() {
        return String.format("[ %s ] Op.id: %d, cat: %d, date: %s, vol: %d",
                derivativePrice.getDerivative().getTicker(),
                derivativePrice.getOid(),
                getPurchaseType(),
                getDx(),
                getVolume());
    }
}
