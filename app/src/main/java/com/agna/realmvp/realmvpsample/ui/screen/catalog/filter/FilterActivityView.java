package com.agna.realmvp.realmvpsample.ui.screen.catalog.filter;


import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.agna.ferro.mvp.component.ScreenComponent;
import com.agna.ferro.mvp.presenter.MvpPresenter;
import com.agna.realmvp.realmvpsample.R;
import com.agna.realmvp.realmvpsample.domain.Filter;
import com.agna.realmvp.realmvpsample.domain.ShowFirstFilterValue;
import com.agna.realmvp.realmvpsample.ui.base.screen.activity.CoreActivityView;

import javax.inject.Inject;

public class FilterActivityView extends CoreActivityView {

    @Inject
    FilterPresenter presenter;

    private RadioButton newRb;
    private RadioButton cheapRb;
    private View applyBtn;

    @Override
    public String getName() {
        return "Filter";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_filter;
    }

    @Override
    protected ScreenComponent createScreenComponent() {
        return DaggerFilterComponent.builder()
                .appComponent(getAppComponent())
                .activityScreenModule(getActivityScreenModule())
                .filterScreenModule(new FilterScreenModule(new FilterRoute(getIntent())))
                .build();
    }

    @Override
    public MvpPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState, boolean viewRecreated) {
        super.onCreate(savedInstanceState, viewRecreated);
        findViews();
        initListeners();
    }

    private void initListeners() {
        applyBtn.setOnClickListener(v->presenter.applyFilter());
        addCheckedListeners();
    }

    private void addCheckedListeners() {
        newRb.setOnCheckedChangeListener((v, checked)->{
            if(checked){
                presenter.onShowFirstChanged(ShowFirstFilterValue.NEW);
            }
        });
        cheapRb.setOnCheckedChangeListener((v, checked)->{
            if(checked){
                presenter.onShowFirstChanged(ShowFirstFilterValue.CHEAP);
            }
        });
    }

    private void clearCheckedListeners(){
        newRb.setOnCheckedChangeListener(null);
        cheapRb.setOnCheckedChangeListener(null);
    }

    private void findViews() {
        newRb = (RadioButton) findViewById(R.id.new_rb);
        cheapRb = (RadioButton) findViewById(R.id.cheap_rb);
        applyBtn = findViewById(R.id.apply_btn);
    }

    public void showFilter(Filter filter) {
        switch (filter.getShowFirst()){
            case NEW:
                clearCheckedListeners();
                newRb.setChecked(true);
                addCheckedListeners();
                break;
            case CHEAP:
                clearCheckedListeners();
                cheapRb.setChecked(true);
                addCheckedListeners();
                break;
        }
    }
}
