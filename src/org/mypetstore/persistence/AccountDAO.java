package org.mypetstore.persistence;

import org.mypetstore.domain.Account;

/**
 * Created by Mr.Wan on 2016/3/19.
 */
public interface AccountDAO {

    Boolean signIn(String username,String password);

    Account getAccountByUsername(String username);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);
}
