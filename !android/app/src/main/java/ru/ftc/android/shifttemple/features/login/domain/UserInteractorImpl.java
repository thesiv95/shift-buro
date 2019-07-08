package ru.ftc.android.shifttemple.features.login.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.login.data.user.UserRepository;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public final class UserInteractorImpl implements UserInteractor {

    private final UserRepository userRepository;

    public UserInteractorImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void loadUsers(Carry<List<User>> carry) {
        userRepository.loadUsers(carry);
    }

    @Override
    public void loadUser(String id, Carry<User> carry) {
        userRepository.loadUser(id, carry);
    }

}