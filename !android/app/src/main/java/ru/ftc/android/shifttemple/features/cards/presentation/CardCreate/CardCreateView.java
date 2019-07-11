package ru.ftc.android.shifttemple.features.cards.presentation.CardCreate;

import ru.ftc.android.shifttemple.features.MvpView;

public interface CardCreateView extends MvpView {

    void showProgress();

    void hideProgress();

    void addCard();

    void showError(String message);

    void showSuccess(String message);

    void finishActivity();
}
