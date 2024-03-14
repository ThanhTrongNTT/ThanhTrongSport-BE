package hcmute.nhom.kltn.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import hcmute.nhom.kltn.dto.UserProfileDTO;
import hcmute.nhom.kltn.mapper.UserProfileMapper;
import hcmute.nhom.kltn.model.UserProfile;
import hcmute.nhom.kltn.repository.UserProfileRepository;
import hcmute.nhom.kltn.service.UserProfileService;

/**
 * Class UserProfileServiceImpl.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl
        extends AbstractServiceImpl<UserProfileRepository, UserProfileMapper, UserProfileDTO, UserProfile>
        implements UserProfileService {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);
    private static final String SERVICE = "UserProfileService";
}
