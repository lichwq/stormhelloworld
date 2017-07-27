package cn.ljh.storm.helloworld;

import org.apache.storm.topology.OutputFieldsDeclarer;
import java.util.Map;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;
import java.util.Random;


public class TestWordSpout extends BaseRichSpout {

    private SpoutOutputCollector _collector;
    private final String[] words = new String[] {"王强", "家利", "飞爷", "斌斌","二娴","国粹","温鹿"};

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {

        _collector = collector;
    }

    public void nextTuple() {
        Utils.sleep(100);
        final Random rand = new Random();
            _collector.emit(new Values(words[rand.nextInt(words.length)]),"name_king_word");
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word_list_spout"));
    }
}