package ru.ftc.android.shifttemple.features.login.domain;

import ru.ftc.android.shifttemple.features.login.data.SessionRepository;

public final class SessionInteractorImpl implements SessionInteractor {

    private final SessionRepository sessionRepository;

    public SessionInteractorImpl(final SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public String getSessionId() {
        return sessionRepository.getSessionId();
    }

    @Override
    public Integer getUserId() {
        return sessionRepository.getUserId();
    }

    @Override
    public String getUserName() {
        return sessionRepository.getUserName();
    }

    @Override
    public Integer getUserAge() {
        return sessionRepository.getUserAge();
    }

    @Override
    public String getUserCity() {
        return sessionRepository.getUserCity();
    }

    @Override
    public String getUserPicUrl() {
        return sessionRepository.getUserPicUrl();
    }

    @Override
    public String getUserPhone() {
        return sessionRepository.getUserPhone();
    }

    @Override
    public Integer getUserBalance() {
        return sessionRepository.getUserBalance();
    }

    @Override
    public Boolean getUserStatus() {
        return sessionRepository.getUserStatus();
    }

    @Override
    public String getUserDescription() {
        return sessionRepository.getUserDescription();
    }

    @Override
    public void setSessionId(String sessionId) {
        sessionRepository.setSessionId(sessionId);
    }

    @Override
    public void setUserId(Integer id) {
        sessionRepository.setUserId(id);
    }

    @Override
    public void setUserName(String name) {
        sessionRepository.setUserName(name);
    }

    @Override
    public void setUserAge(Integer age){
        sessionRepository.setUserAge(age);
    }

    @Override
    public void setUserCity(String city){
        sessionRepository.setUserCity(city);
    }

    @Override
    public void setUserPicUrl(String picUrl){
        sessionRepository.setUserPicUrl(picUrl);
    }

    @Override
    public void setUserPhone(String phone){
        sessionRepository.setUserPhone(phone);
    }

    @Override
    public void setUserBalance(Integer balance){
        sessionRepository.setUserBalance(balance);
    }

    @Override
    public void setUserStatus(Boolean status){
       sessionRepository.setUserStatus(status);
    }

    @Override
    public void setUserDescription(String description){
        sessionRepository.setUserDescription(description);
    }

}