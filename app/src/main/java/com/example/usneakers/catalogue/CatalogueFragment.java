package com.example.usneakers.catalogue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usneakers.R;

import java.util.ArrayList;

public class CatalogueFragment extends Fragment {

    private TextView mTextViewEmpty;
    private ProgressBar mProgressBarLoading;
    private ImageView mImageViewEmpty;
    private RecyclerView mRecyclerView;
    private ListAdapter mListadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogue, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mProgressBarLoading = (ProgressBar)view.findViewById(R.id.progressBarLoading);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        ArrayList data = new ArrayList<catalogueItems>();
        for (int i = 0; i < catalogueData.id.length; i++){
            data.add(
                    new catalogueItems
                            (
                                    catalogueData.id[i],
                                    catalogueData.imgArray[i],
                                    catalogueData.textArray[i]
                            ));
        }


        mListadapter = new ListAdapter(data);
        mRecyclerView.setAdapter(mListadapter);

        return view;
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        private ArrayList<catalogueItems> dataList;

        public ListAdapter(ArrayList<catalogueItems> data)
        {
            this.dataList = data;
        }

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

        @Override
        public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, final int position) {
            holder.imaV.setImageResource(dataList.get(position).getImageResource());
            holder.textView.setText(dataList.get(position).getText());

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
