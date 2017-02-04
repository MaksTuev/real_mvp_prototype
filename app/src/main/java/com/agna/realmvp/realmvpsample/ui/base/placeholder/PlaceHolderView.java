package com.agna.realmvp.realmvpsample.ui.base.placeholder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.LoadState;


/**
 * плейсхолдер с состояниями: PlaceHolderView.State#LOADING, LOADING_TRANSPARENT, EMPTY, ERROR, NOT_FOUND, SUCCESS.
 * Используется на всех экранах, где для отображения контента необходимо сначала загрузить данные.
 * Состояние NOT_FOUND используется на экранах с поиском или фильтром.
 */
public class PlaceHolderView extends FrameLayout {

    private static final int TRANSPARENT_WHITE_COLOR = 0x5FFFFFFF;

    private State state;

    private View loadingContainer;
    private View contentContainer;
    private TextView titleTv;
    private TextView subtitleTv;
    private Button submitBtn;
    private AlphaAnimation loadingFadeOutAnimation;
    private OnActionClickListener onActionClickListener;

    private String titleEmpty;
    private String subtitleEmpty;
    private String buttonTextEmpty;
    private Drawable drawableIconEmpty;

    private String titleNotFound;
    private String subtitleNotFound;
    private String buttonTextNotFound;
    private Drawable drawableIconNotFound;

    private String titleError;
    private String subtitleError;
    private String buttonTextError;
    private Drawable drawableIconError;

    public enum State {
        /**
         * прогресс бар по центру экрана
         */
        LOADING {
            @Override
            public void showState(PlaceHolderView view) {
                view.showLoadingContainer(false);
            }
        },
        /**
         * прогресс бар по центру экрана с прозрачным фоном
         */
        LOADING_TRANSPARENT {
            @Override
            public void showState(PlaceHolderView view) {
                view.showLoadingContainer(true);
            }
        },
        /**
         * заглушка - нет данных для отображения
         */
        EMPTY {
            @Override
            public void showState(PlaceHolderView view) {
                view.showEmptyViewContainer();
            }
        },
        /**
         * заглушка - пустой результат поиска
         */
        NOT_FOUND {
            @Override
            public void showState(PlaceHolderView view) {
                view.showNotFoundViewContainer();
            }
        },
        /**
         * плейсхолдер невидим, при этом необходимо показывать контент экрана
         */
        NONE {
            @Override
            public void showState(PlaceHolderView view) {
                view.hide();
            }
        },
        /**
         * Виден плейсхолдер ошибки
         */
        ERROR {
            @Override
            public void showState(PlaceHolderView view) {
                view.showErrorViewContainer();
            }
        };

        public abstract void showState(PlaceHolderView view);

    }

    public interface OnActionClickListener {
        void onActionClick(State state);
    }

