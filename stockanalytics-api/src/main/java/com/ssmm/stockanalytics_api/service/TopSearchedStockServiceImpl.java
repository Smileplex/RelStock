package com.ssmm.stockcrawler.service;

import com.ssmm.stockcrawler.model.TopSearchedStock;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.transaction.Transaction;

import java.util.*;

public class TopSearchedStockServiceImpl extends GenericService<TopSearchedStock> implements TopSearchedStockService {
    @Override
    public Iterable<TopSearchedStock> findAll() {
        return null;
    }

    @Override
    public TopSearchedStock find(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public <S extends TopSearchedStock> S save(S entity) {
        try (Transaction tx = session.beginTransaction()) {
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Class<TopSearchedStock> getEntityType() {
        return TopSearchedStock.class;
    }

    @Override
    public TopSearchedStock findByName(String name)
    {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return session.queryForObject(getEntityType(),"match (n:TopSearchedStock{name:{name}}) return n", params);
    }

    @Override
    public Iterable<TopSearchedStock> findAllOrderByUpdatedDateAndRank() {
        return session.query(TopSearchedStock.class, "match(n:TopSearchedStock) with n order by n.updatedDate desc limit 30\n" +
                "return n order by n.rank",new HashMap<>());
    }

}
