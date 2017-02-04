package com.agna.realmvp.realmvpsample.ui.base.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agna.realmvp.realmvpsample.ui.base.screen.model.LdsSwrPgnScreenModel;
import com.agna.realmvp.realmvpsample.ui.base.screen.model.state.PaginationState;

/**
 * Адаптер с поддержкой пагинации, работает в связке с {@link LdsSwrPgnScreenModel}
 * Добавляет к списку футер с состояниями {@link PaginationState}
 * От этого адаптера следует унаследоваться и переопределить методы {@link #onCreateCustomViewHolder},
 * {@link #onBindCustomViewHolder}, {@link #getCustomItemCount} вместо оригинальных.
 * Также в случае необходимости можно переопределить {@link #getCustomItemViewType},
 * {@link #getPaginationFooterPosition}
 * <p>
 * После эмита события о том что необходимо загрузить новый блок данных, события больше не
 * будут выбрасываться пока адаптеру не будет установлен {@link PaginationState#READY}
 * Также событие о необходимости подгрузить данные эмитится при клике на футер с состоянием
 * {@link PaginationState#ERROR}
 */
public abstract class PaginationableAdapter<VH extends RecyclerView.ViewHolder> extends BaseAdapter {

    private static final int FOOTER_TYPE = 2002;
    protected static final int DEFAULT_ITEM_TYPE = 0;

    private PaginationState paginationState = PaginationState.READY;
    private OnShowMoreListener onShowMoreListener;
    private boolean blockShowMoreEvent = true;

    public interface OnShowMoreListener {
        void onShowMore();
    }

    protected abstract VH onCreateCustomViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindCustomViewHolder(VH holder, int position);

    protected abstract int getCustomItemCount();

    protected int getCustomItemViewType(int position) {
        return DEFAULT_ITEM_TYPE;
    }

    protected int getPaginationFooterPosition() {
        return getItemCount() - 1;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType == FOOTER_TYPE
                ? PaginationFooterHolder.newInstance(parent, this::onShowMoreClick)
                : onCreateCustomViewHolder(parent, viewType);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getPaginationFooterPosition()) {
            ((PaginationFooterHolder) holder).bind(paginationState);
        } else {
            onBindCustomViewHolder((VH)holder, position);
        }
    }

    @Override
    public final int getItemViewType(int position) {
        return position == getPaginationFooterPosition()
                ? FOOTER_TYPE
                : getCustomItemViewType(position);
    }

    @Override
    public final int getItemCount() {
        return getCustomItemCount() == 0 ? 0 : getCustomItemCount() + 1;
    }

    public void setState(PaginationState state) {
        this.paginationState = state;
        blockShowMoreEvent = state != PaginationState.READY;
        notifyItemChanged(getPaginationFooterPosition());
    }

    public void setOnShowMoreListener(OnShowMoreListener onShowMoreListener) {
        this.onShowMoreListener = onShowMoreListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        initLayoutManager(layoutManager);
        initPaginationListener(recyclerView, layoutManager);
    }

    private void initPaginationListener(RecyclerView recyclerView, LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (onShowMoreListener != null && !blockShowMoreEvent) {
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    int numVisibleItem = lastVisibleItem - firstVisibleItem;

                    if (totalItemCount - lastVisibleItem < 2 * numVisibleItem) {
                        onShowMoreListener.onShowMore();
                        blockShowMoreEvent = true;
                    }
                }
            }
        });
    }

    private void initLayoutManager(LinearLayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager castedLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup existingLookup = castedLayoutManager.getSpanSizeLookup();

            castedLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == getPaginationFooterPosition()) {
                        //footer должен быть на всю ширину экрана
                        return castedLayoutManager.getSpanCount();
                    } else {
                        return existingLookup.getSpanSize(position);
                    }
                }
            });
        }
    }

    private void onShowMoreClick() {
        setState(PaginationState.READY);
        blockShowMoreEvent = true;
        if (onShowMoreListener != null) {
            onShowMoreListener.onShowMore();
        }
    }

    private static class PaginationFooterHolder extends BindableViewHolder<PaginationState> {

        private ProgressBar progressBar;
        private TextView showMoreTv;

        public static PaginationFooterHolder newInstance(ViewGroup parent, OnShowMoreListener onShowMoreListener) {
            /*return new PaginationFooterHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.pagination_footer_layout, parent, false),
                    onShowMoreListener);*/
            //// TODO: 04.02.2017
            return null;
        }

        public PaginationFooterHolder(View itemView, OnShowMoreListener onShowMoreListener) {
            super(itemView);
           /* showMoreTv = (TextView) itemView.findViewById(R.id.pagination_footer_text);
            progressBar = (MaterialProgressBar) itemView.findViewById(R.id.pagination_footer_progress);
            showMoreTv.setOnClickListener(v -> onShowMoreListener.onShowMore());
            progressBar.setVisibility(View.GONE);
            showMoreTv.setVisibility(View.GONE);*/
        }

        @Override
        public void bind(PaginationState state) {
            switch (state) {
                case READY:
                    progressBar.setVisibility(View.VISIBLE);
                    showMoreTv.setVisibility(View.GONE);
                    break;
                case COMPLETE:
                    progressBar.setVisibility(View.GONE);
                    showMoreTv.setVisibility(View.GONE);
                    break;
                case ERROR:
                    progressBar.setVisibility(View.GONE);
                    showMoreTv.setVisibility(View.VISIBLE);
                    break;
                default:
                    throw new IllegalArgumentException("unsupported state: " + state);
            }
        }
    }


}
