package com.ankit.profilevista.network;

import com.ankit.profilevista.model.User;
import java.util.List;

public class UsersResponse {
    public List<User> users;
    public int total;
    public int skip;
    public int limit;
}
