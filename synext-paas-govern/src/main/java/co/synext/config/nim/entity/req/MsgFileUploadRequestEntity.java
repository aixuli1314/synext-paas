package co.synext.config.nim.entity.req;

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
public class MsgFileUploadRequestEntity extends NimBaseReqEntity {

    private Byte[] content;
    private String type;
    private String ishttps;
    private Integer expireSec;
    private String tag;

}
