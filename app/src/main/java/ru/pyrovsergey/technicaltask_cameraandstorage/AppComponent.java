package ru.pyrovsergey.technicaltask_cameraandstorage;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PresenterModule.class})
public interface AppComponent {
    Presenter getPresenter();
}
