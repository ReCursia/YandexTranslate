package com.recursia.yandextranslate.presentation.favorite.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.domain.favorite.GetFavoriteWordPairsInteractor;
import com.recursia.yandextranslate.domain.favorite.RemoveFavoriteWordPairInteractor;
import com.recursia.yandextranslate.presentation.favorite.view.FavoriteView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class FavoritePresenter extends MvpPresenter<FavoriteView> {
    private final RemoveFavoriteWordPairInteractor mRemoveFavoriteWordPairInteractor;
    private final GetFavoriteWordPairsInteractor mGetFavoriteWordPairsInteractor;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    public FavoritePresenter(RemoveFavoriteWordPairInteractor mRemoveFavoriteWordPairInteractor,
                             GetFavoriteWordPairsInteractor mGetFavoriteWordPairsInteractor) {
        this.mRemoveFavoriteWordPairInteractor = mRemoveFavoriteWordPairInteractor;
        this.mGetFavoriteWordPairsInteractor = mGetFavoriteWordPairsInteractor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    @Override
    protected void onFirstViewAttach() {
        initView();
    }

    private void initView() {
        Disposable d = mGetFavoriteWordPairsInteractor.getAllFavoriteWordPairs()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModels -> getViewState().setWords(wordPairViewModels));
        mCompositeDisposable.add(d);
    }


    public void onWordPairClicked(WordPair wordPair, int position) {
        Disposable d = mRemoveFavoriteWordPairInteractor.removeFavorite(wordPair)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> getViewState().deleteWord(position))
                .doOnError(throwable -> getViewState().showErrorMessage(throwable.getLocalizedMessage()))
                .subscribe();
        mCompositeDisposable.add(d);
    }
}
