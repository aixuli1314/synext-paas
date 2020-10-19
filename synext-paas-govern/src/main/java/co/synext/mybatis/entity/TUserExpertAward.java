package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专家获奖表
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUserExpertAward {
    private static final long serialVersionUID=1L;

    /**
     * id主键
     */
    private String id;
    /**
     * URL路径中的部门类型,例如 URL路径中的 kjj
     */
    @TableField("orgTypePathKey")
    private String orgTypePathKey;
    /**
     * 专家Id,和从t_user的id字段同步过来
     */
    @TableField("userId")
    private String userId;
    /**
     * 专家名称,和从t_user的name字段同步过来
     */
    private String name;
    /**
     * 获奖名称
     */
    @TableField("qualificationName")
    private String qualificationName;
    /**
     * 专业开始时间
     */
    @TableField("startDate")
    private String startDate;
    /**
     * 专业结束时间
     */
    @TableField("endDate")
    private String endDate;
    /**
     * 单位或院校名称
     */
    @TableField("fromUnitName")
    private String fromUnitName;
    /**
     * 排序,查询时倒叙排列
     */
    private int sortno;
    /**
     * 是否有效(0否,1是)
     */
    private int active;
}
