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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * UserTaskQueryDTO对象
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
@ApiModel(value="UserTaskQueryDTO对象")
public class UserTaskQueryDTO extends BaseDTO<UserTaskQueryDTO, TUserTask>  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "1-当前页码")
    public Integer current = 1;

    @ApiModelProperty(value = "2-分页数量")
    public Integer size = 20;

    private String procDefKey;
    private String procDefName;
    private String taskDefKey;
    private String taskName;
    private String taskType;
    private String candidateName;
    private String candidateIds;

    public Page<TUserTask> page() {
        return new Page<>(current, size);
    }

    public static Converter<UserTaskQueryDTO, TUserTask> converter = new Converter<UserTaskQueryDTO, TUserTask>() {
        @Override
        public TUserTask doForward(UserTaskQueryDTO userTaskQueryDTO) {
            return WarpsUtils.copyTo(userTaskQueryDTO, TUserTask.class);
        }

        @Override
        public UserTaskQueryDTO doBackward(TUserTask userTask) {
            return WarpsUtils.copyTo(userTask, UserTaskQueryDTO.class);
        }
    };

    @Override
    public TUserTask convertToEntity() {
        return converter.doForward(this);
    }

    @Override
    public UserTaskQueryDTO convertFor(TUserTask userTask) {
        return converter.doBackward(userTask);
    }
}
