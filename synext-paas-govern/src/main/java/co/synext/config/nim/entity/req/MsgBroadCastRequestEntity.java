package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.INimBaseMsg;
import co.synext.config.nim.entity.NimBaseReqEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class MsgBroadCastRequestEntity extends NimBaseReqEntity {

    /**
     * @see https://dev.yunxin.163.com/docs/product/IM%E5%8D%B3%E6%97%B6%E9%80%9A%E8%AE%AF/%E6%9C%8D%E5%8A%A1%E7%AB%AFAPI%E6%96%87%E6%A1%A3/%E6%B6%88%E6%81%AF%E5%8A%9F%E8%83%BD?#发送自定义系统通知
     */

    private String from;
    private String isOffline;
    private String to;
    private Integer ttl;
    private INimBaseMsg body;
    private String targetOs;

}
