package co.synext.module.system.vo;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/***
 *   {
 *     path: '/devops',
 *     component: Layout,
 *     redirect: '/codegen/index',
 *     name: '开发运维',
 *     alwaysShow: true,
 *     meta: { title: '开发运维', icon: 'database-et-fill', noCache: true },
 *     children: [
 *       {
 *         path: 'codegen',
 *         name: '代码生成',
 *         component: () => import('@/views/codegen/index'),
 *         meta: { title: '代码生成', icon: 'star', noCache: true }
 *       }
 *     ]
 *   }
 */
@Data
@ToString
public class RouteVo {

    private String path;
    private Object component;
    private String redirect;
    private String name;
    private Boolean alwaysShow;
    private Boolean hidden;
    private Meta meta;
    private List<RouteVo> children;

    @Data
    @ToString
    public static class Meta{
        private String title;
        private String icon;
        private Boolean noCache;
    }

}
