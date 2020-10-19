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
 *   "name":"图片发送于2015-05-07 13:59",   //图片name
 *   "md5":"9894907e4ad9de4678091277509361f7",    //图片文件md5
 *   "url":"http://nimtest.nos.netease.com/cbc500e8-e19c-4b0f-834b-c32d4dc1075e",    //生成的url
 *   "ext":"jpg",    //图片后缀
 *   "w":6814,    //宽
 *   "h":2332,    //高
 *   "size":388245    //图片大小
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
public class ImageMsg  implements INimBaseMsg {

    public static final int TYPE = 1;

    private String name;
    private String md5;
    private String url;
    private String ext;
    private Integer w;
    private Integer h;
    private Long size;

}
