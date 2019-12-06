package com.imooc.flink.project;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @ClassName LogAnalysis
 * @Author zzrdark
 * @Date 2019-12-06 17:56
 * @Description TODO
 **/
public class LogAnalysisJava {

    public static void main(String[] args) {


        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        String topic = "pktext";

        Properties props = new Properties();

        props.setProperty("bootstrap.servers", "192.168.199.233:9092");
        props.setProperty("group.id", "test-pk-group");


        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), props);

        DataStreamSource<String> stringDataStreamSource = executionEnvironment.addSource(consumer);



        stringDataStreamSource.map(new MapFunction<String, Object>() {

            @Override
            public Object map(String s) throws Exception {
                return null;
            }
        })
    }
}
