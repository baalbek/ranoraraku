package ranoraraku.beans.options;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by rcs on 20.09.16.
 *
 */
public class SpotOptionPriceSummaryBean {
    /*
    select t.ticker,s.stock_id,date_part('year',s.dx) as year,date_part('month',s.dx) as month,
    count(1) as num_prices,max(s.dx) as max_dx
    from stockmarket.spot s
    join stockmarket.stocktickers t on t.oid=s.stock_id
    group by t.ticker,s.stock_id,date_part('year',s.dx),date_part('month',s.dx)
    order by s.stock_id,date_part('year',s.dx),date_part('month',s.dx)
    */
    private String ticker;
    private int stockId;
    private int year;
    private int month;
    private int numPrices;
    private Date sqlMaxDx;

    public LocalDate getMaxDx() {
        return sqlMaxDx.toLocalDate();
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumPrices() {
        return numPrices;
    }

    public void setNumPrices(int numPrices) {
        this.numPrices = numPrices;
    }

    public Date getSqlMaxDx() {
        return sqlMaxDx;
    }

    public void setSqlMaxDx(Date sqlMaxDx) {
        this.sqlMaxDx = sqlMaxDx;
    }
}
