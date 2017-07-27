package cn.ljh.storm.helloworld;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

public class TestTopology {
    public static void main(String[] args) throws Exception {

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("word_spout", new TestWordSpout(), 1);

        builder.setBolt("word_count_bolt_fieldGrouping",new TestWordBolt(),5)
                .fieldsGrouping("word_spout" , new Fields("word_list_spout"));

//        builder.setBolt("word_count_bolt_shuffleGrouping",new TestWordBolt(),5)
//               .shuffleGrouping("word_spout");

//        builder.setBolt("print_bolt",new TestPrintBolt(),3).shuffleGrouping("word_count_bolt_fieldGrouping");

        Config conf = new Config();
        conf.setDebug(false);

        if (args != null && args.length > 0) {
            conf.setNumWorkers(3);
            StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
        } else {
            LocalCluster cluster = new LocalCluster();
            conf.setNumWorkers(3);
            cluster.submitTopology("local_test", conf, builder.createTopology());
            Utils.sleep(10000);
            cluster.killTopology("local_test");
            cluster.shutdown();
        }
    }
}