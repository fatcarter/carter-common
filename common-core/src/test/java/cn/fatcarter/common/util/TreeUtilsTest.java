package cn.fatcarter.common.util;

import cn.fatcarter.common.Pair;
import cn.fatcarter.common.TreeNode;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.junit.Test;

import java.util.List;

public class TreeUtilsTest {

    @Test
    public void testBuildTree() {
        List<Pair<Integer, Integer>> nodes = List.of(
                Pair.of(1, null),
                Pair.of(2, null),
                Pair.of(3, 1),
                Pair.of(4, 1),
                Pair.of(5, 2),
                Pair.of(6, 2),
                Pair.of(7, 3),
                Pair.of(8, 4),
                Pair.of(9, 5),
                Pair.of(10, 6)
        );
        List<TreeNode<Pair<Integer, Integer>, Integer>> rootNodes = TreeUtils.buildTree(nodes, null, Pair::getKey, Pair::getValue);
        System.out.println(JSON.toJSONString(rootNodes, JSONWriter.Feature.PrettyFormat));

    }
}
