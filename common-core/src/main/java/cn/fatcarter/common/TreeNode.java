package cn.fatcarter.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TreeNode<T, ID> {
    private ID nodeId;
    private ID parentNodeId;
    private T node;
    private List<TreeNode<T, ID>> children;
}
