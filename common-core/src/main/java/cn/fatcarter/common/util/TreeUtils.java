package cn.fatcarter.common.util;

import cn.fatcarter.common.TreeNode;
import cn.fatcarter.common.function.RecursionFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class TreeUtils {

    public static <T, ID> List<TreeNode<T, ID>> buildTree(List<T> values, ID rootParentId, Function<T, ID> idMapper, Function<T, ID> parentIdMapper) {
        Function<ID, List<TreeNode<T, ID>>> getChildren = RecursionFunction.recursion((self, parentId) -> {
            List<TreeNode<T, ID>> children = new ArrayList<>();
            for (T value : values) {
                ID parent = parentIdMapper.apply(value);
                if (!Objects.equals(parent, parentId)) {
                    continue;
                }
                ID id = idMapper.apply(value);
                TreeNode<T, ID> node = new TreeNode<>(id, parent, value, new ArrayList<>());
                node.setChildren(self.apply(self, id));
                children.add(node);
            }
            return children;
        });
        return getChildren.apply(rootParentId);
    }

}
