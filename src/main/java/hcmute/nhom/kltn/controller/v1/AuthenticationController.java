package hcmute.nhom.kltn.controller.v1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hcmute.nhom.kltn.common.payload.ApiResponse;
import hcmute.nhom.kltn.common.payload.JwtAuthenticationResponse;
import hcmute.nhom.kltn.common.payload.LoginRequest;
import hcmute.nhom.kltn.dto.UserDTO;
import hcmute.nhom.kltn.security.jwt.JwtProvider;
import hcmute.nhom.kltn.service.UserService;
import hcmute.nhom.kltn.service.session.SessionManagementService;
import hcmute.nhom.kltn.util.Constants;
import hcmute.nhom.kltn.util.SessionConstants;
import hcmute.nhom.kltn.util.Utilities;

/**
 * Class AuthenticationController.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@RestController
@RequiredArgsConstructor
public class AuthenticationController extends AbstractController{
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final SessionManagementService sessionService;

    /**
     * Register User.
     * @return ResponseEntity<?>
     */
    @PostMapping("auth/register")
    public ResponseEntity<ApiResponse<Boolean>> registerUser(
            HttpServletRequest httpServletRequest,
            @RequestBody UserDTO userDTO
    ) {
        String messageStart = getMessageStart(httpServletRequest.getRequestURL().toString(), "registerUser");
        String messageEnd = getMessageEnd(httpServletRequest.getRequestURL().toString(), "registerUser");
        logger.info("{}", messageStart);
        // Execute RegisterUser
        Boolean result = userService.registerUser(userDTO);
        logger.info("{}", messageEnd);
        return ResponseEntity.ok().body(new ApiResponse<>(result, "User registered successfully"));
    }

    /**
     * Login User.
     *
     * @param httpServletRequest HttpServletRequest
     * @param loginRequest       LoginRequest
     * @return ResponseEntity<?>
     */
    @PostMapping("auth/login")
    public ResponseEntity<ApiResponse<JwtAuthenticationResponse>> login(
            HttpServletRequest httpServletRequest,
            HttpSession session,
            @Validated @RequestBody LoginRequest loginRequest) {
        String messageStart = getMessageStart(httpServletRequest.getRequestURL().toString(), "loginUser");
        String messageEnd = getMessageEnd(httpServletRequest.getRequestURL().toString(), "loginUser");
        logger.info("{}", messageStart);
        try {
            if (session != null) {
                session.invalidate();
            }
            sessionService.setAttribute(SessionConstants.IP_ADDRESS, Utilities.getIpAdressByHeader(httpServletRequest));
            // Execute Login
            UserDTO userDTO = userService.findByEmail(loginRequest.getEmail());
            if (userDTO == null) {
                logger.info("{}", messageEnd);
                return new ResponseEntity<>(new ApiResponse<>(null, "Email not found"),
                        HttpStatus.BAD_REQUEST);
            }
            if (!passwordEncoder.matches(loginRequest.getPassword(), userDTO.getPassword())) {
                logger.info("{}", messageEnd);
                return new ResponseEntity<>(new ApiResponse<>(null, "The password was wrong!"), HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            sessionService.setAttribute(SessionConstants.USER_ID, userDTO.getId());
            sessionService.setAttribute(SessionConstants.USER_NAME, userDTO.getUserName());
            sessionService.setAttribute(SessionConstants.USER_EMAIL, userDTO.getEmail());
            sessionService.setAttribute(SessionConstants.LOGGED_IN, Constants.STAGE_LOGIN_SUCCESS);
            logger.info("Creating Json Web Token!!");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("{}", messageEnd);
            return ResponseEntity.ok().body(new ApiResponse<>(jwtProvider.createToken(authentication),
                    "Login successfully!!"));
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            return new ResponseEntity<>(new ApiResponse<>(false, null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
