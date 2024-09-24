package com.wooyj.picsum.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.wooyj.picsum.data.repository.local.LocalFavoriteRepository
import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import com.wooyj.picsum.data.repository.local.LocalPicSumWithFavRepository
import com.wooyj.picsum.data.repository.paging.PagingPicSumRepository
import com.wooyj.picsum.data.repository.remote.RemotePicSumRepository
import com.wooyj.picsum.domain.usecase.detail.GetFavNextIdUseCase
import com.wooyj.picsum.domain.usecase.detail.GetFavPrevIdUseCase
import com.wooyj.picsum.domain.usecase.detail.GetPicSumItemUseCase
import com.wooyj.picsum.domain.usecase.detail.RemoteGetPicSumItemUseCase
import com.wooyj.picsum.domain.usecase.favorite.AddFavoriteUseCase
import com.wooyj.picsum.domain.usecase.favorite.GetFavoriteUseCase
import com.wooyj.picsum.domain.usecase.favorite.IsFavoriteItemUseCase
import com.wooyj.picsum.domain.usecase.favorite.LocalPicSumFavListUseCase
import com.wooyj.picsum.domain.usecase.favorite.RemoveFavoriteNotVisibleUseCase
import com.wooyj.picsum.domain.usecase.favorite.RemoveFavoriteUseCase
import com.wooyj.picsum.domain.usecase.favorite.UpdateFavoriteUseCase
import com.wooyj.picsum.domain.usecase.list.FavoriteListUseCase
import com.wooyj.picsum.domain.usecase.list.FavoriteVisibleListUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetNextIdUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPicSumItemUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalGetPrevIdUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalPicSumListUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.LocalSavePicSumItemUseCase
import com.wooyj.picsum.domain.usecase.local.picsum.PicSumListPagingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetPicSumItemUseCase(repository: RemotePicSumRepository): GetPicSumItemUseCase =
        GetPicSumItemUseCase { id -> repository.getPicSumItem(id) }

    @Provides
    @ViewModelScoped
    fun provideRemoveFavoriteNotVisibleUseCase(repository: LocalFavoriteRepository): RemoveFavoriteNotVisibleUseCase =
        RemoveFavoriteNotVisibleUseCase { repository.removeFavoriteNotVisible() }

    @Provides
    @ViewModelScoped
    fun provideAddFavoriteUseCase(repository: LocalFavoriteRepository): AddFavoriteUseCase =
        AddFavoriteUseCase { entity -> repository.addFavorite(entity) }

    @Provides
    @ViewModelScoped
    fun provideGetFavoriteUseCase(repository: LocalFavoriteRepository): GetFavoriteUseCase =
        GetFavoriteUseCase { id -> repository.getFavoriteItem(id) }

    @Provides
    @ViewModelScoped
    fun provideIsFavoriteItemUseCase(repository: LocalFavoriteRepository): IsFavoriteItemUseCase =
        IsFavoriteItemUseCase { id -> repository.added(id) }

    @Provides
    @ViewModelScoped
    fun provideRemoveFavoriteUseCase(repository: LocalFavoriteRepository): RemoveFavoriteUseCase =
        RemoveFavoriteUseCase { id -> repository.removeFavorite(id) }

    @Provides
    @ViewModelScoped
    fun provideUpdateFavoriteUseCase(repository: LocalFavoriteRepository): UpdateFavoriteUseCase =
        UpdateFavoriteUseCase { entity -> repository.updateFavorite(entity) }

    @Provides
    @ViewModelScoped
    fun provideFavoriteListUseCase(repository: LocalFavoriteRepository): FavoriteListUseCase =
        FavoriteListUseCase { repository.getFavoriteList() }

    @Provides
    @ViewModelScoped
    fun provideFavoriteVisibleListUseCase(repository: LocalFavoriteRepository): FavoriteVisibleListUseCase =
        FavoriteVisibleListUseCase { repository.getVisibleFavoriteList() }

    @Provides
    @ViewModelScoped
    fun provideRemoteGetPicSumItemUseCase(repository: RemotePicSumRepository): RemoteGetPicSumItemUseCase =
        RemoteGetPicSumItemUseCase { id -> repository.getPicSumItem(id) }

    @Provides
    @ViewModelScoped
    fun provideLocalPicSumFavListUseCase(repository: LocalPicSumWithFavRepository): LocalPicSumFavListUseCase =
        LocalPicSumFavListUseCase { repository.getPicSumWithFavoriteList() }

    @Provides
    @ViewModelScoped
    fun provideGetFavNextIdUseCase(repository: LocalFavoriteRepository): GetFavNextIdUseCase =
        GetFavNextIdUseCase { id -> repository.getNextId(id) }

    @Provides
    @ViewModelScoped
    fun provideLocalGetNextIdUseCase(repository: LocalPicSumRepository): LocalGetNextIdUseCase =
        LocalGetNextIdUseCase { id -> repository.getNextId(id) }

    @Provides
    @ViewModelScoped
    fun provideLocalGetPicSumItemUseCase(repository: LocalPicSumRepository): LocalGetPicSumItemUseCase =
        LocalGetPicSumItemUseCase { id -> repository.getPicSumItem(id) }

    @Provides
    @ViewModelScoped
    fun provideLocalGetPrevIdUseCase(repository: LocalPicSumRepository): LocalGetPrevIdUseCase =
        LocalGetPrevIdUseCase { id -> repository.getPrevId(id) }

    @Provides
    @ViewModelScoped
    fun provideLocalPicSumListUseCase(repository: LocalPicSumRepository): LocalPicSumListUseCase =
        LocalPicSumListUseCase { repository.getPicSumList() }

    @Provides
    @ViewModelScoped
    fun provideLocalPicSumRepository(repository: LocalPicSumRepository): LocalSavePicSumItemUseCase =
        LocalSavePicSumItemUseCase { entity -> repository.insert(entity) }

    @Provides
    @ViewModelScoped
    fun provideGetFavPrevIdUseCase(repository: LocalFavoriteRepository): GetFavPrevIdUseCase =
        GetFavPrevIdUseCase { id -> repository.getPrevId(id) }

    @Provides
    @ViewModelScoped
    fun providePicSumListPagingUseCase(repository: PagingPicSumRepository): PicSumListPagingUseCase =
        PicSumListPagingUseCase { limit ->
            Pager(
                PagingConfig(
                    pageSize = limit,
                    enablePlaceholders = false,
                ),
                pagingSourceFactory = {
                    repository.getPicSumPagingSource()
                },
            ).flow
        }
}
