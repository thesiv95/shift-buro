package ru.ftc.android.shifttemple.features.cards.presentation.CardList;

import java.util.List;

import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.cards.domain.CardsInteractor;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.domain.model.Success;
import ru.ftc.android.shifttemple.features.login.domain.SessionInteractor;
import ru.ftc.android.shifttemple.network.Carry;

final class CardListPresenter extends MvpPresenter<CardListView> {

    private final CardsInteractor interactor;
    private final SessionInteractor sessionInteractor;

    CardListPresenter(CardsInteractor interactor, SessionInteractor sessionInteractor) {
        this.interactor = interactor;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    protected void onViewReady() {
        loadCards();
    }

    String getUserImageUrl() {
        return sessionInteractor.getUserPicUrl();
    }

    Integer getUserBalance() {
        return sessionInteractor.getUserBalance();
    }

    Integer getUserId() {return  sessionInteractor.getUserId();}

    private void loadCards() {
        view.showProgress();

        int spinnerPosition = view.getSpinnerItemPosition();
        onSpinnerItemSelected(spinnerPosition);
    }

    void onSpinnerItemSelected(int position) {
        view.showProgress();
        switch (position) {
            case 0:
                loadAllCard();
                break;
            case 1:
                loadRequestCards();
                break;
            case 2:
                loadInvitationCards();
                break;
            default:
                view.hideProgress();
                break;
        }
    }

    private void loadAllCard() {
        interactor.loadCards(new Carry<List<Card>>() {
            @Override
            public void onSuccess(List<Card> result) {
                view.showCardList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private void loadRequestCards() {
        interactor.loadRequestCards(new Carry<List<Card>>() {
            @Override
            public void onSuccess(List<Card> result) {
                view.showCardList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private void loadInvitationCards() {
        interactor.loadInvitationCards(new Carry<List<Card>>() {
            @Override
            public void onSuccess(List<Card> result) {
                view.showCardList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private void updateCardStatus(Card card, Integer userId) {
        interactor.updateCardStatus(card.getId(), userId, new Carry<Void>() {
            @Override
            public void onSuccess(Void v) {
                loadCards();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

    void onAcceptButtonClicked(Card card) {
        Integer userId = getUserId();
        updateCardStatus(card, userId);
    }

    void onAddTaskClicked() {
        view.openCardCreateScreen();
    }

    void onCardSelected(Card card) {
        //Your code here
    }

    void onCardLongClicked(Card card) {
        //Your code here
    }

}