package ru.ftc.android.shifttemple.features.cards.presentation.CardCreate;

import android.widget.EditText;
import android.widget.Spinner;

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
        //Your code here
    }

    public void onCreateCardClicked(Card card) {
        interactor.createCard(card, new Carry<Void>() {

            @Override
            public void onSuccess(Void v) {
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

    protected Card getCard(EditText ownerNameText, Spinner spinnerStatus, EditText taskText, EditText descriptionText,
                               EditText priceText, EditText cityText, EditText phoneText) {

        if (!cardInfoIsCorrect(ownerNameText, taskText, descriptionText, priceText, cityText, phoneText)) {
            view.showError("Заполните все поля");
            Card card = null;
            return card;
        }

        Integer ownerId = sessionInteractor.getUserId();
        String ownerName = ownerNameText.getText().toString();
        String type = spinnerStatus.getSelectedItem().toString();
        String task = taskText.getText().toString();
        String description = descriptionText.getText().toString();
        boolean status = true;
        Integer price = Integer.valueOf(priceText.getText().toString());
        String city = cityText.getText().toString();
        String phone = phoneText.getText().toString();

        Card card = new Card(ownerId,ownerName,type, task, description, status,
                price, city, phone);

        return card;
    }

    private boolean cardInfoIsCorrect(EditText ownerNameText, EditText taskText, EditText descriptionText,
                          EditText priceText, EditText cityText, EditText phoneText) {
        if (ownerNameText.getText().length() == 0 || taskText.getText().length() == 0 ||
            descriptionText.getText().length() == 0 || priceText.getText().length() == 0 ||
            cityText.getText().length() == 0 || phoneText.getText().length() == 0){
            return false;
        }
        else {
            return true;
        }
    }

    public void finishActivity() {
        view.finishActivity();
    }

}
