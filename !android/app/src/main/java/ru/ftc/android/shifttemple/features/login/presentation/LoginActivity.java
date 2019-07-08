package ru.ftc.android.shifttemple.features.login.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.ftc.android.shifttemple.R;
import ru.ftc.android.shifttemple.features.BaseActivity;
import ru.ftc.android.shifttemple.features.MvpPresenter;
import ru.ftc.android.shifttemple.features.MvpView;
import ru.ftc.android.shifttemple.features.cards.presentation.CardActivity;
import ru.ftc.android.shifttemple.features.login.domain.model.User;

public final class LoginActivity extends BaseActivity implements LoginView, LoginJumpView{

    private LoginPresenter presenter;
    private UserAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.users_recycle_view);
        progressBar = findViewById(R.id.users_progress);

        adapter = new UserAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(User user) {
        presenter.onNavigateNextClick(user);
    }


    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        //For handle creating users:
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,"Зеленский Максим", 34, "Новосибирск", "https://pp.userapi.com/c846520/v846520705/17a48e/oNDy0fgijM8.jpg?ava=1", "89142553345",
                100, true, "Человек"));
        userList.add(new User(2,"Павел Воля", 34, "Новосибирск", "https://sun1-86.userapi.com/c851416/v851416077/6bdc0/Ju8Kpu1XRXQ.jpg?ava=1", "89142553345",
                100, true, "Человек"));
        userList.add(new User(3,"Михаил Галустян", 34, "Новосибирск", "https://sun1-20.userapi.com/c639228/v639228720/38500/ODaaqZmKoPw.jpg?ava=1", "89142553345",
                100, true, "Человек"));
        userList.add(new User(4,"Дмитрий Билан", 34, "Новосибирск", "https://sun1-21.userapi.com/c844723/v844723134/1908f7/79z2OB-kjro.jpg?ava=1", "89142553345",
                100, true, "Человек"));

        userList.add(new User(1,"Зеленский Максим", 34, "Новосибирск", "https://pp.userapi.com/c846520/v846520705/17a48e/oNDy0fgijM8.jpg?ava=1", "89142553345",
                100, true, "Человек"));
        userList.add(new User(2,"Павел Воля", 34, "Новосибирск", "https://sun1-86.userapi.com/c851416/v851416077/6bdc0/Ju8Kpu1XRXQ.jpg?ava=1", "89142553345",
                100, true, "Человек"));
        userList.add(new User(3,"Михаил Галустян", 34, "Новосибирск", "https://sun1-20.userapi.com/c639228/v639228720/38500/ODaaqZmKoPw.jpg?ava=1", "89142553345",
                100, true, "Человек"));
        userList.add(new User(4,"Дмитрий Билан", 34, "Новосибирск", "https://sun1-21.userapi.com/c844723/v844723134/1908f7/79z2OB-kjro.jpg?ava=1", "89142553345",
                100, true, "Человек"));
        adapter.setUserList(userList);
        //
    }

    @Override
    public void showUserList(List<User> userList) {
        adapter.setUserList(userList);
    }

    @Override
    public void openCardListScreen() {
        CardActivity.start(this);
    }

    @Override
    public void showNotSelectedUserError() {
        Toast.makeText(this, getText(R.string.not_selected_user_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<LoginView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}