package ru.ftc.android.shifttemple.features.login.data.user;

import java.util.List;

import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public interface UserDataSource {

    void getUsers(Carry<List<User>> carry);

    void getUser(String id, Carry<User> carry);

}