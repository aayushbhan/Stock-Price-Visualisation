package com.Visualizer.Stockopedia.Service.Spark;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.fasterxml.jackson.databind.ObjectMapper;

import kafka.serializer.Decoder;
import kafka.utils.VerifiableProperties;


public class AlphaVantageTimeSeriesDailyJsonDecoder implements Decoder<AlphaVantageTimeSeriesDailyJson>{
    private static ObjectMapper objectMapper = new ObjectMapper();

    public AlphaVantageTimeSeriesDailyJsonDecoder(VerifiableProperties verifiableProperties) {

    }
    public AlphaVantageTimeSeriesDailyJson fromBytes(byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, AlphaVantageTimeSeriesDailyJson.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
