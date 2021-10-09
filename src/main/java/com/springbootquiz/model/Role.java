package com.springbootquiz.model;


public enum Role {
    USER, //default
    ADMIN, // admin manager, CRUD
    SUPER_ADMIN,//control admin , update role user to admin
    SYSTEM_MANAGER // full control.
}
