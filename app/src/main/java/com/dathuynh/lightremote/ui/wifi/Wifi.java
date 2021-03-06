package com.dathuynh.lightremote.ui.wifi;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dathuynh.lightremote.R;
import com.dathuynh.lightremote.utils.Storage;
import com.dathuynh.lightremote.utils.api.API;
import com.dathuynh.lightremote.utils.api.APIService;
import com.dathuynh.lightremote.utils.api.ResponseModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wifi extends Fragment implements View.OnClickListener {

  // DECLARE VARIABLES
  private static String TAG = "Wifi Remote";
  private String SERVER_API_URL = new String("");
  private WifiViewModel mViewModel;
  private FloatingActionButton lightButton1;
  private FloatingActionButton lightButton2;
  private FloatingActionButton connectButton;

  private Boolean light1;
  private Boolean light2;

  public static Wifi newInstance() {
    return new Wifi();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.wifi_fragment, container, false);

    // INITIALIZE
    initView(view);
    initVariable();
    initListener();

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this).get(WifiViewModel.class);

    getServerIPAddress();
    getLightStatus();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.wifi_button_func1:
        onLight1Click();
        break;

      case R.id.wifi_button_func2:
        onLight2Click();
        break;

      case R.id.wifi_button_connect:
        getLightStatus();
        break;

      default:
        pushNotify("Command not found!");
        break;
    }
  }

  // INIT
  private void initView(View view) {
    lightButton1 = view.findViewById(R.id.wifi_button_func1);
    lightButton2 = view.findViewById(R.id.wifi_button_func2);
    connectButton = view.findViewById(R.id.wifi_button_connect);

    lightButton1.setImageResource(R.drawable.ic_led_off);
    lightButton2.setImageResource(R.drawable.ic_fan_off);
    connectButton.setImageResource(R.drawable.ic_refresh);
  }

  private void initListener() {
    lightButton1.setOnClickListener(this);
    lightButton2.setOnClickListener(this);
    connectButton.setOnClickListener(this);
  }

  private void initVariable() {
    light1 = false;
    light2 = false;
  }

  // HANDLER
  private void onLight1Click() {
    light1 = !light1;
    setLightStatus(1, light1);

    if (light1) {
      lightButton1.setImageResource(R.drawable.ic_led_on);
    } else {
      lightButton1.setImageResource(R.drawable.ic_led_off);
    }
  }

  private void onLight2Click() {
    light2 = !light2;
    setLightStatus(2, light2);

    if (light2) {
      lightButton2.setImageResource(R.drawable.ic_fan_on);
    } else {
      lightButton2.setImageResource(R.drawable.ic_fan_off);
    }
  }

  private void updateLightStatus(int light, boolean status) {
    switch (light) {
      case 1:
        light1 = status;
        if (light1) {
          lightButton1.setImageResource(R.drawable.ic_led_on);
        } else {
          lightButton1.setImageResource(R.drawable.ic_led_off);
        }
        break;

      case 2:
        light2 = status;
        if (light2) {
          lightButton2.setImageResource(R.drawable.ic_fan_on);
        } else {
          lightButton2.setImageResource(R.drawable.ic_fan_off);
        }
        break;

      default:
        break;
    }
  }

  // UTILS
  private void getLightStatus() {
    API api = APIService.createAPIService(SERVER_API_URL);
    Call<ResponseModel> callback = api.getLightStatus();

    callback.enqueue(new Callback<ResponseModel>() {
      @Override
      public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
        if (response != null) {
          ResponseModel data = response.body();

          boolean light1 = data.getLight1();
          boolean light2 = data.getLight2();

          updateLightStatus(1, light1);
          updateLightStatus(2, light2);
        }
      }

      @Override
      public void onFailure(Call<ResponseModel> call, Throwable t) {
        Log.d(TAG, t.getMessage());
        pushNotify("Please check internet connection!");
      }
    });
  }

  private void setLightStatus(int light, boolean status) {
    API api = APIService.createAPIService(SERVER_API_URL);
    Call<ResponseModel> callback;

    String param = new String();

    if (status) {
      param = "true";
    } else {
      param = "false";
    }

    switch (light) {
      case 1:
        callback = api.updateLight1Status(param);
        break;
      case 2:
        callback = api.updateLight2Status(param);
        break;
      default:
        callback = null;
        break;
    }

    callback.enqueue(new Callback<ResponseModel>() {
      @Override
      public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
        if (response != null) {
          ResponseModel data = response.body();

          boolean light1 = data.getLight1();
          boolean light2 = data.getLight2();

          updateLightStatus(1, light1);
          updateLightStatus(2, light2);
        }
      }

      @Override
      public void onFailure(Call<ResponseModel> call, Throwable t) {
        Log.d(TAG, t.getMessage());
      }
    });
  }

  private String getServerIPAddress() {
    String address = new Storage(getContext()).getServerIp();

    if (!address.equals("")) {
      address = "http://" + address;
    }

    SERVER_API_URL = address;
    return address;
  }

  private void pushNotify(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }
}
