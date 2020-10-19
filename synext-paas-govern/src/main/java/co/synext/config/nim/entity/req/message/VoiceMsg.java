package co.synext.config.nim.entity.req.message;

import co.synext.config.nim.entity.INimBaseMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 语音消息
 * </p>
 *
 * 格式:
 *
 *{
 *   "dur":4551,        //语音持续时长ms
 *   "md5":"87b94a090dec5c58f242b7132a530a01",    //语音文件的md5值
 *   "url":"http://nimtest.nos.netease.com/a2583322-413d-4653-9a70-9cabdfc7f5f9",    //生成的url
 *   "ext":"aac",        //语音消息格式，只能是aac格式
 *   "size":16420        //语音文件大小
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
public class VoiceMsg  implements INimBaseMsg {

    public static final int TYPE = 2;

    private Integer dur;
    private String md5;
    private String url;
    private String ext;
    private Long size;
}
