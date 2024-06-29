package com.la.xuecheng.content.api;

import com.la.xuecheng.content.model.dto.SaveTeachPlanDto;
import com.la.xuecheng.content.model.dto.TeachPlanTreeNode;
import com.la.xuecheng.content.service.TeachPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/teachplan")
    public void saveTeachplan(@RequestBody SaveTeachPlanDto saveTeachPlanDto){

        teachPlanService.saveTeachPlan(saveTeachPlanDto);
    }

    @DeleteMapping("/teachplan/{id}")
    public void deleteTeachplan(@PathVariable("id") Long id){

        teachPlanService.deleteTeachPlan(id);
    }
}
