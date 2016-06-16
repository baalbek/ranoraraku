package ranoraraku.models.impl;

import oahu.financial.*;
import oahu.financial.repository.StockMarketRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import ranoraraku.beans.options.DerivativeBean;
import ranoraraku.beans.options.OptionPurchaseWithDerivativeBean;
import ranoraraku.beans.StockPriceBean;
import ranoraraku.models.mybatis.CritterMapper;
import ranoraraku.models.mybatis.DerivativeMapper;
import ranoraraku.models.mybatis.StockMapper;
import ranoraraku.utils.MyBatisUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Created by rcs on 11.10.14.
 *
 */
public class StockMarketReposImpl implements StockMarketRepository {
    Logger log = Logger.getLogger(getClass().getPackage().getName());
    private HashMap<Integer,Stock> idLookup;
    private HashMap<String,Stock> tickerLookup;
    private List<Stock> stocks;

    @Override
    public Derivative findDerivative(String derivativeTicker) {
        if (idLookup == null) {
            populate();
        }

        Function<SqlSession,Object> c = (session) -> {
            DerivativeMapper mapper = session.getMapper(DerivativeMapper.class);
            Derivative result = mapper.findDerivative(derivativeTicker);

            if (result == null) {
                log.warn(String.format("[%s] No derivative in options", derivativeTicker));
                return null;
            }
            ((DerivativeBean) result).setStock(idLookup.get(((DerivativeBean) result).getStockId()));
            return result;
        };

        return (Derivative)MyBatisUtils.withSession(c);
    }

    @Override
    public Stock findStock(String ticker) {
        if (tickerLookup == null) {
            populate();
        }
        return tickerLookup.get(ticker);
    }

    @Override
    public Collection<Stock> getStocks() {
        if (stocks == null) {
            populate();
        }
        return stocks;
    }

    @Override
    public Collection<StockPrice> findStockPrices(String ticker, LocalDate fromDx) {
        log.info(String.format("[findStockPrices] ticker: %s, fromDx: %s", ticker, fromDx));
        if (tickerLookup == null) {
            populate();
        }
        //Function<SqlSession,List<StockPrice>> c = (session) -> {
        return MyBatisUtils.withSession((session) -> {
            Stock stock = tickerLookup.get(ticker);
            if (stock == null) {
                log.warn(String.format("[%s] No stock in options", ticker));
                return null;
            }
            StockMapper mapper = session.getMapper(StockMapper.class);
            List<StockPrice> result = mapper.selectStockPrices(stock.getOid(), java.sql.Date.valueOf(fromDx));
            for (StockPrice p : result) {
                ((StockPriceBean) p).setStock(stock);
            }
            return result;
        });
    }

    @Override
    public void registerOptionPurchase(DerivativePrice purchase, int purchaseType, int volume) {
        MyBatisUtils.withSessionConsumer((session) -> {
            DerivativeMapper dmapper = session.getMapper(DerivativeMapper.class);
            DerivativeBean dbBean = dmapper.findDerivative(purchase.getDerivative().getTicker());
            if (dbBean == null) {
                dbBean = new DerivativeBean();
                dbBean.setTicker(purchase.getDerivative().getTicker());
                dbBean.setExpiry(purchase.getDerivative().getExpiry());
                dbBean.setOpTypeStr(purchase.getDerivative().getOpTypeStr());
                dbBean.setStock(purchase.getDerivative().getStock());
                dmapper.insertDerivative(dbBean);
            }
            CritterMapper cmapper = session.getMapper(CritterMapper.class);
            OptionPurchaseWithDerivativeBean newPurchase = new OptionPurchaseWithDerivativeBean();
            purchase.setOid(dbBean.getOid());
            //newPurchase.setDerivative(purchase.getDerivative());
            //newPurchase.setDx(new java.sql.Date());
            newPurchase.setLocalDx(LocalDate.now());
            newPurchase.setVolume(volume);
            newPurchase.setStatus(1);
            newPurchase.setPurchaseType(purchaseType);
            newPurchase.setSpotAtPurchase(purchase.getStockPrice().getCls());
            cmapper.insertPurchase(newPurchase);
        });
    }

    @Override
    public Collection<SpotOptionPrice> findOptionPrices(int opxId) {
        return MyBatisUtils.withSession((session) -> {
            return session.getMapper(DerivativeMapper.class).spotsOpricesOpxId(opxId);
        });
    }

    @Override
    public Collection<SpotOptionPrice> findOptionPricesStockId(int stockId,
                                                               LocalDate fromDate,
                                                               LocalDate toDate) {
        return MyBatisUtils.withSession((session) -> {
            return session.getMapper(DerivativeMapper.class).spotsOpricesStockId(stockId,
                    Date.valueOf(fromDate),
                    Date.valueOf(toDate));
        });
    }

    @Override
    public Collection<SpotOptionPrice> findOptionPricesStockIds(List<Integer> stockIds,
                                                                LocalDate fromDate,
                                                                LocalDate toDate) {
        return MyBatisUtils.withSession((session) -> {
            return session.getMapper(DerivativeMapper.class).spotsOpricesStockIds(stockIds,
                    Date.valueOf(fromDate),
                    Date.valueOf(toDate));
        });
    }

    @Override
    public Collection<SpotOptionPrice> findOptionPricesStockTix(List<String> stockTix,
                                                                LocalDate fromDate,
                                                                LocalDate toDate) {
        return MyBatisUtils.withSession((session) -> {
            return session.getMapper(DerivativeMapper.class).spotsOpricesStockTix(stockTix,
                    Date.valueOf(fromDate),
                    Date.valueOf(toDate));
        });
    }

    private void populate() {
        idLookup = new HashMap<>();
        tickerLookup = new HashMap<>();
        stocks = new ArrayList<>();

        SqlSession session = MyBatisUtils.getSession();

        StockMapper mapper = session.getMapper(StockMapper.class);

        List<Stock> tix = mapper.selectStocks();


        for (Stock b : tix) {
            idLookup.put(b.getOid(), b);
            tickerLookup.put(b.getTicker(), b);
            stocks.add(b);
        }
        session.commit();
        session.close();
    }
}
