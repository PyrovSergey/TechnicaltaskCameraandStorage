package ru.pyrovsergey.technicaltask_cameraandstorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {
    @Singleton
    @Provides
    Presenter providePresenter() {
        return new Presenter();
    }
}
