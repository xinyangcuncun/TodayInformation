package com.vivo.player.factory;

import com.vivo.player.lf.IMediaSource;
import com.vivo.player.lf.IPlayer;
import com.vivo.player.player.GoogleMediaPlayer;

/**
 * author : renchangcun
 * date : 2019/6/25 15:04
 * description :
 */
public class MediaPlayerFactory {


    public IPlayer createPlayer(int type) {
        switch (type) {
            case 1:
                return new GoogleMediaPlayer();

        }
        return null;
    }
}
