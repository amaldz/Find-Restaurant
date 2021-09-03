package com.dz.restaurant.interfaces;

import java.util.HashMap;

public interface CallbackDelegate {
    void onResultReceived(String type, boolean resultCode, HashMap<String, String> extras);
}
