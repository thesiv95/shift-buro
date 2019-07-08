package ru.ftc.android.shifttemple.features.login.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.login.domain.model.User;

public interface LoginView extends MvpView {

    void showProgress();

    void hideProgress();

    void showUserList(List<User> userList);

    void openCardListScreen();

    void showNotSelectedUserError();

    void showError(String message);
}