    public PlaceHolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        /*inflate(context, R.layout.placeholder_view_layout, this);

        initLoadingFadeOutAnimation();

        loadingContainer = findViewById(R.id.placeholder_view_load_container);

        contentContainer = findViewById(R.id.placeholder_empty_view_container);
        titleTv = (TextView) findViewById(R.id.placeholder_empty_view_title_tv);
        subtitleTv = (TextView) findViewById(R.id.placeholder_empty_view_subtitle_tv);
        submitBtn = (Button) findViewById(R.id.placeholder_empty_view_btn);
        submitBtn.setOnClickListener(this::onSubmitButtonClick);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PlaceHolderView, 0, 0);
        try {
            titleEmpty = ta.getString(R.styleable.PlaceHolderView_titleEmpty);
            subtitleEmpty = ta.getString(R.styleable.PlaceHolderView_subtitleEmpty);
            buttonTextEmpty = ta.getString(R.styleable.PlaceHolderView_buttonTextEmpty);
            drawableIconEmpty = ta.getDrawable(R.styleable.PlaceHolderView_iconEmpty);

            titleNotFound = ta.getString(R.styleable.PlaceHolderView_titleNotFound);
            subtitleNotFound = ta.getString(R.styleable.PlaceHolderView_subtitleNotFound);
            buttonTextNotFound = ta.getString(R.styleable.PlaceHolderView_buttonTextNotFound);
            drawableIconNotFound = ta.getDrawable(R.styleable.PlaceHolderView_iconNotFound);

            titleError = ta.getString(R.styleable.PlaceHolderView_titleError);
            subtitleError = ta.getString(R.styleable.PlaceHolderView_subtitleError);
            buttonTextError = ta.getString(R.styleable.PlaceHolderView_buttonTextError);
            drawableIconError = ta.getDrawable(R.styleable.PlaceHolderView_iconError);
        } finally {
            ta.recycle();
        }
        if (TextUtils.isEmpty(titleEmpty) && TextUtils.isEmpty(subtitleEmpty) && TextUtils.isEmpty(buttonTextEmpty) && drawableIconEmpty == null) {
            drawableIconEmpty = ContextCompat.getDrawable(getContext(), R.drawable.ic_default_placeholder);
            titleEmpty = getResources().getString(R.string.placeholder_default_title);
            subtitleEmpty = getResources().getString(R.string.placeholder_default_subtitle);
            buttonTextEmpty = getResources().getString(R.string.placeholder_default_button_text);
        }

        if (TextUtils.isEmpty(titleError) && TextUtils.isEmpty(subtitleError) && TextUtils.isEmpty(buttonTextError) && drawableIconError == null) {
            drawableIconError = ContextCompat.getDrawable(getContext(), R.drawable.ic_default_placeholder);
            titleError = getResources().getString(R.string.placeholder_default_title);
            subtitleError = getResources().getString(R.string.placeholder_default_subtitle);
            buttonTextError = getResources().getString(R.string.placeholder_default_button_text);
        }*/
    }

    public void showState(LoadState state) {
        /*this.state = state;
        this.state.showState(this);*/
        //todo implement
    }

    /**
     * @param onActionClickListener обработчик нажатия кнопок PlaceHolderView
     */
    public void setOnActionClickListener(OnActionClickListener onActionClickListener) {
        this.onActionClickListener = onActionClickListener;
    }

    private void onSubmitButtonClick(View v) {
        if (onActionClickListener != null) {
            onActionClickListener.onActionClick(state);
        }
    }

    public void setTitleNotFound(String titleNotFound) {
        this.titleNotFound = titleNotFound;
    }

    public void setSubtitleNotFound(String subtitleNotFound) {
        this.subtitleNotFound = subtitleNotFound;
    }

    private void initLoadingFadeOutAnimation() {
        loadingFadeOutAnimation = new AlphaAnimation(1f, 0f);
        loadingFadeOutAnimation.setDuration(250);
        loadingFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                loadingContainer.setVisibility(GONE);
                loadingContainer.setAlpha(1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void showLoadingContainer(boolean transparent) {
        hideEmptyViewContainer();
        if (transparent) {
            loadingContainer.setBackgroundColor(TRANSPARENT_WHITE_COLOR);
        } else {
            loadingContainer.setBackgroundColor(Color.WHITE);
        }
        loadingContainer.setVisibility(VISIBLE);
    }

    private void hideLoadingContainer() {
        if (loadingContainer.getVisibility() == VISIBLE) {
            loadingContainer.startAnimation(loadingFadeOutAnimation);
        }
    }

    private void showEmptyViewContainer() {
        contentContainer.setVisibility(VISIBLE);
        showPlaceHolderContent(titleEmpty, subtitleEmpty, buttonTextEmpty, drawableIconEmpty);
    }

    private void showErrorViewContainer() {
        contentContainer.setVisibility(VISIBLE);
        showPlaceHolderContent(titleError, subtitleError, buttonTextError, drawableIconError);
    }

    private void hideEmptyViewContainer() {
        contentContainer.setVisibility(GONE);
    }

    private void showNotFoundViewContainer() {
        contentContainer.setVisibility(VISIBLE);
        showPlaceHolderContent(titleNotFound, subtitleNotFound, buttonTextNotFound, drawableIconNotFound);
    }

     private void showPlaceHolderContent(String title, String subtitle, String buttonText, Drawable icon) {
        showPlaceHolderContent(titleTv, title, subtitleTv, subtitle, submitBtn, buttonText, icon);
    }

    /**
     * @param title      заголовок
     * @param subtitle   подзаголовок
     * @param buttonText текст кнопки действия
     * @param icon       иконка
     */
    private void showPlaceHolderContent(TextView titleTv, String title,
                                        TextView subtitleTv, String subtitle,
                                        Button actionButton, String buttonText,
                                        Drawable icon) {
        if (TextUtils.isEmpty(title)) {
            titleTv.setVisibility(GONE);
        } else {
            titleTv.setVisibility(VISIBLE);
            titleTv.setText(title);
        }
        if (TextUtils.isEmpty(subtitle)) {
            subtitleTv.setVisibility(GONE);
        } else {
            subtitleTv.setVisibility(VISIBLE);
            subtitleTv.setText(subtitle);
        }
        if (TextUtils.isEmpty(buttonText)) {
            actionButton.setVisibility(GONE);
        } else {
            actionButton.setVisibility(VISIBLE);
            actionButton.setText(buttonText);
        }
        if (icon != null) {
            titleTv.setVisibility(VISIBLE);
            titleTv.setCompoundDrawablesWithIntrinsicBounds(null, icon, null, null);
        }
    }

    private void hide() {
        hideLoadingContainer();
        hideEmptyViewContainer();
    }
}
