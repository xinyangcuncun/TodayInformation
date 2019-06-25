package com.vivo.player.lf;

/**
 * Created by anson on 2017/11/24.
 */

public interface IPlayerError {

    int getCode();

    String getMessage();

    Throwable getCause();
}
