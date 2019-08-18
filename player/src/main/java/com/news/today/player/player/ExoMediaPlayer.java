package com.news.today.player.player;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.news.today.player.source.IPlayerSource;
import com.news.today.player.state.PlayerState;

/**
 * Created by anson on 2019/8/10.
 */
public class ExoMediaPlayer implements IPlayer, Player.EventListener {


    private final SimpleExoPlayer mExoPlayer;
    private IPlayerListener mPlayerListener;

    public ExoMediaPlayer(Context context) {
        //创建播放器
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(context);
        mExoPlayer.addListener(this);
    }

    @Override
    public void release() {

    }

    @Override
    public void prepare(Context context, IPlayerSource iPlayerSource) {
        DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(iPlayerSource.getResId()));
        RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(context);
        try {
            rawResourceDataSource.open(dataSpec);
        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
            e.printStackTrace();
        }
        Uri uri = rawResourceDataSource.getUri();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, ""));
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        mExoPlayer.prepare(videoSource);
        mExoPlayer.setPlayWhenReady(true);
        setPlayerState(PlayerState.STARTED);

    }

    @Override
    public void setPlayingListener(IPlayerListener listener) {
        this.mPlayerListener = listener;
    }

    @Override
    public void paused() {
        mExoPlayer.setPlayWhenReady(false);
        setPlayerState(PlayerState.PAUSED);
    }

    @Override
    public void reStart() {
        mExoPlayer.setPlayWhenReady(true);
        setPlayerState(PlayerState.STARTED);
    }

    private void setPlayerState(PlayerState state) {
        if (mPlayerListener != null) {
            mPlayerListener.playerStateChanged(state);
        }
    }

}
