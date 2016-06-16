package ranoraraku.beans.critters;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 12/31/12
 * Time: 2:15 PM
 */
public class DenyRuleBean {
    /*
    oid        | integer   | not null default nextval('deny_rules_oid_seq'::regclass)
    denyValue      | price     | not null
    rtyp       | integer   | not null
    group_id   | integer   | not null
    active     | bool_type | default 'y'::bpchar
    has_memory | bool_type | default 'y'::bpchar
    */
    private int oid;
    private int groupId;
    private double denyValue;
    private int rtyp;
    private String rtypDesc;
    private String active = "y";
    private String memory;

    public DenyRuleBean() {
    }

    public DenyRuleBean(int oid,
                        double denyValue,
                        int rtyp,
                        String active,
                        String memory) {
        this.oid = oid;
        this.denyValue = denyValue;
        this.rtyp = rtyp;
        this.active = active;
        this.memory = memory;
    }

    public DenyRuleBean(int oid,
                        double denyValue,
                        int rtyp) {
        this(oid,denyValue,rtyp,"y","y");
    }

    //---------------- oid ----------------
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    //---------------- groupId ----------------
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int value) {
        this.groupId = value;
    }

    //---------------- oid ----------------
    public double getDenyValue() {
        return denyValue;
    }

    public void setDenyValue(double denyValue) {
        this.denyValue = denyValue;
    }

    //---------------- oid ----------------
    public int getRtyp() {
        return rtyp;
    }

    public void setRtyp(int rtyp) {
        this.rtyp = rtyp;
    }

    //---------------- oid ----------------
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    //---------------- oid ----------------
    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getRtypDesc() {
        return rtypDesc;
    }

    public void setRtypDesc(String rtypDesc) {
        this.rtypDesc = rtypDesc;
    }
}
