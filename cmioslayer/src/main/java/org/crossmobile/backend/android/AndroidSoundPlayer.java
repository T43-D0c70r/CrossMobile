/* (c) 2019 by Panayotis Katsaloulis
 *
 * CrossMobile is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 2.
 *
 * CrossMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CrossMobile; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.crossmobile.backend.android;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import crossmobile.ios.foundation.NSURL;
import org.crossmobile.bind.io.SoundPlayer;
import org.crossmobile.bridge.Native;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AndroidSoundPlayer implements SoundPlayer {

    private static final int MAXSTREAMS = 10;
    private static final AudioManager manager = (AudioManager) MainActivity.current.getSystemService(Context.AUDIO_SERVICE);
    private static final SoundPool soundPool = new SoundPool(MAXSTREAMS, AudioManager.STREAM_MUSIC, 0);
    private static final HashMap<String, Integer> poolindex = new HashMap<>();
    private static MediaPlayer player;
    private float volume = 1;
    private final SoundPlayer.Delegate delegate;
    private boolean prepared = false;

    public AndroidSoundPlayer(NSURL url, SoundPlayer.Delegate delegate) {
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        AssetFileDescriptor afd = null;
        try {
            afd = MainActivity.current.getAssets().openFd(url.absoluteString().substring(AndroidFileBridge.APPLICATION_PREFIX.length() + 1));
        } catch (IOException ex) {
            Logger.getLogger(AndroidSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        player.setOnPreparedListener(opl);
        try {
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException | IllegalArgumentException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(AndroidSoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.delegate = delegate;
        if (delegate != null) {
            player.setOnCompletionListener((MediaPlayer mp) -> {
                System.out.println("Completed playing AUDIO");
                AndroidSoundPlayer.this.delegate.finishSuccessful();
            });

            player.setOnErrorListener((MediaPlayer mp, int what, int extra) -> {
                System.out.println("Error while playing AUDIO : " + what + " extra : " + extra);
                AndroidSoundPlayer.this.delegate.finishWithError(new RuntimeException(
                        what == MediaPlayer.MEDIA_ERROR_SERVER_DIED
                                ? "Server died"
                                : "Error while playing audio stream"));
                return false;
            });
        }
        player.prepareAsync();
    }

    MediaPlayer.OnPreparedListener opl = (MediaPlayer mp) -> {
        mp.start();
    };

    @Override
    public boolean isPlaying() {
        return player.isPlaying();
    }

    @Override
    public boolean play() {
        opl.onPrepared(player);
        return true;
    }

    @Override
    public void pause() {
        player.pause();
    }

    @Override
    public int getChannels() {
        Native.system().debug("Android sound player only supports 2 channels", null);
        return 2;
    }

    @Override
    public double getDuration() {
        return player.getDuration();
    }

    @Override
    public void setLocation(double time) {
        player.seekTo((int) (time + 0.5));
    }

    @Override
    public double getLocation() {
        return player.getCurrentPosition();
    }

    @Override
    public void setVolume(float volume) {
        player.setVolume(volume, volume);
        this.volume = volume;
    }

    @Override
    public float getVolume() {
        return volume;
    }
}