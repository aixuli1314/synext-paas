package co.synext.module.Activiti.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TUserTask;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.Activiti.dto.UserTaskInputDTO;
import co.synext.module.Activiti.dto.UserTaskUpdateDTO;
import co.synext.module.Activiti.dto.UserTaskQueryDTO;
import co.synext.module.Activiti.vo.UserTaskVO;
import co.synext.module.Activiti.service.IUserTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 *  控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-16
 */
@Api(tags = "接口")
@RestController
@RequestMapping("/open/api/kjj/userTask")
@AllArgsConstructor
public class UserTaskController {
    private final IUserTaskService userTaskService;

    @ApiOperation(value = "列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TUserTask>> getPage(@RequestBody UserTaskQueryDTO userTaskQueryDTO) {
        return userTaskService.page(userTaskQueryDTO);
    }

    @ApiOperation(value = "保存接口")
    @PostMapping
    public ReturnDatas<Boolean> save(@Valid @RequestBody UserTaskInputDTO userTaskInputDTO) {
        return userTaskService.save(userTaskInputDTO);
    }

    @ApiOperation(value = "保存接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody List<UserTaskUpdateDTO> updateDTOS) {
        return userTaskService.update(updateDTOS);
    }

    @ApiOperation(value = "详情接口")
    @GetMapping("/{id}")
    public ReturnDatas<TUserTask> getById(@PathVariable String id) {
        return userTaskService.findById(id);
    }

    @ApiOperation(value = "删除接口")
    @DeleteMapping("/{id}")
    public ReturnDatas<Boolean> removeById(@PathVariable String id) {
        return userTaskService.deleteById(id);
    }

    @ApiOperation(value = "删除多个接口")
    @DeleteMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return userTaskService.batchDelete(ids);
    }

}