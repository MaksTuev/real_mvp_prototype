package com.agna.realmvp.realmvpsample.ui.base.screen;


import com.agna.realmvp.realmvpsample.ui.base.recycler.changes.ListChanges;

/**
 * интерфейс для вью со списком основанным на RecyclerView
 * для оповещения View об изменении списка
 */
public interface ChangeableListView {
    void notifyListChanged(ListChanges listChanges);
}
