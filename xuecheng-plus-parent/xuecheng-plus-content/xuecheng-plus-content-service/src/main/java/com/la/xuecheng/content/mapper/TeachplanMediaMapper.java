package com.la.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.la.xuecheng.content.model.dto.TeachPlanTreeNode;
import com.la.xuecheng.content.model.po.TeachplanMedia;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMediaMapper extends BaseMapper<TeachplanMedia> {

    public List<TeachPlanTreeNode> getChildrenNodes(Long parentId);

}
