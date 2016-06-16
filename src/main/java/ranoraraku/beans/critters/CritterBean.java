package ranoraraku.beans.critters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 12/27/12
 * Time: 10:42 PM
 */
public class CritterBean {
    private int oid;
    private String name;
    private int sellVolume;

    private int status;
    private int critterType = 1;
    private int purchaseId;
    private int saleId;

    private List<AcceptRuleBean> acceptrules;

    //----------------- oid -------------------
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    //----------------- status -------------------
    public int getStatus() {
        return status;
    }

    public void setStatus(int value) {
        this.status = value;
    }

    //----------------- critterType -------------------
    public int getCritterType() {
        return critterType;
    }

    public void setCritterType(int value) {
        this.critterType = value;
    }

    //----------------- purchaseId -------------------
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int value) {
        this.purchaseId = value;
    }

    //----------------- saleId -------------------
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    //----------------- name -------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //----------------- sellVolume -------------------
    public int getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(int sellVolume) {
        this.sellVolume = sellVolume;
    }

    //----------------- acceptRules -------------------
    public List<AcceptRuleBean> getAcceptRules() {
        return acceptrules;
    }

    public void setAcceptRules(List<AcceptRuleBean> acceptrules) {
        this.acceptrules = acceptrules;
    }
    public void addAcceptRule(AcceptRuleBean acc) {
        if (acceptrules == null) {
            acceptrules = new ArrayList<AcceptRuleBean>();
        }
        acceptrules.add(acc);
    }
}
