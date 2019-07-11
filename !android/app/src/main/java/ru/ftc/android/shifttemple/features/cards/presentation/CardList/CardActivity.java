package ru.ftc.android.shifttemple.features.cards.presentation.CardList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.Image.CircleImageTransform;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.cards.domain.model.Card;
import ru.ftc.android.shifttemple.features.cards.presentation.CardCreate.CardCreateActivity;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:49
 */

public class CardActivity extends BaseActivity implements CardListView {

    public static void start(final Context context) {
        Intent intent = new Intent(context, CardActivity.class);
        context.startActivity(intent);
    }

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ImageView createBookButton;
    private ImageView imageView;
    private CardAdapter adapter;
    private TextView textView;
    private Spinner spinner;

    protected CardListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        //Hide the title bar.
        getSupportActionBar().hide();

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.books_progress);
        recyclerView = findViewById(R.id.books_recycle_view);
        createBookButton = findViewById(R.id.addTask);
        imageView = findViewById(R.id.user_image_icon);
        textView = findViewById(R.id.userBalance);
        spinner = findViewById(R.id.spinnerCategory);

        //Load user image into ImageView
        Picasso.get().load(presenter.getUserImageUrl()).transform(new CircleImageTransform()).into(imageView);

        //Load user balance into TextView
        textView.setText(String.valueOf(presenter.getUserBalance()));

        createBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddTaskClicked();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String message = String.valueOf(position);
                Log.e("tag", message);
                presenter.onSpinerItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        adapter = new CardAdapter(this, new CardAdapter.SelectCardListener() {
            @Override
            public void onCardSelect(Card card) {
                presenter.onCardSelected(card);
            }

            @Override
            public void onCardLongClick(Card card) {
                presenter.onCardLongClicked(card);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        //       WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void showCardList(List<Card> list) {
        adapter.setCards(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        //Toast.makeText(this, presenter.getUserInfo(), Toast.LENGTH_LONG).show();
        List<Card> list = new ArrayList<>();
        list.add(new Card(1,"Иван","Просьба", "Прошу погулять с моей собакой", "Описание", true,
                10, "Новосибирск", "89123456789"));
        list.add(new Card(1,"Федор","Просьба", "Прошу застрелить моего соседа", "Описание", true,
                10, "Москва", "89123456789"));
        list.add(new Card(1,"Петр","Предложение", "Прошу погулять с моей собакой", "Описание", true,
                10, "Иркутск", "89123456789"));
        adapter.setCards(list);
    }

    public int getSpinnerItemPosition() {
        return spinner.getSelectedItemPosition();
    }

    @Override
    public void openCardCreateScreen() {
        CardCreateActivity.start(this);
    }

    @Override
    protected MvpPresenter<CardListView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
