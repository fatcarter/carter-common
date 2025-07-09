package cn.fatcarter.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SimpleTreeNode<T, ID extends Serializable> implements TreeNode<SimpleTreeNode<T, ID>, ID> {
    private final ID id;
    private final ID parentId;
    private final T data;
    private SimpleTreeNode<T, ID> parent;
    private List<SimpleTreeNode<T, ID>> children;
}
