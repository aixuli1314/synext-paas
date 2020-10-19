package co.synext.config.security.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import co.synext.common.base.vo.UserVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xu.ran
 * @date 2020/4/21 18:02
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class LoginUser extends UserVo implements UserDetails {

    private static final long serialVersionUID = 4125096758372084669L;

    @ApiModelProperty(value = "部门集合")
    private Set<Long> depts;

    @ApiModelProperty(value = "角色集合")
    private Set<String> roles;

    @ApiModelProperty(value = "权限集合")
    private Set<String> permissions;
    @ApiModelProperty(value = "token令牌")
    private String token;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.parallelStream().forEach(role ->collection.add(new SimpleGrantedAuthority("ROLE_" + role)));
        }

        if (!CollectionUtils.isEmpty(permissions)) {
            permissions.parallelStream().forEach(permission -> collection.add(new SimpleGrantedAuthority(permission)));
        }
        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       return getActive()==1;
    }

	@Override
	public String getUsername() {
		return getAccount();
	}

}
