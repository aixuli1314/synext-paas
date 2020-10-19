package co.synext.config.nim.entity.req.message;

import co.synext.config.nim.entity.INimBaseMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件消息
 * </p>
 *
 * 格式:
 *
 * {
 *   "name":"BlizzardReg.ttf",    //文件名
 *   "md5":"79d62a35fa3d34c367b20c66afc2a500", //文件MD5
 *   "url":"http://nimtest.nos.netease.com/08c9859d-183f-4daa-9904-d6cacb51c95b", //文件URL
 *   "ext":"ttf",    //文件后缀类型
 *   "size":91680    //大小
 * }
 *
 * @author xu.ran
 * @since 2020-04-09
 */
@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class FileMsg  implements INimBaseMsg {

    public static final int TYPE = 6;

    private String name;
    private String md5;
    private String url;
    private String ext;
    private Long size;

}
