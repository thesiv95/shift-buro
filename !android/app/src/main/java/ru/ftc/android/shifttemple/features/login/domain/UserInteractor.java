package ru.ftc.android.shifttemple.features.login.domain;

import java.util.List;

import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public interface UserInteractor {

    void loadUsers(Carry<List<User>> carry);

    void loadUser(String id, Carry<User> carry);

}