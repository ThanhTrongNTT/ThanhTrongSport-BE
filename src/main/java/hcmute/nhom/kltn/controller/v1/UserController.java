package hcmute.nhom.kltn.controller.v1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hcmute.nhom.kltn.common.payload.ApiResponse;
import hcmute.nhom.kltn.common.payload.ChangePasswordRequest;
import hcmute.nhom.kltn.dto.UserDTO;
import hcmute.nhom.kltn.service.UserService;
import hcmute.nhom.kltn.util.Constants;

/**
 * Class UserController.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@RestController
@RequiredArgsConstructor
public class UserController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<Page<UserDTO>>> getAllUser(
            HttpServletRequest request,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getAllUser"));
        Page<UserDTO> userDTOPage = userService.getPaging(pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getAllUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, userDTOPage, "Get all user successfully!"));
    }

    @GetMapping("/users/search-by-name")
    public ResponseEntity<ApiResponse<Page<UserDTO>>> searchUser(
            HttpServletRequest request,
            @RequestParam("keyword") String keyword,
            @RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false)
            int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.DEFAULT_SORT_BY, required = false)
            String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.DEFAULT_SORT_DIRECTION, required = false)
            String sortDir
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "searchUser"));
        Page<UserDTO> userDTOPage = userService.searchUser(keyword, pageNo, pageSize, sortBy, sortDir);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "searchUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, userDTOPage, "Search user successfully!"));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getUserById"));
        UserDTO userDTO = userService.findById(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getUserById"));
        return ResponseEntity.ok(new ApiResponse<>(true, userDTO, "Get user by id successfully!"));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail(
            HttpServletRequest request,
            @PathVariable("email") String email
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "getUserByEmail"));
        UserDTO userDTO = userService.findByEmail(email);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "getUserByEmail"));
        return ResponseEntity.ok(new ApiResponse<>(true, userDTO, "Get user by email successfully!"));
    }

    @PutMapping("/user/{email}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(
            HttpServletRequest request,
            HttpSession session,
            @RequestBody UserDTO userDTO
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "updateUser"));
        String userEmail = (String) session.getAttribute("email");
        UserDTO result = userService.updateUserProfile(userEmail, userDTO);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "updateUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, result, "Update user successfully!"));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> deleteUser(
            HttpServletRequest request,
            @PathVariable("id") String id
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "deleteUser"));
        userService.delete(id);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "deleteUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, null, "Delete user successfully!"));
    }

    @PostMapping("/user/change-password")
    public ResponseEntity<ApiResponse<Boolean>> changePassword(
            HttpServletRequest request,
            @RequestBody ChangePasswordRequest changePasswordRequest
            ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "changePassword"));
        Boolean result = userService.changePassword(changePasswordRequest);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "changePassword"));
        return ResponseEntity.ok(new ApiResponse<>(true, result, "Change password successfully!"));
    }

    @PostMapping("/user/active/{email}")
    public ResponseEntity<ApiResponse<Boolean>> activeUser(
            HttpServletRequest request,
            @PathVariable("email") String email
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "activeUser"));
        Boolean result = userService.activeUser(email);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "activeUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, result, "Active user successfully!"));
    }

    @PostMapping("/user/deactive/{email}")
    public ResponseEntity<ApiResponse<Boolean>> deactiveUser(
            HttpServletRequest request,
            @PathVariable("email") String email
    ) {
        logger.info(getMessageStart(request.getRequestURL().toString(), "activeUser"));
        Boolean result = userService.deactiveUser(email);
        logger.info(getMessageEnd(request.getRequestURL().toString(), "activeUser"));
        return ResponseEntity.ok(new ApiResponse<>(true, result, "Active user successfully!"));
    }
}
