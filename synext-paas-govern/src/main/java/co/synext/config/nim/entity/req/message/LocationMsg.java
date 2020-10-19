package co.synext.config.nim.entity.req.message;

import co.synext.config.nim.entity.INimBaseMsg;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地理位置消息
 * </p>
 *
 * 格式:
 *
 *{
 *   "title":"中国 浙江省 杭州市 网商路 599号",    //地理位置title
 *   "lng":120.1908686708565,        // 经度
 *   "lat":30.18704515647036            // 纬度
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
public class LocationMsg  implements INimBaseMsg {

    public static final int TYPE = 4;

    private String title;
    private Double lng;
    private Double lat;

}
