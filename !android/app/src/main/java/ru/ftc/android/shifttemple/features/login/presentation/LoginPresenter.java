package ru.ftc.android.shifttemple.features.login.presentation;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.login.domain.SessionInteractor;
import ru.ftc.android.shifttemple.features.login.domain.UserInteractor;
import ru.ftc.android.shifttemple.features.login.domain.model.User;
import ru.ftc.android.shifttemple.network.Carry;

public final class LoginPresenter extends MvpPresenter<LoginView> {

    private final UserInteractor userInteractor;
    private final SessionInteractor sessionInteractor;

    LoginPresenter(UserInteractor userInteractor, SessionInteractor sessionInteractor) {
        this.userInteractor = userInteractor;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    protected void onViewReady() {
        loadUsers();
    }

    private void loadUsers() {
        view.showProgress();
        userInteractor.loadUsers(new Carry<List<User>>() {

            @Override
            public void onSuccess(List<User> result) {
                view.showUserList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    void onNavigateNextClick(User selectedUser) {
        if (selectedUser == null) {
            view.showNotSelectedUserError();
        } else {
            sessionInteractor.setUserAge(selectedUser.getAge());
            sessionInteractor.setUserBalance(selectedUser.getBalance());
            sessionInteractor.setUserCity(selectedUser.getCity());
            sessionInteractor.setUserDescription(selectedUser.getDescription());
            sessionInteractor.setUserId(selectedUser.getId());
            sessionInteractor.setUserName(selectedUser.getName());
            sessionInteractor.setUserPhone(selectedUser.getPhoneNumber());
            sessionInteractor.setUserPicUrl(selectedUser.getPicUrl());
            sessionInteractor.setUserStatus(selectedUser.getStatus());
            view.openCardListScreen();
        }
    }
}