package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Document(collection = "financials")
public class RepositoryModel {
    @Id
    String dataId;

    @Field("data")
    List<financialModel> financialModelList;
}
