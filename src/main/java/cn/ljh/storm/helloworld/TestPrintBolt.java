package cn.ljh.storm.helloworld;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPrintBolt extends BaseRichBolt {
//    private static Logger LOG = LoggerFactory.getLogger(TestPrintBolt.class);
    private OutputCollector _collector;

    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        _collector = collector;
    }

    public void execute(Tuple tuple) {

        String name = tuple.getStringByField("name");

        System.out.println("他是数据部门的: "+name);
        _collector.emit(tuple,new Values(name));
        _collector.ack(tuple);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        {
            declarer.declare(new Fields("print_name"));
        }
        }
}