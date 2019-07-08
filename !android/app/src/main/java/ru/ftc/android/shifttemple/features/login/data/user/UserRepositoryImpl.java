package ru.ftc.android.shifttemple.features.login.data.user;

import java.util.List;

import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public final class UserRepositoryImpl implements UserRepository {

    private final UserDataSource dataSource;

    public UserRepositoryImpl(final UserDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadUsers(Carry<List<User>> carry) {
        dataSource.getUsers(carry);
    }

    @Override
    public void loadUser(String id, Carry<User> carry) {
        dataSource.getUser(id, carry);
    }

}