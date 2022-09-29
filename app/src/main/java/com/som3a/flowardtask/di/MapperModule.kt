package com.som3a.flowardtask.di

import com.som3a.common.Mapper
import com.som3a.domain.entity.User
import com.som3a.feature.mapper.UserDomainUiMapper
import com.som3a.feature.model.UserUiModel
import com.som3a.remote.mapper.UserRemoteDataMapper
import com.som3a.remote.model.UserRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindUserRemoteDataMapper(mapper: UserRemoteDataMapper): Mapper<UserRemote, User>

    @Binds
    abstract fun bindsUserDomainUiMapper(mapper: UserDomainUiMapper): Mapper<User, UserUiModel>

}