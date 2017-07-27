package cn.ljh.storm.helloworld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import sun.management.Sensor;

public class TestWordBolt extends BaseRichBolt {
    private int i=0;
    private HashMap<String,String> facemap = new HashMap<String, String>();
    private OutputCollector _collector;

    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {

        facemap.put("王强","抽");
        _collector = collector;
    }

    public void execute(Tuple tuple) {

        String word = tuple.getStringByField("word_list_spout");

        System.out.println(this.toString()+"===="+word);


        String face =facemap.get(word);
        if(word.equals("王强")){
            System.out.println(word+"is "+i++);
        }

        if(face == null){
            face ="fdskafsaf";
        }

        System.out.println(word+face);


        _collector.emit(tuple,new Values(word,face));
        _collector.ack(tuple);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("name","face"));
    }
}
