package ranoraraku.beans.critters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 12/31/12
 * Time: 2:14 PM
 */
public class AcceptRuleBean {
    /*
    oid         | integer      | not null default nextval('accept_rules_oid_seq'::regclass)
    accValue       | price        | not null
    rtyp        | integer      | not null
    active      | bool_type    | default 'y'::bpchar
    name        | critter_name |
    description | rule_desc    |
    cid         | critter_id   |
    */
    private int oid;
    private int cid;
    private double accValue;
    private int rtyp;
    private String rtypDesc;
    private String active = "y";
    private List<DenyRuleBean> denyRules;

    public AcceptRuleBean() {}

    public AcceptRuleBean(int oid,
                          double accValue,
                          int rtyp,
                          String active) {
        this.oid = oid;
        this.accValue = accValue;
        this.rtyp = rtyp;
        this.active = active;
    }

    public AcceptRuleBean(int oid,
                          double accValue,
                          int rtyp) {
        this(oid,accValue,rtyp,"y");
    }

    //---------------- oid -----------------
    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }

    //---------------- cid -----------------
    public int getCid() {
        return cid;
    }
    public void setCid(int value) {
        this.cid = value;
    }

    //---------------- accValue -----------------
    public double getAccValue() {
        return accValue;
    }

    public void setAccValue(double accValue) {
        this.accValue = accValue;
    }


    //---------------- rtyp -----------------
    public int getRtyp() {
        return rtyp;
    }

    public void setRtyp(int rtyp) {
        this.rtyp = rtyp;
    }

    //---------------- active -----------------
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public List<DenyRuleBean> getDenyRules() {
        return denyRules;
    }

    public void setDenyRules(List<DenyRuleBean> denyRules) {
        this.denyRules = denyRules;
    }
    public void addDenyRule(DenyRuleBean dny) {
        if (denyRules == null) {
            denyRules = new ArrayList<DenyRuleBean>();
        }
        denyRules.add(dny);
    }

    public String getRtypDesc() {
        return rtypDesc;
    }

    public void setRtypDesc(String rtypDesc) {
        this.rtypDesc = rtypDesc;
    }
}
