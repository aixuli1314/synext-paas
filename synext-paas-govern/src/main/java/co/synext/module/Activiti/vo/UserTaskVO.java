package co.synext.module.Activiti.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * TUserTaskVO对象
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Data
@Accessors(chain = true)
@ApiModel(value="TUserTaskVO对象", description="")
public class UserTaskVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String procDefKey;
    private String procDefName;
    private String taskDefKey;
    private String taskName;
    private String taskType;
    private String candidateName;
    private String candidateIds;

}
