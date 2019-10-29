package com.recursia.yandextranslate.presentation.dictionary.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.recursia.yandextranslate.domain.dictionary.GetFavoriteWordPairsInteractor;
import com.recursia.yandextranslate.domain.dictionary.RemoveFavoriteWordPairInteractor;
import com.recursia.yandextranslate.domain.dictionary.models.WordPair;
import com.recursia.yandextranslate.presentation.dictionary.mapper.ViewModelToWordPairMapper;
import com.recursia.yandextranslate.presentation.dictionary.mapper.WordPairToViewModelMapper;
import com.recursia.yandextranslate.presentation.dictionary.models.WordPairViewModel;
import com.recursia.yandextranslate.presentation.dictionary.view.FavoriteView;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FavoritePresenter extends MvpPresenter<FavoriteView> {
    private final RemoveFavoriteWordPairInteractor mRemoveFavoriteWordPairInteractor;
    private final GetFavoriteWordPairsInteractor mGetFavoriteWordPairsInteractor;
    private final WordPairToViewModelMapper mWordPairToViewModelMapper;
    private final ViewModelToWordPairMapper mViewModelToWordPairMapper;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    public FavoritePresenter(RemoveFavoriteWordPairInteractor mRemoveFavoriteWordPairInteractor,
                             GetFavoriteWordPairsInteractor mGetFavoriteWordPairsInteractor,
                             WordPairToViewModelMapper mWordPairToViewModelMapper,
                             ViewModelToWordPairMapper mViewModelToWordPairMapper) {
        this.mRemoveFavoriteWordPairInteractor = mRemoveFavoriteWordPairInteractor;
        this.mGetFavoriteWordPairsInteractor = mGetFavoriteWordPairsInteractor;
        this.mWordPairToViewModelMapper = mWordPairToViewModelMapper;
        this.mViewModelToWordPairMapper = mViewModelToWordPairMapper;
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
                .map(mWordPairToViewModelMapper::transform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wordPairViewModels -> getViewState().setWords(wordPairViewModels));
        mCompositeDisposable.add(d);
    }


    public void onWordPairClicked(WordPairViewModel viewModel, int position) {
        WordPair wordPair = mViewModelToWordPairMapper.transform(viewModel);
        Disposable d = Completable.fromAction(() -> mRemoveFavoriteWordPairInteractor.removeFavorite(wordPair))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getViewState().deleteWord(position))
                .subscribe();
        mCompositeDisposable.add(d);
    }
}
