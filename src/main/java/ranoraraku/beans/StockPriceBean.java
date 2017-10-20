package ranoraraku.beans;

import oahu.financial.DerivativePrice;
import oahu.financial.Stock;
import oahu.financial.StockPrice;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//import org.joda.time.DateMidnight;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 11/21/12
 * Time: 10:43 PM
 */
public class StockPriceBean implements StockPrice {


    //private SimpleStringProperty ticker = new SimpleStringProperty();
    private LocalDate localDx;
    private LocalTime tm;
    private double opn;
    private double hi;
    private double lo;
    private double cls;
    private double marketValue;
    private int volume;
    private int oid = -1;
    private List<DerivativePrice> optionPrices;


    public StockPriceBean() {
    }

    public StockPriceBean(Date dx,
                          double opn,
                          double hi,
                          double lo,
                          double cls,
                          int volume) {
        this(dx.toLocalDate(), opn, hi, lo, cls, volume);
    }
    public StockPriceBean(LocalDate dx,
                          double opn,
                          double hi,
                          double lo,
                          double cls,
                          int volume) {
        this.localDx = dx;
        this.opn = opn;
        this.hi = hi;
        this.lo = lo;
        this.cls = cls;
        this.volume = volume;
    }
    public StockPriceBean(LocalDate dx,
                          LocalTime tm,
                          double opn,
                          double hi,
                          double lo,
                          double cls,
                          int volume) {
        this(dx,opn,hi,lo,cls,volume);
        this.setTm(tm);
    }


    private Stock stock = null;
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    @Override
    public Stock getStock() {
        return stock;
    }

    public int getStockId() {
        return stock == null ? -1 : stock.getOid();
    }
    @Override
    public void assign(StockPrice other) {
        this.setCls(other.getCls());
        this.setHi(other.getHi());
        this.setLo(other.getLo());
        this.setOpn(other.getOpn());
        this.setVolume(other.getVolume());
    }

    @Override
    public List<DerivativePrice> getOptionPrices() {
        return optionPrices;
    }
    public void setOptionPrices(List<DerivativePrice> value) {
        this.optionPrices = value;
    }


    //region Time

    @Override
    public LocalTime getTm() {
        return tm;
    }
    public void setTm(LocalTime tm) {
        this.tm = tm;
    }
    public Time getSqlTime() {
        return new Time(tm.getHour(), tm.getMinute(), tm.getSecond());
    }

    public Date getDx() {
        return Date.valueOf(localDx);
    }

    public void setDx(Date value) {
        localDx = value.toLocalDate();
    }

    @Override
    public LocalDate getLocalDx() {
       return localDx;
    }

    public void setLocalDx(LocalDate value) {
        this.localDx = value;
    }
    //endregion Time


    @Override
    public String toString() {
        return String.format("[%s] dx: %s, tm: %s",
                stock == null ? "-" :stock.getTicker(),
                localDx,
                tm);
    }

    @Override
    public double getOpn() {
        return opn;
    }
    public void setOpn(double value) {
        opn = value;
    }

    @Override
    public double getHi() {
        return hi;
    }
    public void setHi(double value) {
        hi = value;
    }

    @Override
    public double getLo() {
        return lo;
    }
    public void setLo(double value) {
        lo = value;
    }

    @Override
    public double getCls() {
        return cls;
    }
    public void setCls(double value) {
        cls = value;
    }

    @Override
    public int getVolume() {
        return volume;
    }
    public void setVolume(int value) {
        volume = value;
    }

    @Override
    public double getValue() {
        return cls;
    }

    @Override
    public double getMarketValue() {
        return marketValue;
    }
    public void setMarketValue(double value) {
        marketValue = value;
    }
    @Override
    public int getOid() {
        return oid;
    }

    //region Non-interface Properties
    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getTicker() {
        return getStock() == null ? "N/A" : getStock().getTicker();
    }

    public int getTickerId() {
        return getStock().getOid();
    }


    /*
    @Override
    public int compareTo(Object other) {
        return localDx.compareTo(((StockPriceBean)other).getLocalDx());
    }
    //*/

    //endregion Non-interface Properties

    //region Diverse Utilities
      /*
    public void assign(StockPriceBean other) {
        this.stockTicker = other.stockTicker;
        this.tickerId = other.tickerId;
        this.setOpn(other.getOpn());
        this.setHi(other.getHi());
        this.setLo(other.getLo());
        this.setCls(other.getCls());
        this.setVolume(other.getVolume());
        this.setDx(other.getDx());
    }
    */
    //endregion
}
