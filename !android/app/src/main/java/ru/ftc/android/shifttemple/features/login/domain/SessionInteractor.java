package ru.ftc.android.shifttemple.features.login.domain;

public interface SessionInteractor {

    String getSessionId();

    Integer getUserId();

    String getUserName();

    Integer getUserAge();

    String getUserCity();

    String getUserPicUrl();

    String getUserPhone();

    Integer getUserBalance();

    Boolean getUserStatus();

    String getUserDescription();

    void setSessionId(String sessionId);

    void setUserId(Integer id);

    void setUserName(String name);

    void setUserAge(Integer age);

    void setUserCity(String city);

    void setUserPicUrl(String picUrl);

    void setUserPhone(String phone);

    void setUserBalance(Integer balance);

    void setUserStatus(Boolean status);

    void setUserDescription(String description);
}