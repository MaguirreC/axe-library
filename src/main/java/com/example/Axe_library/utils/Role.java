package com.example.Axe_library.utils;

import java.util.Arrays;
import java.util.List;

public enum Role {

    ADMINISTRATOR(Arrays.asList(Permission.READ_ALL_BOOKS,Permission.SAVE_ONE_BOOK,Permission.CREATE_ONE_CLIENT)),

    CUSTOMER(Arrays.asList(Permission.READ_ALL_BOOKS));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
