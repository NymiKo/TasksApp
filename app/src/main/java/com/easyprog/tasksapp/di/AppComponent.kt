package com.easyprog.tasksapp.di

import com.easyprog.tasksapp.presenters.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ProviderModule::class, RemoteModule::class, RepositoryModule::class, RoomModule::class])
@Singleton
interface AppComponent {

    // Presenters
    fun inject(presenter: LoginUserPresenter)
    fun inject(presenter: CreateTaskPresenter)
    fun inject(presenter: ChangePasswordPresenter)
    fun inject(presenter: EditPersonalDataPresenter)
    fun inject(presenter: SplashScreenPresenter)
    fun inject(presenter: ProfilePresenter)
    fun inject(presenter: UserActivityPresenter)
}