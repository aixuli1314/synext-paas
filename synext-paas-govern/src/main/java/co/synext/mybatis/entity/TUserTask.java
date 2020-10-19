package co.synext.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUserTask implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("PROC_DEF_KEY")
    private String procDefKey;

    @TableField("PROC_DEF_NAME")
    private String procDefName;

    @TableField("TASK_DEF_KEY")
    private String taskDefKey;

    @TableField("TASK_NAME")
    private String taskName;

    @TableField("TASK_TYPE")
    private String taskType;

    @TableField("CANDIDATE_NAME")
    private String candidateName;

    @TableField("CANDIDATE_IDS")
    private String candidateIds;


}
