package ranoraraku.beans.options;

import oahu.dto.Tuple;
import oahu.financial.Derivative;
import oahu.financial.DerivativePrice;
import oahu.financial.repository.DerivativeRepository;
import ranoraraku.beans.critters.CritterBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 12/29/12
 * Time: 3:19 PM
 */
public class OptionPurchaseBean {
    //region Init
    private int oid;
    private LocalDate localDx;
    private int status;
    private double price;
    private double buyAtPurchase;
    private long volume;

    private String ticker;
    private String optionName;
    private String optionType;

    private int optionId;
    private int purchaseType;
    private double spotAtPurchase;

    private DerivativeRepository<Tuple<String>> repository;

    private List<CritterBean> critters;
    private List<OptionSaleBean> sales;

    public OptionPurchaseBean() {}

    //endregion Init

    //region Public Utility Methods
    public long remainingVolume() {
        return volume - volumeSold() - volumeActiveCritters();
    }
    public long volumeActiveCritters() {
        if ((critters == null) || (critters.size() == 0)) return 0;

        return critters.stream().mapToLong(crit -> {
            if (crit.getStatus() == 7) {
                return crit.getSellVolume();
            }
            else {
                return 0;
            }
        }).sum();
    }
    public long volumeSold() {
        if ((sales == null) || (sales.size() == 0)) return 0;

        return sales.stream().mapToLong(OptionSaleBean::getVolume).sum();
    }
    public void addSale(OptionSaleBean sale) {
        if (sales == null) {
            sales = new ArrayList<>();
        }
        sales.add(sale);
        if (isFullySold() == true) {
            status = 2;
        }
    }
    public boolean isFullySold() {
        return volumeSold() < volume ? false : true;
    }
    //endregion Public Utility Methods


    //region Properties
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    //region Dates
    public Date getDx() {
        return Date.valueOf(getLocalDx());
    }

    public void setDx(Date dx) {
        this.localDx = dx.toLocalDate();
    }
    public LocalDate getLocalDx() {
        if (localDx == null) {
            localDx = java.time.LocalDate.now();
        }
        return localDx;
    }

    public void setLocalDx(LocalDate localDx) {
        this.localDx = localDx;
    }

    //endregion Dates

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public List<CritterBean> getCritters() {
        return critters;
    }

    public void setCritters(List<CritterBean> critters) {
        this.critters = critters;
    }

    public List<OptionSaleBean> getSales() {
        return sales;
    }
    public void setSales(List<OptionSaleBean> sales) {
        this.sales = sales;
    }
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getOptionId() {
        return optionId;
    }
    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getPurchaseType() {
        return purchaseType;
    }
    public void setPurchaseType(int purchaseType) {
        this.purchaseType = purchaseType;
    }

    public double getSpotAtPurchase() {
        return spotAtPurchase;
    }
    public void setSpotAtPurchase(double spotAtPurchase) {
        this.spotAtPurchase = spotAtPurchase;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBuyAtPurchase() {
        return buyAtPurchase;
    }

    public void setBuyAtPurchase(double buyAtPurchase) {
        this.buyAtPurchase = buyAtPurchase;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }



    public DerivativeRepository getRepository() {
        return repository;
    }

    public void setRepository(DerivativeRepository repository) {
        this.repository = repository;
    }
    //endregion Properties

    //region Monadic Methods
    public Optional<DerivativePrice> getDerivativePrice() {
        return repository.findDerivativePrice(new Tuple<>(ticker,optionName));
    }

    private Double _watermark = null;
    public Double getWatermark() {
        Optional<DerivativePrice> curDeriv = getDerivativePrice();

        if (!curDeriv.isPresent()) return null;

        DerivativePrice price = curDeriv.get();
        if ((_watermark == null) || (price.getBuy() > _watermark)) {
            _watermark = price.getBuy();
        }
        //region Obsolete
        /*
        if (_watermark == null) {
            _watermark = curDeriv.getBuy();
        }
        else {
            if (curDeriv.getBuy() > _watermark) {
                _watermark = curDeriv.getBuy();
            }
        }
        */
        //endregion Obsolete
        return _watermark;
    }


    //endregion Monadic Methods

}
