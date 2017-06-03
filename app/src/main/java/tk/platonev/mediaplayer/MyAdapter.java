package tk.platonev.mediaplayer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<MediaFileInfo> mVideos = new ArrayList<>();

    public MyAdapter(Context context) {
        String[] proj = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DURATION};

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor videocursor = context.getContentResolver().query(uri, proj, null, null, null);

        int video_column_index;

        int count = videocursor.getCount();

        for (int i = 0; i < count; i++) {
            videocursor.moveToPosition(i);
            video_column_index = videocursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME);
            String name = videocursor.getString(video_column_index);

            Log.i("Hello", name);

            video_column_index = videocursor.getColumnIndex(MediaStore.Video.Media.DATA);
            String filepath = videocursor.getString(video_column_index);

            String[] columnNames = videocursor.getColumnNames();
            for (String n : columnNames) {
                Log.i("Hello", n);
            }

            Log.i("Hello", filepath);

            video_column_index = videocursor.getColumnIndex(MediaStore.Video.Media.DURATION);
            String duration = videocursor.getString(video_column_index);

            MediaFileInfo mediaFileInfo = new MediaFileInfo();
            mediaFileInfo.setFileName(name);
            mediaFileInfo.setFilePath(filepath);
            mediaFileInfo.setDuration(duration);
            mVideos.add(mediaFileInfo);
        }
        videocursor.close();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getVideoName().setText(mVideos.get(position).getFileName());
        holder.getVideoDuration().setText(mVideos.get(position).getDuration());
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView videoName;

        public TextView getVideoDuration() {
            return videoDuration;
        }

        private TextView videoDuration;

        public ViewHolder(View itemView) {
            super(itemView);

            videoName = (TextView) itemView.findViewById(R.id.videoName);
            videoDuration = (TextView) itemView.findViewById(R.id.videoDuration);
        }

        public TextView getVideoName() {
            return videoName;
        }
    }
}
