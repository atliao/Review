package com.la.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.la.xuecheng.content.mapper.TeachplanMapper;
import com.la.xuecheng.content.mapper.TeachplanMediaMapper;
import com.la.xuecheng.content.model.dto.SaveTeachPlanDto;
import com.la.xuecheng.content.model.dto.TeachPlanTreeNode;
import com.la.xuecheng.content.model.po.Teachplan;
import com.la.xuecheng.content.model.po.TeachplanMedia;
import com.la.xuecheng.content.service.TeachPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-26-17:25
 * @description
 */
@Service
@Slf4j
public class TeachPlanServiceImpl implements TeachPlanService {

    @Resource
    TeachplanMapper teachplanMapper;

    @Resource
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachPlanTreeNode> getTeachPlanTree(Long courseId) {
        List<TeachPlanTreeNode> result = teachplanMapper.selectTreeNode(courseId);
        return result;
    }

    @Override
    public List<TeachPlanTreeNode> getTeachPlanTree2(Long courseId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId, courseId).eq(Teachplan::getGrade, "1").orderByAsc(Teachplan::getOrderby);
        List<Teachplan> teachPlans = teachplanMapper.selectList(queryWrapper);
        List<TeachPlanTreeNode> res = new ArrayList<>();
        for(Teachplan teachplan : teachPlans){
            TeachPlanTreeNode teachPlanTreeNode = new TeachPlanTreeNode();
            BeanUtils.copyProperties(teachplan, teachPlanTreeNode);
            teachPlanTreeNode.setTeachPlanTreeNodes(getChildren(teachplan.getId()));
            res.add(teachPlanTreeNode);
        }
        return res;
    }

    public List<TeachPlanTreeNode> getChildren(Long parentId){
        List<TeachPlanTreeNode> childrenNodes = teachplanMediaMapper.getChildrenNodes(parentId);
        for(TeachPlanTreeNode t : childrenNodes){
            t.setTeachPlanTreeNodes(getChildren(t.getId()));
        }
        return childrenNodes;
    }

    @Transactional
    @Override
    public void saveTeachPlan(SaveTeachPlanDto saveTeachPlanDto){
        Long id = saveTeachPlanDto.getId();
        if(id == null){
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachPlanDto, teachplan);
            Long parentId = saveTeachPlanDto.getParentid();
            Long courseId = saveTeachPlanDto.getCourseId();
            Integer max = teachplanMapper.selectMaxOrderBy(courseId, parentId);
            teachplan.setOrderby(max == null ? 1 : max+1);
            teachplanMapper.insert(teachplan);
        }else {
            Teachplan teachPlan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(saveTeachPlanDto, teachPlan);
            teachplanMapper.updateById(teachPlan);
        }
    }

    @Transactional
    @Override
    public void deleteTeachPlan(Long id) {
        Teachplan teachplan = teachplanMapper.selectById(id);
        if(teachplan != null){
            teachplanMapper.deleteById(id);
        }
    }



}
