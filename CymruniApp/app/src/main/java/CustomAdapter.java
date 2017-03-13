import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by c1629177 on 10/03/2017.
 */
//public class CustomAdapter {
//    {
//        idh = new DBConnector(c);
//        Map<Long, String> items = idh.getItems(null);
//        this.dataSource = new ArrayList<>();
//        this.dataSource.addAll(items.values());
//        inflater = LayoutInflater.from(c);
//    }
//
//    public void addItem(String item) {
//        Long i = this.idh.addItem(item);
//        this.dataSource.add(item);
//        notifyDataSetChanged();
//    }
//    public void remove(int i) {
//        idh.removeItem(String.valueOf(i));
//        this.dataSource.remove(i);
//        notifyDataSetChanged();
//    }
//    public View getView(int i, View view, ViewGroup viewGroup) {
//
//        ViewHolder vh = null;
//
//        if(view == null) {
//            vh = new ViewHolder();
//            view = inflater.inflate(R.layout.row, null);
//            vh.textView = (TextView) view.findViewById(R.id.textView);
//            view.setTag(vh);
//        }else{
//            vh = (ViewHolder) view.getTag();
//        }
//        vh.textView.setText(dataSource.get(i));
//        return view;
//    }
//}
