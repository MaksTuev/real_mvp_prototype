package com.agna.realmvp.realmvpsample.ui.base.screen.model.state;


public enum PaginationState {
    READY, //готово к пагинации или запрос на пагинацию выполняется
    COMPLETE, //все данные загружены
    ERROR, //при загрузке данных произошла ошибка
}
