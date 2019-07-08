package ru.ftc.android.shifttemple.features.login.data;

public final class SessionRepositoryImpl implements SessionRepository {

    private final SessionDataSource dataSource;

    public SessionRepositoryImpl(SessionDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getSessionId() {
        return dataSource.getSessionId();
    }

    @Override
    public Integer getUserId() {
        return dataSource.getUserId();
    }

    @Override
    public String getUserName() {
        return dataSource.getUserName();
    }

    @Override
    public Integer getUserAge() {
        return dataSource.getUserAge();
    }

    @Override
    public String getUserCity() {
        return dataSource.getUserCity();
    }

    @Override
    public String getUserPicUrl() {
        return dataSource.getUserPicUrl();
    }

    @Override
    public String getUserPhone() {
        return dataSource.getUserPhone();
    }

    @Override
    public Integer getUserBalance() {
        return dataSource.getUserBalance();
    }

    @Override
    public Boolean getUserStatus() {
        return dataSource.getUserStatus();
    }

    @Override
    public String getUserDescription() {
        return dataSource.getUserDescription();
    }

    @Override
    public void setSessionId(String sessionId) {
        dataSource.setSessionId(sessionId);
    }

    @Override
    public void setUserId(Integer id) {
        dataSource.setUserId(id);
    }

    @Override
    public void setUserName(String name) {
        dataSource.setUserName(name);
    }

    @Override
    public void setUserAge(Integer age){
        dataSource.setUserAge(age);
    }

    @Override
    public void setUserCity(String city){
        dataSource.setUserCity(city);
    }

    @Override
    public void setUserPicUrl(String picUrl){
        dataSource.setUserPicUrl(picUrl);
    }

    @Override
    public void setUserPhone(String phone){
        dataSource.setUserPhone(phone);
    }

    @Override
    public void setUserBalance(Integer balance){
        dataSource.setUserBalance(balance);
    }

    @Override
    public void setUserStatus(Boolean status){
        dataSource.setUserStatus(status);
    }

    @Override
    public void setUserDescription(String description){
        dataSource.setUserDescription(description);
    }
}