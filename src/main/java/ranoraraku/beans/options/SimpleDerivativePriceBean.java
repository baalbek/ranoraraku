package ranoraraku.beans.options;

import oahu.financial.Derivative;
import oahu.financial.StockPrice;
import oahu.financial.OptionCalculator;

/**
 * Created by rcs on 13.07.15.
 *
 */
public class SimpleDerivativePriceBean extends DerivativePriceBean {
    public SimpleDerivativePriceBean() {
    }

    public SimpleDerivativePriceBean(StockPrice stockPrice,
                                     Derivative derivative,
                                     double buy,
                                     double sell) {
        super(stockPrice, derivative, buy, sell, null);
    }

    public SimpleDerivativePriceBean(StockPrice stockPrice,
                                     Derivative derivative,
                                     double buy,
                                     double sell,
                                     OptionCalculator calculator) {
        super(stockPrice, derivative, buy, sell, calculator);
    }

    @Override
    public double getIvBuy() {
        return 0.0;
    }
    @Override
    public double getIvSell() {
        return 0.0;
    }
}
