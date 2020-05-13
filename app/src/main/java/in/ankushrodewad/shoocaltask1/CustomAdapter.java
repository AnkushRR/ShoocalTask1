package in.ankushrodewad.shoocaltask1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<RepoObject> {

    private ArrayList<RepoObject> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView tv_login;
        TextView tv_ids;
        TextView tv_repo_url;
        CheckBox cb;
    }

    public CustomAdapter(ArrayList<RepoObject> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RepoObject repoObject = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.tv_login = (TextView) convertView.findViewById(R.id.tv_login);
            viewHolder.tv_ids = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tv_repo_url = (TextView) convertView.findViewById(R.id.tv_repo_url);
            viewHolder.cb = (CheckBox) convertView.findViewById(R.id.cb_state);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_login.setText("login: "+repoObject.getUser().getLogin());
        viewHolder.tv_ids.setText("id: "+repoObject.getUser().getId());
        viewHolder.tv_repo_url.setText("repo_url: "+repoObject.getUser().getReposUrl());
        if(repoObject.getState().equals("open"))
            viewHolder.cb.setChecked(true);
        // Return the completed view to render on screen
        return convertView;
    }
}
