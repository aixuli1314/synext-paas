package co.synext.config.nim.entity.req.message;

import co.synext.config.nim.entity.INimBaseMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * <p>
 * 视频消息
 * </p>
 *
 * 格式:
 *
 *{
 *   "dur":8003,        //视频持续时长ms
 *   "md5":"da2cef3e5663ee9c3547ef5d127f7e3e",    //视频文件的md5值
 *   "url":"http://nimtest.nos.netease.com/21f34447-e9ac-4871-91ad-d9f03af20412",    //生成的url
 *   "w":360,    //宽
 *   "h":480,    //高
 *   "ext":"mp4",    //视频格式
 *   "size":16420    //视频文件大小
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
public class VideoMsg  implements INimBaseMsg {

    public static final int TYPE = 3;

    private Integer dur;
    private String md5;
    private String url;
    private Integer w;
    private Integer h;
    private String ext;
    private Long size;

}
