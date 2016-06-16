package ranoraraku.beans.options;

import oahu.financial.Derivative;
import oahu.financial.Stock;

//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 11/21/12
 * Time: 10:43 PM
 */
public class DerivativeBean implements Derivative {

    private static Pattern p = Pattern.compile("\\D+(\\d\\D)\\d+");

    private Stock stock;


    public DerivativeBean() {
    }


    public DerivativeBean(String ticker,
                          int opType,
                          double x,
                          LocalDate expiry,
                          Stock stock) {
        setTicker(ticker);
        setOpType(opType);
        setX(x);
        setExpiry(expiry);
        setStock(stock);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(getTicker());
        /*
        buf.append("\n\tParent: ").append(parent == null ? "none" : "parent") // parent.getTicker())
        */
           buf.append("\n\texpiry: ").append(getExpiry())
           .append("\n\toption type: ").append(getOpType() == CALL ? "apply" : "put")
           .append("\n\tx: ").append(getX())
           //.append("\n\tbuy ").append(getBuy())
           //.append("\n\tsell ").append(getSell())
           .append("\n\tseries ").append(getSeries());
           //.append("\n\twatermark ").append(getWatermark());
        //*/
        return buf.toString();
    }

    //--------------------------------------------------
    //------------- Parent
    //--------------------------------------------------
    @Override
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    private Integer _stockId = null;
    public int getStockId() {
        return stock == null ? _stockId : stock.getOid();
    }
    public void setStockId(int value) {
        _stockId = value;
    }


    //--------------------------------------------------
    //------------- Id
    //--------------------------------------------------
    private int oid;
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    //--------------------------------------------------
    //------------- Expiry
    //--------------------------------------------------
    private LocalDate expiry;
    @Override
    public LocalDate getExpiry() {
        return expiry;
    }
    public void setExpiry(LocalDate value) {
        expiry = value;
    }

    public Date getExpirySql() {
        return Date.valueOf(expiry);
    }

    public void setExpirySql(Date expirySql) {
        this.expiry = expirySql.toLocalDate();
    }

    //--------------------------------------------------
    //------------- OpType
    //--------------------------------------------------
    private int opType;
    @Override
    public int getOpType() {
        return opType;
    }


    public void setOpType(int value) {
            opType = value;
    }

    public String getOpTypeStr() {
        return getOpType() == 1 ? "c" : "p";
    }
    public void setOpTypeStr(String value) {
        if (value.equals("c")) {
            setOpType(1);
        }
        else {
            setOpType(2);
        }
    }


    //--------------------------------------------------
    //------------- Series
    //--------------------------------------------------

    private String series;
    public String getSeries() {
        if (series == null) {
            Matcher m = p.matcher(getTicker());
            if (m.find()) {
                series = m.group(1);
            }
            else {
                series = "??";
            }
        }
        return series;
    }
    public void setSeries(String value) {
        series = value;
    }

    //--------------------------------------------------
    //------------- X
    //--------------------------------------------------
    private double x;
    @Override
    public double getX() {
        return x;
    }



    public void setX(double value) {
        x = value;
    }


    //--------------------------------------------------
    //------------- Ticker
    //--------------------------------------------------
    private String ticker;
    public String getTicker() {
        return ticker;
    }



    public void setTicker(String value) {
        ticker = value;
    }

}
