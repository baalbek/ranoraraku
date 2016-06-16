package ranoraraku.models.mybatis;

import oahu.financial.SpotOptionPrice;
import oahu.financial.Stock;
import org.apache.ibatis.annotations.Param;
import ranoraraku.beans.StockPriceBean;
import ranoraraku.beans.options.DerivativeBean;
import ranoraraku.beans.options.DerivativePriceBean;

import java.sql.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: rcs
 * Date: 4/18/13
 * Time: 8:52 PM
 */

public interface DerivativeMapper {
    void insertSpot(StockPriceBean s);
    void insertDerivativePrice(DerivativePriceBean d);
    void insertDerivative(DerivativeBean d);

    int countDerivative(String ticker);

    int countIvForSpot(StockPriceBean s);

    int countOpxPricesForSpot(StockPriceBean s);

    Integer findSpotId(StockPriceBean s);

    DerivativeBean findDerivative(String ticker);

    /*
    List<Stock> selectDerivatives(@Param("tickerIds") List<Integer> tickerIds,
                                  @Param("fromDx") Date fromDx);
                                    */

    List<SpotOptionPrice> spotsOprices();
    List<SpotOptionPrice> spotsOpricesOpxId(@Param("opxId") int opxId);
    List<SpotOptionPrice> spotsOpricesStockId(@Param("stockId") int stockId,
                                                  @Param("fromDx") Date fromDx,
                                                  @Param("toDx") Date toDx);
    List<SpotOptionPrice> spotsOpricesStockIds(@Param("stockIds") List<Integer> stockIds,
                                              @Param("fromDx") Date fromDx,
                                              @Param("toDx") Date toDx);
    List<SpotOptionPrice> spotsOpricesStockTix(@Param("stockTix") List<String> stockTix,
                                               @Param("fromDx") Date fromDx,
                                               @Param("toDx") Date toDx);
    void insertBlackScholes(@Param("oid") int oid,
                            @Param("ivBuy") double ivBuy,
                            @Param("ivSell") double ivSell);

    Stock calls(@Param("tickerId") int tickerId,
                           @Param("fromDx") Date fromDx);

    Stock puts(@Param("tickerId") int tickerId,
                           @Param("fromDx") Date fromDx);

}
