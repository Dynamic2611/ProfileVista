package com.ankit.profilevista.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromAddress(Address address) {
        return address == null ? null : gson.toJson(address);
    }

    @TypeConverter
    public static Address toAddress(String data) {
        if (data == null) return null;
        Type type = new TypeToken<Address>(){}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String fromCompany(Company company) {
        return company == null ? null : gson.toJson(company);
    }

    @TypeConverter
    public static Company toCompany(String data) {
        if (data == null) return null;
        Type type = new TypeToken<Company>(){}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String fromBank(Bank bank) {
        return bank == null ? null : gson.toJson(bank);
    }

    @TypeConverter
    public static Bank toBank(String data) {
        if (data == null) return null;
        Type type = new TypeToken<Bank>(){}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String fromHair(Hair hair) {
        return hair == null ? null : gson.toJson(hair);
    }

    @TypeConverter
    public static Hair toHair(String data) {
        if (data == null) return null;
        Type type = new TypeToken<Hair>(){}.getType();
        return gson.fromJson(data, type);
    }
}
