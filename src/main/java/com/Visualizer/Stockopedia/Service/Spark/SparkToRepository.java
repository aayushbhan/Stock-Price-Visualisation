package com.Visualizer.Stockopedia.Service.Spark;

import com.Visualizer.Stockopedia.Model.OhlcModel;
import com.Visualizer.Stockopedia.Model.SharePriceModel;
import com.Visualizer.Stockopedia.Repository.SharePriceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SparkToRepository{
    private static SharePriceRepository sharePriceRepository;

    private static final Logger logger = LoggerFactory.getLogger(SharePriceService.class);

    public SparkToRepository(SharePriceRepository sharePriceRepository) {
        SparkToRepository.sharePriceRepository = sharePriceRepository;
    }

    public static void rddToDatabase(String symbol) throws IOException {
        File file = ResourceUtils.getFile("classpath:util.json");
        InputStream in = new FileInputStream(file);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(in,Map.class);
        String source = map.get("source").toString();

        List<OhlcModel> ohlcModelList = new ArrayList<>();
        String line;
        FileReader fileReader = new FileReader(source);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {
            String[] temp = line.split(",");
            double open = Double.parseDouble(temp[1]);
            double high = Double.parseDouble(temp[2]);
            double low = Double.parseDouble(temp[3]);
            double close = Double.parseDouble(temp[4]);
            double volume = Double.parseDouble(temp[5]);
            ohlcModelList.add(new OhlcModel(open, high, low, close, volume));
        }
        bufferedReader.close();
        logger.info(ohlcModelList.toString());

        SharePriceModel sharePriceModel = new SharePriceModel(symbol,ohlcModelList);
        sharePriceRepository.insert(sharePriceModel);


    }
}

//https://www.reddit.com/r/funnyvideos/comments/ok9u0w/bro_just_wanted_a_haircut/?utm_source=share&utm_medium=web2x&context=3
//https://www.reddit.com/r/funnyvideos/comments/okaoeo/weeeeeeeee/?utm_source=share&utm_medium=web2x&context=3
//https://www.reddit.com/r/funnyvideos/comments/ojb314/iq_200/?utm_source=share&utm_medium=web2x&context=3
//https://www.reddit.com/r/sex/comments/ojxd3v/my_boyfriend_and_i_havent_had_sex_in_a_while_and/?utm_source=share&utm_medium=web2x&context=3
//https://www.reddit.com/r/sex/comments/ok5om1/i_male_26_am_able_to_cum_without_using_my_hands/?utm_source=share&utm_medium=web2x&context=3