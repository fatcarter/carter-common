package cn.fatcarter.common;

import java.io.Serializable;
import java.util.List;

public interface TreeNode<T extends TreeNode<T, ID>, ID extends Serializable> {
    ID getId();

    ID getParentId();

    T getParent();

    void setParent(T parent);

    void setChildren(List<T> children);

    List<T> getChildren();
}
