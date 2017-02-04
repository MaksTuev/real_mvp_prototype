package com.agna.realmvp.realmvpsample.ui.screen.book.inner;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agna.ferro.core.PersistentScreenScope;
import com.agna.ferro.mvp.presenter.MvpPresenter;
import com.agna.realmvp.realmvpsample.R;
import com.agna.realmvp.realmvpsample.domain.Book;
import com.agna.realmvp.realmvpsample.ui.base.screen.configurator.ScreenConfigurator;
import com.agna.realmvp.realmvpsample.ui.base.screen.fragment.CoreFragmentView;
import com.agna.realmvp.realmvpsample.ui.screen.test.TestFragment;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * View for Book screen
 */
public class BookFragmentView extends CoreFragmentView {

    @Inject
    BookPresenter presenter;

    private TextView nameTv;
    private ImageView coverIv;
    private Button downloadBtn;
    private View readBtn;
    private TextView descriptionTv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler();
    private View contentContainer;

    @Override
    public MvpPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected ScreenConfigurator createScreenConfigurator(Context context,
                                                          PersistentScreenScope persistentScreenScope,
                                                          Bundle arguments) {
        return new BookScreenConfigurator(context, persistentScreenScope, arguments);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        initViews();
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState, boolean viewRecreated) {
        super.onActivityCreated(savedInstanceState, viewRecreated);
        //TestActivity.startForResult(getActivity());
        getChildFragmentManager().beginTransaction().add(new TestFragment(), "DF").commit();
    }

    public void showLoading() {
        handler.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initViews() {
        downloadBtn.setOnClickListener(v -> presenter.downloadBook());
        readBtn.setOnClickListener(v -> Toast.makeText(getActivity(), "Stub", Toast.LENGTH_SHORT).show());
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.reloadData());
    }

    private void findViews(View view) {
        contentContainer = view.findViewById(R.id.book_content_container);
        nameTv = (TextView) view.findViewById(R.id.book_name_tv);
        coverIv = (ImageView) view.findViewById(R.id.book_cover_iv);
        downloadBtn = (Button)view.findViewById(R.id.book_download_btn);
        readBtn = view.findViewById(R.id.book_read_btn);
        descriptionTv = (TextView) view.findViewById(R.id.book_description_tv);
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.book_swr);
    }

    public void showData(FullBookModel fullBookModel) {
        contentContainer.setVisibility(View.VISIBLE);
        Book book = fullBookModel.getBook();
        nameTv.setText(book.getName());
        Glide.with(coverIv.getContext())
                .load(book.getImageUrl())
                .fitCenter()
                .placeholder(R.drawable.book_placeholder)
                .error(R.drawable.book_placeholder)
                .into(coverIv);
        descriptionTv.setText(fullBookModel.getDescription());

        if (book.isDownloaded()) {
            downloadBtn.setVisibility(View.GONE);
            readBtn.setVisibility(View.VISIBLE);
        } else if (book.isDownloading()){
            downloadBtn.setVisibility(View.VISIBLE);
            String downloadBtnText = downloadBtn.getResources()
                    .getString(R.string.downloading_btn, book.getDownloadProgress());
            downloadBtn.setText(downloadBtnText);
            readBtn.setVisibility(View.GONE);
        } else {
            downloadBtn.setVisibility(View.VISIBLE);
            downloadBtn.setText(R.string.download_btn);
            readBtn.setVisibility(View.GONE);
        }
    }


}
