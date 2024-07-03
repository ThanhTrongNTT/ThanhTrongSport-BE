// file: src/main/hcmute/nhom/kltn

package hcmute.nhom.kltn;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import hcmute.nhom.kltn.dto.MediaFileDTO;
import hcmute.nhom.kltn.dto.RoleDTO;
import hcmute.nhom.kltn.dto.UserDTO;
import hcmute.nhom.kltn.dto.UserProfileDTO;
import hcmute.nhom.kltn.enums.RoleName;
import hcmute.nhom.kltn.service.MediaFileService;
import hcmute.nhom.kltn.service.RoleService;
import hcmute.nhom.kltn.service.UserProfileService;
import hcmute.nhom.kltn.service.UserService;
import hcmute.nhom.kltn.util.Constants;

/**
 * Class KLTNBEApplication.<br>
 */
@SpringBootApplication
public class KLTNBEApplication {

    public static void main(String[] args) {
        SpringApplication.run(KLTNBEApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner runner(
            UserService userService, PasswordEncoder passwordEncoder, RoleService roleService,
            UserProfileService userProfileService, MediaFileService mediaFileService) {
        return args -> {
            MediaFileDTO mediaFile = mediaFileService.findByFileName("default-avatar.png");
            if (Objects.isNull(mediaFile)) {
                String fileName = "default-avatar.png";
                mediaFile = new MediaFileDTO();
                mediaFile.setFileName(fileName);
                mediaFile.setFileType(fileName.substring(fileName.lastIndexOf(".")));
                mediaFile.setUrl(Constants.DEFAULT_AVATAR);
                mediaFile.setRemovalFlag(false);
                mediaFile = mediaFileService.save(mediaFile);
            }

            RoleDTO roleUser = roleService.findByRoleName(RoleName.USER.name());
            if (Objects.isNull(roleUser)) {
                roleUser = new RoleDTO();
                roleUser.setRoleName(RoleName.USER.name());
                roleUser.setRemovalFlag(false);
                roleService.save(roleUser);
            }
            RoleDTO roleAdmin = roleService.findByRoleName(RoleName.ADMIN.name());
            if (Objects.isNull(roleAdmin)) {
                roleAdmin = new RoleDTO();
                roleAdmin.setRoleName(RoleName.ADMIN.name());
                roleAdmin.setRemovalFlag(false);
                roleAdmin = roleService.save(roleAdmin);
            }
            UserDTO admin = userService.findByEmail("admin@store.com");
            if (Objects.isNull(admin)) {
                admin = new UserDTO();
                UserProfileDTO userProfile = userProfileService.findProfileByEmail("admin@store.com");
                if (Objects.isNull(userProfile)) {
                    userProfile = new UserProfileDTO();
                    userProfile.setFirstName("Nguyen");
                    userProfile.setLastName("Thanh Trong");
                    userProfile.setAvatar(mediaFile);
                    userProfile.setRemovalFlag(false);
                    userProfile = userProfileService.save(userProfile);
                }
                admin.setUserProfile(userProfile);
                admin.setUserName("admin");
                admin.setEmail("admin@store.com");
                admin.setActiveFlag(true);
                admin.setPassword(passwordEncoder.encode("Store@@admin"));
                Set<RoleDTO> roles = new HashSet<>();
                roles.add(roleAdmin);
                roles.add(roleUser);
                admin.setRoles(roles);
                admin.setRemovalFlag(false);
                userService.save(admin);
            }
        };
    }
}
