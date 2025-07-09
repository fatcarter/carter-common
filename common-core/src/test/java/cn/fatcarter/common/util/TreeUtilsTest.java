package cn.fatcarter.common.util;

import cn.fatcarter.common.SimpleTreeNode;
import cn.fatcarter.common.TreeNode;
import cn.fatcarter.common.function.RecursionFunction;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class TreeUtilsTest {
    private static List<SimpleTreeNode<Integer, Integer>> nodes = null;

    @BeforeClass
    public static void before() {
        nodes = List.of(
            new SimpleTreeNode<>(1, null, 1),
            new SimpleTreeNode<>(2, null, 2),
            new SimpleTreeNode<>(3, 1, 3),
            new SimpleTreeNode<>(4, 1, 4),
            new SimpleTreeNode<>(5, 2, 5),
            new SimpleTreeNode<>(6, 2, 6),
            new SimpleTreeNode<>(7, 3, 7),
            new SimpleTreeNode<>(8, 4, 8),
            new SimpleTreeNode<>(9, 5, 9),
            new SimpleTreeNode<>(10, 6, 10)
        );

    }

    @Test
    public void testBuildTree() {
        List<SimpleTreeNode<Integer, Integer>> rootNodes = TreeUtils.buildTree(null, nodes);
        AtomicInteger level = new AtomicInteger(0);
        Function<SimpleTreeNode<Integer, Integer>, Void> printer = RecursionFunction.recursion((self, node) -> {
            System.out.println((StringUtils.repeat("\t", level.get()) + node.getId()));
            if (node.getChildren() != null) {
                level.incrementAndGet();
                for (SimpleTreeNode<Integer, Integer> child : node.getChildren()) {
                    self.apply(self, child);
                }
                level.decrementAndGet();
            }
            return null;
        });
        for (SimpleTreeNode<Integer, Integer> node : rootNodes) {
            printer.apply(node);
        }

//
//        System.out.println(JSON.toJSONString(rootNodes, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.ReferenceDetection));
//        rootNodes = TreeUtils.buildTree(nodes);
//        System.out.println(JSON.toJSONString(rootNodes, JSONWriter.Feature.PrettyFormat, JSONWriter.Feature.ReferenceDetection));
    }

    @Test
    public void eachNodeTest() {
        List<SimpleTreeNode<Integer, Integer>> rootNodes = TreeUtils.buildTree(null, nodes);
        TreeUtils.eachRootTree(rootNodes, (parent, node) -> {
            System.out.println("parent=" + Op.map(parent, SimpleTreeNode::getId) + ", node=" + node.getId());
        });
    }

    @Test
    public void flattenTreeTest() {
        List<SimpleTreeNode<Integer, Integer>> rootNodes = TreeUtils.buildTree(nodes);
        for (SimpleTreeNode<Integer, Integer> rootNode : rootNodes) {
            List<List<Integer>> flatten = TreeUtils.flatten(rootNode, TreeNode::getId);
//            System.out.println(JSON.toJSONString(flatten, JSONWriter.Feature.PrettyFormat));
        }
    }


}
