package cn.fatcarter.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class MappedTreeNode<T, ID extends Serializable> implements TreeNode<MappedTreeNode<T, ID>, ID> {
    @Getter
    private final T value;
    private final Function<T, ID> idGetter;
    private final Function<T, ID> parentIdGetter;
    private ID id;
    private ID parentId;
    @Getter
    @Setter
    private MappedTreeNode<T, ID> parent;
    @Getter
    private List<MappedTreeNode<T, ID>> children;

    public MappedTreeNode(T value, Function<T, ID> idGetter, Function<T, ID> parentIdGetter) {
        this.value = value;
        this.idGetter = idGetter;
        this.parentIdGetter = parentIdGetter;
    }


    public static <T, ID extends Serializable> MappedTreeNode<T, ID> create(T value, Function<T, ID> idGetter, Function<T, ID> parentIdGetter) {
        return new MappedTreeNode<>(value, idGetter, parentIdGetter);
    }


    @Override
    public ID getId() {
        return id == null ? (id = idGetter.apply(this.value)) : id;
    }

    @Override
    public ID getParentId() {
        return parentId == null ? (parentId = parentIdGetter.apply(this.value)) : parentId;
    }

    @Override
    public void setChildren(List<MappedTreeNode<T, ID>> children) {
        this.children = children;
    }
}
