package ca.sunshineboys.it.cropmanagementsystem.ui.AirHumidity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/*
Sivajan Manikavasagar (Team Leader) N01240148
Muhammad Qamar N01344609
Noha Philips N01351336
Tanvir Pahwa N01245843
CENG 322 - RNC/D
CENG 317 - 0NF
 */
public class HumidityViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HumidityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Placeholder");
    }

    public LiveData<String> getText() {
        return mText;
    }
}