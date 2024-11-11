package hcmute.nhom.kltn.security.oauth2.dto;

import java.util.Map;

/**
 * Class OAuth2User.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
public abstract class OAuth2UserDetail {
    protected Map<String, Object> attributes;

    public OAuth2UserDetail(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    public abstract String getEmail();

    public abstract String getName();
}
