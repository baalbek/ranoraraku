package ranoraraku.models.mybatis;


import org.apache.ibatis.annotations.Param;
import ranoraraku.beans.critters.*;
import ranoraraku.beans.options.OptionPurchaseBean;
import ranoraraku.beans.options.OptionSaleBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 12/27/12
 * Time: 6:03 PM
 */

public interface CritterMapper {
    void toggleAcceptRule(@Param("oid") int oid, @Param("isActive") String isActive);
    void toggleDenyRule(@Param("oid") int oid, @Param("isActive") String isActive);

    List<OptionPurchaseBean> activePurchases(@Param("tickerIds") List<Integer> tickerIds,
                                                   @Param("purchaseType") int purchaseType);

    List<OptionPurchaseBean> activePurchasesAll(@Param("purchaseType") int purchaseType);

    List<OptionPurchaseBean> purchasesWithSales(@Param("stockId") int stockId,
                                                      @Param("purchaseType") int purchaseType,
                                                      @Param("status") int status,
                                                      @Param("optype") String optype);

    List<OptionPurchaseBean> purchasesWithSalesAll(
                                                      @Param("purchaseType") int purchaseType,
                                                      @Param("status") int status,
                                                      @Param("optype") String optype);

    OptionPurchaseBean findPurchase(@Param("purchaseId") int purchaseId);

    OptionPurchaseBean findPurchaseForCritId(@Param("critterId") int critterId);

    OptionPurchaseBean findPurchaseForAccId(@Param("accId") int accId);

    void insertPurchase(OptionPurchaseBean purchase);

    void insertCritter(CritterBean critter);

    void insertGradientRule(GradientRuleBean rule);

    void insertAcceptRule(AcceptRuleBean rule);

    void insertDenyRule(DenyRuleBean rule);

    List<RuleTypeBean> ruleTypes();

    void registerCritterClosedWithSale(CritterBean critter);

    void registerPurchaseFullySold(OptionPurchaseBean purchase);

    void insertCritterSale(OptionSaleBean sale);
}
