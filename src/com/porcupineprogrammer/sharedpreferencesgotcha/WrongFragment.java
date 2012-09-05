package com.porcupineprogrammer.sharedpreferencesgotcha;

import android.content.SharedPreferences;

public class WrongFragment extends BaseFragment {

  private static final String WRONG_FRAGMENT_KEY = "WRONG_FRAGMENT_KEY";

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    if (key.equals(getSharedPreferencesKey())) {
      updateButtonText();
    }
  }

  @Override
  protected String getSharedPreferencesKey() {
    return WRONG_FRAGMENT_KEY;
  }

}
