package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TUserExpert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.UserExpertInputDTO;
import co.synext.module.system.dto.UserExpertUpdateDTO;
import co.synext.module.system.dto.UserExpertQueryDTO;
import co.synext.module.system.service.IUserExpertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 专家信息表,是t_user表的扩展表,userType=3 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-08
 */
@Api(tags = "专家信息表接口")
@RestController
@RequestMapping("/expert/api/kjj/userexpert")
@AllArgsConstructor
public class UserExpertController {
    private final IUserExpertService userExpertService;

    @ApiOperation(value = "列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TUserExpert>> getPage(@RequestBody UserExpertQueryDTO userExpertQueryDTO) {
        return userExpertService.page(userExpertQueryDTO);
    }

    @ApiOperation(value = "保存接口")
    @PostMapping("/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody UserExpertInputDTO userExpertInputDTO) {
        return userExpertService.save(userExpertInputDTO);
    }

    @ApiOperation(value = "更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody UserExpertUpdateDTO userExpertUpdateDTO) {
        return userExpertService.update(userExpertUpdateDTO);
    }

    @ApiOperation(value = "详情接口")
    @PostMapping("/look/{id}")
    public ReturnDatas<TUserExpert> getById(@PathVariable String id) {
        return userExpertService.findById(id);
    }

    @ApiOperation(value = "删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return userExpertService.batchDelete(ids);
    }


}