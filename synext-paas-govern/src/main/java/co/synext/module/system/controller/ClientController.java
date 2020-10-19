package co.synext.module.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TOauthClientDetails;
import co.synext.module.system.service.IOauthClientDetailsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "4-Oauth2客户端接口")
@RestController
@AllArgsConstructor
@RequestMapping("/member/api/kjj/clients")
public class ClientController {
  private final IOauthClientDetailsService sysOauthClientDetailsService;

 /**
   * 分页查询
   * @param page 分页对象
   * @param queryBean
   * @return
   */
  @PostMapping("/page")
  public ReturnDatas<Page<TOauthClientDetails>> getSysOauthClientDetailsPage(Page page, TOauthClientDetails queryBean) {
    return  ReturnDatas.ok(sysOauthClientDetailsService.page(page, Wrappers.query(queryBean)));
  }

      /**
       * 通过id查询
       * @param id
       * @return R
       */
   @GetMapping("/{id}")
   public ReturnDatas<TOauthClientDetails> getById(@PathVariable("id") Long id){
     return ReturnDatas.ok(sysOauthClientDetailsService.getById(id));
   }

  /**
   * 新增
   * @param entity
   * @return R
   */
  @PostMapping
  public ReturnDatas<Boolean> save(TOauthClientDetails entity){
    return sysOauthClientDetailsService.createClient(entity);
  }


  /**
   * 修改
   * @param entity
   * @return R
   */
  @PutMapping
  public ReturnDatas<Boolean> updateById(TOauthClientDetails entity){
      return sysOauthClientDetailsService.updateClient(entity);
  }

  /**
   * 通过id删除
   * @param id
   * @return R
   */
  @DeleteMapping("/{id}")
  public ReturnDatas<Boolean> removeById(@PathVariable Long id){
    return ReturnDatas.ok(sysOauthClientDetailsService.removeById(id));
  }
}
