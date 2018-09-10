package ru.pyrovsergey.technicaltask_cameraandstorage;



public class Presenter {
    private ViewContract viewContract;

    void onClickImageSelectButton() {
        viewContract.getPhoto();
    }

    void onClickImageRotateImageButton() {

    }

    void onClickImageInvertColorsButton() {

    }

    void onClickImageMirrorButton() {

    }


    public void onAttach(ViewContract viewContract) {
        this.viewContract = viewContract;
    }

    public void onDetach() {
        viewContract = null;
    }
}
