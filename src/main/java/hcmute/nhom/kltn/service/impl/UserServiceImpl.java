package hcmute.nhom.kltn.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hcmute.nhom.kltn.dto.UserDTO;
import hcmute.nhom.kltn.dto.UserProfileDTO;
import hcmute.nhom.kltn.exception.SystemErrorException;
import hcmute.nhom.kltn.mapper.UserMapper;
import hcmute.nhom.kltn.model.User;
import hcmute.nhom.kltn.repository.UserRepository;
import hcmute.nhom.kltn.service.UserProfileService;
import hcmute.nhom.kltn.service.UserService;

/**
 * Class UserServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractServiceImpl<UserRepository, UserMapper, UserDTO, User>
        implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String SERVICE = "UserService";

    private final UserProfileService userProfileService;

    private final UserRepository userRepository;

//    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findByUsername(String username) {
        String methodName = "findByUsername";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "username", username));
        try {
            User user = getRepository().findByUsername(username);
            if (Objects.isNull(user)) {
                logger.error("User not found");
                logger.info(getMessageEnd(SERVICE, methodName));
                throw new SystemErrorException("User not found");
            }
            UserDTO userDTO = getMapper().toDto(user, getCycleAvoidingMappingContext());
            logger.debug(getMessageOutputParam(SERVICE, "userDTO", userDTO));
            logger.info(getMessageEnd(SERVICE, methodName));
            return userDTO;
        } catch (Exception e) {
            logger.error("Error when find user by username", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when find user by username");
        }
    }

    @Override
    @Transactional
    public Boolean registerUser(UserDTO userDTO) {
        String methodName = "registerUser";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "userDTO", userDTO));
        try {
            User user = getRepository().findByEmail(userDTO.getEmail());
            if (Objects.nonNull(user)) {
                logger.error("User already exists");
                logger.info(getMessageEnd(SERVICE, methodName));
                return false;
            }
            // TODO: Add role
            userDTO.setActiveFlag(false);
            userDTO.setRemovalFlag(false);
            userDTO.setCreatedBy(userDTO.getEmail());
            userDTO.setModifiedBy(userDTO.getEmail());
            save(userDTO);
            logger.info(getMessageOutputParam(SERVICE, "result", true));
            logger.info(getMessageEnd(SERVICE, methodName));
            return true;
        } catch (Exception e) {
            logger.error("Error when register user", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when register user");
        }
    }

    @Override
    public UserDTO findByEmail(String email) {
        String methodName = "findByEmail";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "email", email));
        try {
            UserDTO userDTO = getMapper().toDto(getRepository().findByEmail(email), getCycleAvoidingMappingContext());
            if (Objects.isNull(userDTO)) {
                logger.debug(getMessageOutputParam(SERVICE, "userDTO", null));
                logger.info(getMessageEnd(SERVICE, methodName));
                return null;
            }
            logger.debug(getMessageOutputParam(SERVICE, "userDTO", userDTO));
            logger.info(getMessageEnd(SERVICE, methodName));
            return userDTO;
        } catch (Exception e) {
            logger.error("Error when find user by email", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when find user by email");
        }
    }

    @Override
    @Transactional
    public Boolean saveUserProfile(UserDTO userDTO, String email) {
        String methodName = "saveUserProfile";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "userProfileDTO", userDTO.getUserProfile()));
        logger.debug(getMessageInputParam(SERVICE, "email", email));
        try {
            UserDTO user = findByEmail(email);
            if (Objects.isNull(user)) {
                logger.error("User not found");
                logger.info(getMessageEnd(SERVICE, methodName));
                return false;
            }
            UserProfileDTO userProfileDTO = userProfileService.save(userDTO.getUserProfile());
            user.setUserProfile(userProfileDTO);
            save(user);
            logger.debug(getMessageOutputParam(SERVICE, "result", true));
            logger.info(getMessageEnd(SERVICE, methodName));
            return true;
        } catch (Exception e) {
            logger.error("Error when save user profile", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when save user profile");
        }
    }

    @Override
    @Transactional
    public Boolean activeUser(String email) {
        String methodName = "activeUser";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "email", email));
        try {
            UserDTO user = findByEmail(email);
            if (Objects.isNull(user)) {
                logger.error("User not found");
                logger.info(getMessageEnd(SERVICE, methodName));
                return false;
            }
            user.setActiveFlag(true);
            save(user);
            logger.debug(getMessageOutputParam(SERVICE, "result", true));
            logger.info(getMessageEnd(SERVICE, methodName));
            return true;
        } catch (Exception e) {
            logger.error("Error when active user", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when active user");
        }
    }

    @Override
    public Page<UserDTO> searchUser(String keyword, int pageNo, int pageSize, String sortBy, String sortDir) {
        String methodName = "searchUser";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "keyword", keyword));
        logger.debug(getMessageInputParam(SERVICE, "pageNo", pageNo));
        logger.debug(getMessageInputParam(SERVICE, "pageSize", pageSize));
        logger.debug(getMessageInputParam(SERVICE, "sortBy", sortBy));
        logger.debug(getMessageInputParam(SERVICE, "sortDir", sortDir));
        try {
            List<User> list = getRepository().searchUser(keyword);
            List<UserDTO> listDTO = list.stream()
                    .map(item -> getMapper().toDto(item, getCycleAvoidingMappingContext())).collect(Collectors.toList());
            Pageable pageRequest = createPageRequestUsing(pageNo, pageSize);
            logger.debug(getMessageOutputParam(SERVICE, "pageDTO", listDTO));
            logger.info(getMessageEnd(SERVICE, methodName));
            return new PageImpl<>(listDTO, pageRequest, listDTO .size());
        } catch (Exception e) {
            logger.error("Error when search user", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when search user");
        }
    }

    // Todo: Move to Utilities
    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }

    @Override
    public List<UserDTO> getAllUser() {
        String methodName = "getAllUser";
        logger.info(getMessageStart(SERVICE, methodName));
        try {
            List<User> list = getRepository().findAll();
            List<UserDTO> listDTO = list.stream()
                    .map(item -> getMapper().toDto(item, getCycleAvoidingMappingContext())).collect(Collectors.toList());
            logger.debug(getMessageOutputParam(SERVICE, "listDTO", listDTO));
            logger.info(getMessageEnd(SERVICE, methodName));
            return listDTO;
        } catch (Exception e) {
            logger.error("Error when get all user", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when get all user");
        }
    }

    @Override
    @Transactional
    public Boolean updateAvatar(UserDTO userDTO, String email) {
        String methodName = "updateAvatar";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "avatar", userDTO.getUserProfile().getAvatar().getFileName()));
        logger.debug(getMessageInputParam(SERVICE, "email", email));
        try {
            if (Objects.nonNull(userDTO.getUserProfile().getAvatar())) {
                UserDTO user = findByEmail(email);
                if (Objects.isNull(user)) {
                    logger.error("User not found");
                    logger.info(getMessageEnd(SERVICE, methodName));
                    return false;
                }
                UserProfileDTO userProfileDTO = userProfileService.save(userDTO.getUserProfile());
                user.setUserProfile(userProfileDTO);
                save(user);
                logger.debug(getMessageOutputParam(SERVICE, "result", true));
                logger.info(getMessageEnd(SERVICE, methodName));
                return true;
            } else {
                logger.error("Avatar is null");
                logger.info(getMessageEnd(SERVICE, methodName));
                throw new SystemErrorException("Avatar is null");
            }
        } catch (Exception e) {
            logger.error("Error when update avatar", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when update avatar");
        }
    }

    @Override
    public Boolean deleteAvatar(String email) {
        String methodName = "deleteAvatar";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "email", email));
        try {
            UserDTO user = findByEmail(email);
            if (Objects.isNull(user)) {
                logger.error("User not found");
                logger.info(getMessageEnd(SERVICE, methodName));
                return false;
            }
            user.getUserProfile().setAvatar(null);
            save(user);
            logger.debug(getMessageOutputParam(SERVICE, "result", true));
            logger.info(getMessageEnd(SERVICE, methodName));
            return true;
        } catch (Exception e) {
            logger.error("Error when delete avatar", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when delete avatar");
        }
    }

    @Override
    public Boolean checkActiveUser(String email) {
        String methodName = "checkActiveUser";
        logger.info(getMessageStart(SERVICE, methodName));
        logger.debug(getMessageInputParam(SERVICE, "email", email));
        try {
            UserDTO user = findByEmail(email);
            if (Objects.isNull(user)) {
                logger.error("User not found");
                logger.info(getMessageEnd(SERVICE, methodName));
                return false;
            }
            logger.debug(getMessageOutputParam(SERVICE, "result", user.getActiveFlag()));
            logger.info(getMessageEnd(SERVICE, methodName));
            return user.getActiveFlag();
        } catch (Exception e) {
            logger.error("Error when check active user", e);
            logger.info(getMessageEnd(SERVICE, methodName));
            throw new SystemErrorException("Error when check active user");
        }
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public UserMapper getMapper() {
        return UserMapper.INSTANCE;
    }
}
