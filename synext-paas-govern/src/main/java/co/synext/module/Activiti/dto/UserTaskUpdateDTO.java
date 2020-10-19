package co.synext.module.Activiti.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import co.synext.mybatis.entity.TUserTask;
import co.synext.common.base.convert.Converter;
import co.synext.common.base.dto.BaseDTO;
import co.synext.common.utils.WarpsUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * TUserTaskUpdateDTO对象
 * 
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Data
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="UserTaskUpdateDTO对象")
public class UserTaskUpdateDTO extends BaseDTO<UserTaskUpdateDTO, TUserTask>  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String procDefKey;
    private String procDefName;
    private String taskDefKey;
    private String taskName;
    private String taskType;
    private String candidateName;
    private String candidateIds;

    public static Converter<UserTaskUpdateDTO, TUserTask> converter = new Converter<UserTaskUpdateDTO, TUserTask>() {
        @Override
        public TUserTask doForward(UserTaskUpdateDTO userTaskUpdateDTO) {
            return WarpsUtils.copyTo(userTaskUpdateDTO, TUserTask.class);
        }

        @Override
        public UserTaskUpdateDTO doBackward(TUserTask userTask) {
            return WarpsUtils.copyTo(userTask, UserTaskUpdateDTO.class);
        }
    };

    @Override
    public TUserTask convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public UserTaskUpdateDTO convertFor(TUserTask userTask) {
        return converter.doBackward(userTask);
    }
}
