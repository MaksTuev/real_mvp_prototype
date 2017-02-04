package com.agna.realmvp.realmvpsample.interactor.error;

/**
 * коды ошибок {@link HttpError}
 */
public class HttpCodes {
    public static final int CODE_105 = 105; //email уже есть в базе
    public static final int CODE_107 = 107; //номер телефона уже есть в базе
    public static final int CODE_200 = 200; //успех
    public static final int CODE_304 = 304; //нет обновленных данных
    public static final int CODE_401 = 401; //недоступное действие для пользователя
    public static final int CODE_400 = 400; //Bad request, возможно передан невалидный токен
    public static final int CODE_405 = 405; //необходимо обновить приложение
    public static final int CODE_500 = 500; //ошибка сервера
}
