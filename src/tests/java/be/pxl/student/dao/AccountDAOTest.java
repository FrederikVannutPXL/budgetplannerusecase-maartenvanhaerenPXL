package be.pxl.student.dao;

import be.pxl.student.entity.Account;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountDAOTest {
    private AccountDAO accountDAO=new AccountDAO("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "admin");
    private Account account = new Account();

    @Test
    public void testAccountInsert(){ //af
        account.setIBAN("testIBAN");
        account.setName("testName");

        Account accountInserted = accountDAO.createAccount(account);
        //assertEquals(account,accountInserted);
        assertNotEquals(0,accountInserted.getId());
        System.out.println("id: " + accountInserted.getId());
    }

    @Test
    public void testAccountUpdate(){ //under construction
        account.setIBAN("testIBAN");
        account.setName("testNameAangepast");
        account.setId(2);
        accountDAO.updateAccount(account);
        Account UpdatedAccount = accountDAO.readAccount(2);
        Assertions.assertEquals(UpdatedAccount.getName(), account.getName());
        System.out.println("Name:" + UpdatedAccount.getName() + ", IBAN: " + UpdatedAccount.getIBAN());

    }
    @Test
    public void testAccountDelete(){ //under construction
        account.setName("Delete");
        account.setIBAN("Delete");
        Account willBeDelet = accountDAO.createAccount(account);
        System.out.println("Id:" + willBeDelet.getId() +", name:"+ willBeDelet.getName());
        boolean isDelete = accountDAO.deleteAccount(willBeDelet.getId());
        Account deletedAccount = accountDAO.readAccount(willBeDelet.getId());
        Assertions.assertEquals(null, deletedAccount);
        System.out.println("uitkomst: " + deletedAccount);


    }
    @Test
    public void testAccountRead(){ //af
        Account accountRead = accountDAO.readAccount(1);
        assertNotEquals(null, accountRead);
        System.out.println("name: " + accountRead.getName());
        System.out.println("IBAN: " + accountRead.getIBAN());
    }

}
