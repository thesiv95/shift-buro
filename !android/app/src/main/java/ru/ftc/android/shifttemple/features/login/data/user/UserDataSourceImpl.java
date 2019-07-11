package ru.ftc.android.shifttemple.features.login.data.user;

import java.util.List;

import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;
import ru.ftc.android.shifttemple.network.DefaultCallback;

public final class UserDataSourceImpl implements UserDataSource {

    private final UserApi usersApi;

    public UserDataSourceImpl(UserApi usersApi) {
        this.usersApi = usersApi;
    }

    @Override
    public void getUsers(Carry<List<User>> carry) {
        usersApi.getAllUsers().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getUser(String id, Carry<User> carry) {
        usersApi.getUser(id).enqueue(new DefaultCallback(carry));
    }
}