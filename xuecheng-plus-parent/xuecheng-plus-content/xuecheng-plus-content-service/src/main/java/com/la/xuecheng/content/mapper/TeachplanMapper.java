package com.la.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.la.xuecheng.content.model.dto.TeachPlanTreeNode;
import com.la.xuecheng.content.model.po.Teachplan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    public List<TeachPlanTreeNode> selectTreeNode(Long courseId);

    public Integer selectMaxOrderBy(@Param("courseId") Long courseId, @Param("parentId") Long parentId);
}
