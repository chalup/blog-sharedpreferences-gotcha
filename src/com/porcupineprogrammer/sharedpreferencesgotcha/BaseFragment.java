package com.porcupineprogrammer.sharedpreferencesgotcha;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public abstract class BaseFragment extends Fragment implements OnSharedPreferenceChangeListener {
  private SharedPreferences mPreferences;
  private Button mButton;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.base_fragment, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mButton = (Button) view.findViewById(R.id.button);
    mButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            mPreferences.edit().putInt(getSharedPreferencesKey(), getClickCount() + 1).commit();
          }
        }).start();
      }
    });
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
  }

  @Override
  public void onPause() {
    super.onPause();
    mPreferences.unregisterOnSharedPreferenceChangeListener(this);
  }

  @Override
  public void onResume() {
    super.onResume();
    mPreferences.registerOnSharedPreferenceChangeListener(this);
    updateButtonText();
  }

  protected abstract String getSharedPreferencesKey();

  protected void updateButtonText() {
    mButton.setText(getString(R.string.button_text, getClickCount()));
  }

  private int getClickCount() {
    return mPreferences.getInt(getSharedPreferencesKey(), 0);
  }
}