package com.github.pradeepksharma22.nyt.articlelist;


import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.pradeepksharma22.nyt.Constants;
import com.github.pradeepksharma22.nyt.R;
import com.github.pradeepksharma22.nyt.common.BaseFragment;
import com.github.pradeepksharma22.nyt.databinding.FragmentArticlesBinding;
import com.github.pradeepksharma22.nyt.net.ArticleHttpRequest;
import com.github.pradeepksharma22.nyt.net.HttpResponseListener;
import com.github.pradeepksharma22.nyt.pojo.Article;
import com.github.pradeepksharma22.nyt.pojo.ArticleResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;


public class ArticlesFragment extends BaseFragment implements ArticlesAdapter.OnItemClickedListener {

    private ArticlesAdapter adapter;
    private FragmentArticlesBinding binding;

    public ArticlesFragment() {
        // Required empty public constructor
    }

    public static ArticlesFragment newInstance() {
        ArticlesFragment fragment = new ArticlesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_articles, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        binding.rvArticles.setLayoutManager(manager);
        binding.rvArticles.addItemDecoration(new VerticalSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.article_list_vertical_space)));
        binding.rvArticles.setHasFixedSize(true);
        adapter = new ArticlesAdapter(new ArrayList<Article>(), this);
        binding.rvArticles.setAdapter(adapter);
        loadArticles();
    }

    private void onArticleLoad(ArticleResponse articleResponse) {
        binding.animation.setVisibility(View.GONE);

        if (articleResponse.getResults() != null && articleResponse.getResults().size() > 0) {
            binding.rvArticles.setVisibility(View.VISIBLE);
            adapter.addArticles(articleResponse.getResults());
        } else {
            binding.rvArticles.setVisibility(View.GONE);
            binding.errorText.setVisibility(View.VISIBLE);
            binding.errorText.setText("No results!");
        }
    }

    private void loadArticles() {
        binding.animation.setVisibility(View.VISIBLE);
        ArticleHttpRequest.Param param = new ArticleHttpRequest.Param(Constants.DEFAULT_SECTION, Constants.DEFAULT_PERIOD, adapter.getItemCount() + 20);
        ArticleHttpRequest request = new ArticleHttpRequest(param);
        request.execute(new HttpResponseListener() {
            @Override
            public void onFail(final Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.animation.setVisibility(View.GONE);
                        binding.rvArticles.setVisibility(View.GONE);
                        binding.errorText.setVisibility(View.VISIBLE);
                        binding.errorText.setText("Request failed, error= " + e);
                    }
                });
            }

            @Override
            public void onSuccess(Response response) {
                try {
                    final ArticleResponse articleResponse = new Gson().fromJson(response.body().string(), ArticleResponse.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            onArticleLoad(articleResponse);

                        }
                    });
                } catch (IOException e) {
                    onFail(e);
                }
            }
        });
    }

    @Override
    public void onItemClicked(Article article) {

    }

    private class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }
}
