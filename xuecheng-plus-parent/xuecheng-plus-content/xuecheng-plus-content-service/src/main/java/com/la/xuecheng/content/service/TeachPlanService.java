package com.la.xuecheng.content.service;

import com.la.xuecheng.content.model.dto.TeachPlanTreeNode;

import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-26-17:24
 * @description
 */
public interface TeachPlanService {

    public List<TeachPlanTreeNode> getTeachPlanTree(Long courseId);

    public List<TeachPlanTreeNode> getTeachPlanTree2(Long courseId);
}
