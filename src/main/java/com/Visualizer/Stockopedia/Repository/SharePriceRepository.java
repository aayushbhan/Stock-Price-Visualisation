package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.SharePriceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharePriceRepository extends MongoRepository<SharePriceModel, String> {
}
