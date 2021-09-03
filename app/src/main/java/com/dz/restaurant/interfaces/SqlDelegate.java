package com.dz.restaurant.interfaces;

import com.dz.restaurant.helpers.SqlHelper;

/**
 * Creates Response
 */

public interface SqlDelegate {
    void onResponse(SqlHelper sqlHelper);
}
