package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.financialModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface financialRepository
        extends MongoRepository<financialModel, String>
{

}



