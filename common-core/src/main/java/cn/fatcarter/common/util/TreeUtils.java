package cn.fatcarter.common.util;

import cn.fatcarter.common.TreeNode;
import cn.fatcarter.common.function.RecursionFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class TreeUtils {

    public static <ID extends Serializable, T extends TreeNode<T, ID>> void eachNodes(T parent, List<T> nodes, BiPredicate<T, T> treeNodeHandler) {
        for (T node : nodes) {
            boolean noBreak = treeNodeHandler.test(parent, node);
            if (noBreak && node.getChildren() != null && !node.getChildren().isEmpty()) {
                eachNodes(node, node.getChildren(), treeNodeHandler);
            }
        }
    }


    public static <ID extends Serializable, T extends TreeNode<T, ID>> void eachNodes(T parent, List<T> nodes, BiConsumer<T, T> treeNodeHandler) {
        eachNodes(parent, nodes, (p, node) -> {
            treeNodeHandler.accept(p, node);
            return true;
        });
    }

    public static <ID extends Serializable, T extends TreeNode<T, ID>> void eachRootTree(List<T> nodes, BiConsumer<T, T> treeNodeHandler) {
        eachNodes(null, nodes, treeNodeHandler);
    }


    public static <ID extends Serializable, T extends TreeNode<T, ID>> List<T> buildTree(List<T> nodes) {
        return buildTree(null, nodes);
    }

    public static <ID extends Serializable, T extends TreeNode<T, ID>> List<T> buildTree(ID rootParentId, List<T> nodes) {
        Function<T, List<T>> getChildren = RecursionFunction.recursion((self, parent) -> {
            List<T> children = new ArrayList<>();
            for (T node : nodes) {
                if (!Objects.equals(node.getParentId(), parent.getId())) {
                    continue;
                }
                node.setParent(parent);
                node.setChildren(self.apply(self, node));
                children.add(node);
            }
            return children;
        });
        return StreamUtils.filterMapToList(nodes, n -> Objects.equals(rootParentId, n.getParentId()), n -> {
            var children = getChildren.apply(n);
            n.setChildren(children);
            return n;
        });
    }

    public static <ID extends Serializable, T extends TreeNode<T, ID>> List<List<T>> flatten(T root) {
        return flatten(root, Function.identity());
    }

    public static <ID extends Serializable, T extends TreeNode<T, ID>, N> List<List<N>> flatten(T root, Function<T, N> nameMapper) {
        List<List<N>> result = new ArrayList<>();
        traverse(root, new ArrayList<>(), result, nameMapper);
        return result;
    }

    private static <ID extends Serializable, T extends TreeNode<T, ID>, N> void traverse(T node, List<N> path, List<List<N>> result, Function<T, N> nameMapper) {
        if (node == null) return;

        path.add(nameMapper.apply(node));

        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            result.add(new ArrayList<>(path)); // 复制当前路径
        } else {
            for (T child : node.getChildren()) {
                traverse(child, path, result, nameMapper);
            }
        }

        path.remove(path.size() - 1); // 回溯
    }

}
