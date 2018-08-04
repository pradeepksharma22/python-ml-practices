package com.github.pradeepksharma22.nyt.articlelist;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pradeepksharma22.nyt.R;
import com.github.pradeepksharma22.nyt.databinding.ListItemArticleBinding;
import com.github.pradeepksharma22.nyt.pojo.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private List<Article> articles;
    private OnItemClickedListener listener;

    public ArticlesAdapter(List<Article> articles, OnItemClickedListener listener) {
        this.articles = articles;
        this.listener = listener;
    }


    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        ListItemArticleBinding articleBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_article, parent, false);
        ArticleViewHolder vh = new ArticleViewHolder(articleBinding);
        return vh;
    }

    public void addArticles(List<Article> articles) {
        if (articles==null){
            return;
        }
        if (this.articles==null){
            this.articles=new ArrayList<>();
        }
        this.articles.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    public interface OnItemClickedListener {
        void onItemClicked(Article article);
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ListItemArticleBinding binding;

        public ArticleViewHolder(ListItemArticleBinding articleBinding) {
            super(articleBinding.getRoot());
            this.binding = articleBinding;
            binding.getRoot().setOnClickListener(this);
        }

        public void bind(Article article) {
            binding.setArticle(article);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked(articles.get(getAdapterPosition()));
            }
        }
    }
}