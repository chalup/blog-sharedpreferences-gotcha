package com.porcupineprogrammer.sharedpreferencesgotcha;

import android.content.SharedPreferences;

public class RightFragment extends BaseFragment {

  private static final String RIGHT_FRAGMENT_KEY = "RIGHT_FRAGMENT_KEY";

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    if (key.equals(getSharedPreferencesKey())) {
      getActivity().runOnUiThread(new Runnable() {
        @Override
        public void run() {
          updateButtonText();
        }
      });
    }
  }

  @Override
  protected String getSharedPreferencesKey() {
    return RIGHT_FRAGMENT_KEY;
  }

}
