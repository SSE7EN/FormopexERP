package com.wss.authservice.request;

import com.wss.common.model.EUserRole;

import java.util.ArrayList;
import java.util.List;

public class AppUserUpdateRequestModel extends AppUserCreateRequestModel {

    /**
     * Primary key
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
