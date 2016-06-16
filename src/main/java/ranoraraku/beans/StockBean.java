package ranoraraku.beans;

import oahu.financial.Derivative;
import oahu.financial.Stock;
import oahu.financial.StockPrice;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 5/4/13
 * Time: 2:49 PM
 */
public class StockBean implements Stock {
    private String companyName;
    private String ticker;
    private int status;
    private int oid;
    private List<Derivative> derivatives;
    private int tickerCategory;

    @Override
    public String getTicker() {
        return ticker;
    }

    @Override
    public int getTickerCategory() {
        return tickerCategory;
    }

    public void setTickerCategory(int tickerCategory) {
        this.tickerCategory = tickerCategory;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }



    private List<StockPrice> prices;
    @Override
    public List<StockPrice> getPrices() {
        return prices;
    }
    public void setPrices(List<StockPrice> prices) {
        this.prices = prices;
    }


    @Override
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public List<Derivative> getDerivatives() {
        return derivatives;
    }

    public void setDerivatives(List<Derivative> derivatives) {
        this.derivatives = derivatives;
    }

}
