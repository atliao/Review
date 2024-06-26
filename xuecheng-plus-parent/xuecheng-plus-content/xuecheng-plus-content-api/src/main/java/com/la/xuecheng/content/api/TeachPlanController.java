package com.la.xuecheng.content.api;

import com.la.xuecheng.content.model.dto.TeachPlanTreeNode;
import com.la.xuecheng.content.service.TeachPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-26-16:19
 * @description
 */
@RestController
public class TeachPlanController {

    @Resource
    TeachPlanService teachPlanService;

    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachPlanTreeNode> getTreeNodes(@PathVariable("courseId") Long courseId){
        List<TeachPlanTreeNode> teachPlanTree = teachPlanService.getTeachPlanTree2(courseId);
        return teachPlanTree;
    }
}
