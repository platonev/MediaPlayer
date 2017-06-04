package tk.platonev.mediaplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;

import java.io.File;
import java.io.IOException;

import static com.google.android.exoplayer2.source.ExtractorMediaSource.EventListener;

public class PlayerActivity extends Activity {

    SimpleExoPlayerView mSimpleExoPlayerView;

    SimpleExoPlayer mPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle b = getIntent().getExtras();
        String fileName = b.getString("file");

        mSimpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.player);

        mPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());

        mSimpleExoPlayerView.setPlayer(mPlayer);
        File f = new File(fileName);

        MediaSource mediaSource = new ExtractorMediaSource(Uri.fromFile(new File(fileName)), new FileDataSourceFactory(), new DefaultExtractorsFactory(), null, new EventListener() {

            @Override
            public void onLoadError(IOException error) {
                Toast.makeText(PlayerActivity.this, error.getMessage(), Toast.LENGTH_LONG);
            }
        });

        mPlayer.prepare(mediaSource);
        mPlayer.setPlayWhenReady(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
    }
}
