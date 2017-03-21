package com.lsq.learn.newsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lsq.learn.BaseFragment;
import com.lsq.learn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lsq on 2017/3/18.
 */

public class NewsTitleFragment extends BaseFragment  {
    View view;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("BaseActivity", "onCreateView: "+getClass().getSimpleName());
        view=inflater.inflate(R.layout.frag_news_title,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.rec_title);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        newsAdapter=new NewsAdapter(getNews());
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    private List<News> getNews(){
        List<News> newsList=new ArrayList<>();
        for (int i=0;i<50;i++){
            News news=new News("this is news title"+ i,getRandomLengthContent("this is news content"+i));
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<length;i++){
            builder.append(content);
        }
        return builder.toString();
    }


    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewsList;

        public NewsAdapter(List<News> mNewsList) {
            this.mNewsList = mNewsList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_title,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            final NewsActivity newsActivity= (NewsActivity) getActivity();
            view.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    News news=mNewsList.get(holder.getAdapterPosition());
                    newsActivity.chengContentFrag(news.getTitle(),news.getContent());
//                    NewsActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
            News news=mNewsList.get(position);
            holder.TV_news_frag_title.setText(news.getTitle());

        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView TV_news_frag_title;
            View view;
            public ViewHolder(View itemView) {
                super(itemView);
                view=itemView;
                TV_news_frag_title= (TextView) itemView.findViewById(R.id.TV_news_frag_title);
            }
        }
    }
}
