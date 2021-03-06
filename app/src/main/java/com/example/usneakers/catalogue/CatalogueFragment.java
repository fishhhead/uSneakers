package com.example.usneakers.catalogue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usneakers.R;

import java.util.ArrayList;

public class CatalogueFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ListAdapter mListadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogue, container, false);

        /*initial recycler view*/
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        /*iterate though item array list to retrieve data*/
        ArrayList data = new ArrayList<catalogueItems>();
        for (int i = 0; i < catalogueData.textArray.length; i++){
            data.add(
                    new catalogueItems
                            (
                                    catalogueData.imgArray[i],
                                    catalogueData.textArray[i]
                            ));
        }


        /*initial list adapter*/
        mListadapter = new ListAdapter(data);
        mRecyclerView.setAdapter(mListadapter);

        return view;
    }

    /*initial list adapter*/
    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        private ArrayList<catalogueItems> dataList;

        public ListAdapter(ArrayList<catalogueItems> data)
        {
            this.dataList = data;
        }

        /*initial view holders*/
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            ImageView imaV;
            TextView textView;

            public ViewHolder(View itemView)
            {
                super(itemView);

                this.imaV = itemView.findViewById(R.id.imageView);
                this.textView = itemView.findViewById(R.id.textView);
            }
        }

        @NonNull
        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalogue_recyclerview, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        /*bind view holder*/
        @Override
        public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, final int position) {
            holder.imaV.setImageResource(dataList.get(position).getImageResource());
            holder.textView.setText(dataList.get(position).getText());

            /*button click to pass Catalogue data to coupon fragment*/
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Fragment fragment = new catalogueCouponFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("bundleKeyImage", catalogueData.imgArray[position]);
                    bundle.putString("bundleKeyItemName", catalogueData.textArray[position]);
                    fragment.setArguments(bundle);

                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }

        /*get the total length from datalist*/
        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
