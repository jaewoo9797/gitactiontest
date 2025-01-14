package com.sprint.mission.discodeit.service.user;

import com.sprint.mission.discodeit.db.user.UserRepository;
import com.sprint.mission.discodeit.db.user.UserRepositoryImpl;
import com.sprint.mission.discodeit.entity.user.dto.FindUserRequest;
import com.sprint.mission.discodeit.entity.user.dto.ModifyUserInfoRequest;
import com.sprint.mission.discodeit.entity.user.dto.RegisterUserRequest;
import com.sprint.mission.discodeit.entity.user.dto.UnregisterUserRequest;
import com.sprint.mission.discodeit.entity.user.dto.UserInfoResponse;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

public interface UserService {

    UserInfoResponse register(RegisterUserRequest registerUserRequest);

    UserInfoResponse findUserByUsername(FindUserRequest findUserRequest);

    UserInfoResponse modifyUserInfo(ModifyUserInfoRequest request);

    void UnRegisterUser(UnregisterUserRequest request);

    void createChannel();

    public static UserService getJCFUserService(UserRepository userRepository) {
        return JCFUserService.getInstance(userRepository);
    }

    public static UserService getUserServiceInstance(UserRepository userRepository) {

        if (userRepository instanceof UserRepositoryImpl) {
            return JCFUserService.getInstance(userRepository);
        }

        return null;
    }
}
