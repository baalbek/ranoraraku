package ranoraraku.models.impl;

import oahu.financial.StockPrice;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by rcs on 07.04.15.
 *
 */
public class CachedStockMarketReposImpl extends StockMarketReposImpl {
    private HashMap<String, Collection<StockPrice>> prices = new HashMap<>();

    Logger log = Logger.getLogger(getClass().getPackage().getName());

    public void emptyCache() {
        prices = new HashMap<>();
    }

    @Override
    public Collection<StockPrice> findStockPrices(String ticker, LocalDate fromDx) {
        Collection<StockPrice> curPrices = prices.get(ticker);

        if (curPrices == null) {
            log.info(String.format("Fetching fresh stock prices for ticker %s", ticker));
            curPrices = super.findStockPrices(ticker, fromDx);
            prices.put(ticker, curPrices);
        }
        else {
            log.info(String.format("Returning cached stock prices for ticker %s", ticker));
        }
        return curPrices;
    }
}
