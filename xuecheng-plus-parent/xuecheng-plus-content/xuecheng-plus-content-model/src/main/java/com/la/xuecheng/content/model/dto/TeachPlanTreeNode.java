package com.la.xuecheng.content.model.dto;

import com.la.xuecheng.content.model.po.Teachplan;
import com.la.xuecheng.content.model.po.TeachplanMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author LA
 * @createDate 2024-06-26-14:56
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeachPlanTreeNode extends Teachplan {

    private TeachplanMedia teachplanMedia;

    private List<TeachPlanTreeNode> teachPlanTreeNodes;
}
