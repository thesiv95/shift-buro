package ru.ftc.android.shifttemple.features.cards.presentation.CardCreate;

import android.view.View;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.cards.domain.CardsInteractor;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.login.domain.SessionInteractor;
import ru.ftc.android.shifttemple.network.Carry;

final class CardCreatePresenter extends MvpPresenter<CardCreateView> {
    private final CardsInteractor interactor;
    private final SessionInteractor sessionInteractor;

    CardCreatePresenter(CardsInteractor interactor, SessionInteractor sessionInteractor) {
        this.interactor = interactor;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    protected void onViewReady() {
        //
    }

    public void onCreateCardClicked(Card card) {
        interactor.createCard(card, new Carry<Card>() {

            @Override
            public void onSuccess(Card result) {
                view.hideProgress();
                view.showSuccess("Добвалено");
                view.finishActivity();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    protected Integer getUserId() {
        return sessionInteractor.getUserId();
    }

    public void finishActivity() {
        view.finishActivity();
    }

}
