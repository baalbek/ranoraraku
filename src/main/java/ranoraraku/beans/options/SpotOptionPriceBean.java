package ranoraraku.beans.options;

import oahu.financial.OptionCalculator;
import oahu.financial.SpotOptionPrice;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by rcs on 23.07.15.
 *
 */

/*
public interface DerivativePrice {
    double getDays();
    double getBuy();
    double getSell();
    Derivative getDerivative();
    StockPrice getStockPrice();
    double getIvBuy();
    double getIvSell();
    int getOid();
    void setOid(int oid);
}
*/

public class SpotOptionPriceBean implements SpotOptionPrice {
    //region Init, declarations
    // days | stock_id | ticker | opx_id |  opname  | strike |
    // exp_date  | optype | price_id |  buy  | sell  | spot_id |
    // dx     |    tm    |  spot
    private boolean _isCall;
    private int days;
    private int stockId;
    private String ticker;
    private int opxId;
    private String opxName;
    private double strike;
    private Date sqlExpDate;
    private String opxType;
    private int priceId;
    private double buy;
    private double sell;
    private int spotId;
    private Date sqlDx;
    private Time sqlTm;
    private double spot;
    //endregion Init, declarations
    
    //region Interface DerivativePrice
    /*
    @Override
    public Derivative getDerivative() {
        return null;
    }

    @Override
    public StockPrice getStockPrice() {
        return null;
    }

    @Override
    public double getIvBuy() {
        return 0;
    }

    @Override
    public double getIvSell() {
        return 0;
    }

    @Override
    public int getOid() {
        return priceId;
    }

    @Override
    public void setOid(int oid) {
        throw new NotImplementedException();
    }
    //*/
    //endregion Interface DerivativePrice

    //region Public Methods
    public double spread(){
        return sell - buy;
    }
    public double spreadPct(){
        return 100*((sell-buy)/buy);
    }

    public double ivBuy(OptionCalculator calculator) {
        return _isCall == true ?
                calculator.ivCall(spot,strike,days/365.0,buy) :
                calculator.ivCall(spot,strike,days/365.0,buy);
    }
    public double ivSell(OptionCalculator calculator) {
        return _isCall == true ?
                calculator.ivCall(spot,strike,days/365.0,sell) :
                calculator.ivCall(spot,strike,days/365.0,sell);
    }
    //endregion Public Methods

    //region Properties
    @Override
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
    public double getYears() {
        return days/365.0;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public int getOpxId() {
        return opxId;
    }

    public void setOpxId(int opxId) {
        this.opxId = opxId;
    }

    @Override
    public String getOpxName() {
        return opxName;
    }

    @Override
    public LocalDate getPurchaseDate() {
        return sqlDx.toLocalDate();
    }

    public void setOpxName(String opxName) {
        this.opxName = opxName;
    }

    public double getStrike() {
        return strike;
    }

    public void setStrike(double strike) {
        this.strike = strike;
    }

    public String getOpxType() {
        return opxType;
    }

    public void setOpxType(String opxType) {
        this.opxType = opxType;
        if (opxType.equals("c")) {
            _isCall = true;
        }
        else {
            _isCall = false;
        }
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    @Override
    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    @Override
    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Override
    public double getSpot() {
        return spot;
    }

    public void setSpot(double spot) {
        this.spot = spot;
    }

    public void setSqlExpDate(Date sqlExpDate) {
        this.sqlExpDate = sqlExpDate;
    }

    public void setSqlDx(Date sqlDx) {
        this.sqlDx = sqlDx;
    }

    public void setSqlTm(Time sqlTm) {
        this.sqlTm = sqlTm;
    }

    public LocalDate getExpDate() {
        return sqlExpDate.toLocalDate();
    }

    public LocalDate getDx() {
        return sqlDx.toLocalDate();
    }

    public LocalTime getTm() {
        return sqlTm.toLocalTime();
    }
    public boolean isCall() {
        return _isCall;
    }
    //endregion Properties
}
