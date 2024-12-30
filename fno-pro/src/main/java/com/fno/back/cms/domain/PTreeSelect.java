package com.fno.back.cms.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 * 
 * @author ry
 */
@Data
public class PTreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    private List<CmsContent> contentList;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PTreeSelect> children;

    public PTreeSelect()
    {

    }

    public PTreeSelect(CmsCatalog catalog)
    {
        this.id = catalog.getId();
        this.label = catalog.getName();
        this.children = catalog.getChildren().stream().map(PTreeSelect::new).collect(Collectors.toList());
    }

    public PTreeSelect(Object o) {
        CmsCatalog catalog = (CmsCatalog)o;
        this.id = catalog.getId();
        this.label = catalog.getName();
        this.children = catalog.getChildren().stream().map(PTreeSelect::new).collect(Collectors.toList());
    }


}
