package ru.ftc.android.shifttemple.features.cards.presentation.CardCreate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;


public class CardCreateActivity extends BaseActivity implements CardCreateView{

    private CardCreatePresenter presenter;
    private ProgressBar progressBar;
    private Button okButton;
    private Button cancelButton;
    private EditText ownerNameText;
    private EditText taskText;
    private EditText descriptionText;
    private Spinner spinnerStatus;
    private EditText priceText;
    private EditText cityText;
    private EditText phoneText;

    public static void start(final Context context) {
        Intent intent = new Intent(context, CardCreateActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_create_activity);

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.progressBar);
        okButton = findViewById(R.id.okButton);
        cancelButton = findViewById(R.id.cancelButton);
        ownerNameText = findViewById(R.id.name_text_edit);
        spinnerStatus = findViewById(R.id.spinner);
        taskText = findViewById(R.id.name_text_edit);
        descriptionText = findViewById(R.id.description_text_edit);
        priceText = findViewById(R.id.cost_text_edit);
        cityText = findViewById(R.id.city_text_edit);
        phoneText = findViewById(R.id.phone_text_edit);

        hideProgress();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.finishActivity();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCard();
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void addCard() {
        showProgress();
        Card card = presenter.getCard(ownerNameText, spinnerStatus, taskText, descriptionText,
                priceText, cityText, phoneText);
        if (card != null) {
            presenter.onCreateCardClicked(card);
        }
        else
            hideProgress();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    protected MvpPresenter<CardCreateView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
