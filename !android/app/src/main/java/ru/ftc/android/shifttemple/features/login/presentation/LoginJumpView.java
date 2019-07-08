package ru.ftc.android.shifttemple.features.login.presentation;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.login.domain.model.User;

public interface LoginJumpView extends MvpView {
    void onItemClick(User user);
}
