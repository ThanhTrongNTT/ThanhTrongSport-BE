package hcmute.nhom.kltn.security.oauth2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Class OAuth2AuthenticationFailureHandler.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // Log lỗi xác thực
        logger.error("OAuth2 authentication failed: " + exception.getMessage());

        // Đặt mã phản hồi HTTP là 401 (Unauthorized)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Thiết lập Content-Type cho phản hồi JSON
        response.setContentType("application/json;charset=UTF-8");

        // Gửi phản hồi JSON với thông báo lỗi
        response.getWriter().write("{\"error\": \"Authentication failed\", \"message\": \"" + exception.getMessage() + "\"}");
    }
}
