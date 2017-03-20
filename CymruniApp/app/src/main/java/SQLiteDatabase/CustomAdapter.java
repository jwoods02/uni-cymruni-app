//package SQLiteDatabase;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//
//import com.example.c1629177.cymruniapp.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import SQLiteDatabase.DBConnector;
//
///**
// * Created by encima on 12/15/16.
// */
//
//public class CustomAdapter extends BaseAdapter implements Filterable {
//
//    List<String> dataSource, originalSource;
//    LayoutInflater inflater;
//    DBConnector idh;
//
//    public CustomAdapter(Context c) {
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
//
//    @Override
//    public int getCount() {
//        return dataSource.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return dataSource.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    public void remove(int i) {
//        idh.removeItem(String.valueOf(i));
//        this.dataSource.remove(i);
//        notifyDataSetChanged();
//    }
//
//    private class ViewHolder {
//        TextView textView;
//    }
//
//    /**
//     * This method is called for EVERY element in the provided array list. We use the `row` xml file to add a text view and fill it with the text in the arraylist.
//     * Each of these views builds up a row in the ListView.
//     * @param i
//     * @param view
//     * @param viewGroup
//     * @return
//     */
//    @Override
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
//
//    /**
//     * Create a filter that can find contents that matches the provided string
//     * @return
//     */
//    @Override
//    public Filter getFilter() {
//
//        Filter f = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence filterTerm) {
//                FilterResults fr = new FilterResults();
//                List<String> filteredArrayList = new ArrayList<>();
//
//                //we back up original values for when the filter is cleared
//                if(originalSource == null) {
//                    originalSource = new ArrayList<>(dataSource);
//                }
//
//                //just return original data if the user has not searched for anything
//                if(filterTerm == null || filterTerm.length() == 0) {
//                    fr.count = originalSource.size();
//                    fr.values = originalSource;
//                }else{
//
//                }
//                return fr;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                dataSource = (List<String>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//        return f;
//    }
//}
