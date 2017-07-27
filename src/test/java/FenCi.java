import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.*;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class FenCi {
    private static void test() {
        //只关注这些词性的词
        String str = "百姓网的数据部门是一个团结牛逼有韧性的部门" ;

    }

    public static void main(String[] args) {
        test();
    }
}
