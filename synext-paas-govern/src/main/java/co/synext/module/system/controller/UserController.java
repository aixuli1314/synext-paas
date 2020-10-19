package co.synext.module.system.controller;


import co.synext.module.system.dto.UserDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.base.vo.UserVo;
import co.synext.config.security.details.LoginUser;
import co.synext.module.system.service.IUserService;
import co.synext.mybatis.entity.TUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基础账户表 前端控制器
 * </p>
 *
 * @author xu.ran
 * @date 2019-05-30
 */
@Api(tags = "4-系统用户接口")
@RestController
@AllArgsConstructor
@RequestMapping("/system/api/kjj/user")
public class UserController {
    private final IUserService userService;

    @PostMapping("/login")
    public ReturnDatas<LoginUser> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ReturnDatas.ok(userService.login(username));
    }


    @PostMapping("/list")
    public ReturnDatas<Page<TUser>> getUserPage(@RequestBody UserDTO userDTO) {
        return userService.page(userDTO);
    }

    @ApiOperation(value = "用户详情接口")
    @PostMapping("/look/{uid}")
    public ReturnDatas<UserVo> getById(@PathVariable("uid") String uid) {
        return ReturnDatas.ok(userService.findByUid(uid));
    }

    @ApiOperation(value = "用户保存接口")
    @PostMapping("/save")
    public ReturnDatas save(@Valid @RequestBody UserDTO userDto) {
        return userService.saveUser(userDto);
    }

    @ApiOperation(value = "用户更新接口")
    @PostMapping("/update")
    public ReturnDatas updateById(@Valid @RequestBody UserDTO userDto) {
        System.out.println(userDto.toString());
        return userService.updateUser(userDto);
    }



    @ApiOperation(value = "用户删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return userService.batchDelete(ids);
    }

/*    @DeleteMapping("/{uid}")
    public ReturnDatas removeById(@PathVariable String uid) {
        return ReturnDatas.ok(userService.removeById(uid));
    }*/
}
