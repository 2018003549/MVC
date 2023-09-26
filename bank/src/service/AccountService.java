package service;

public interface AccountService {
    public void transfer(String fromActno, String toActno, double money) throws RuntimeException;
}
