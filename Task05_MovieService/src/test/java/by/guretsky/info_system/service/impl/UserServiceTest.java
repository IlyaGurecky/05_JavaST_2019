package by.guretsky.info_system.service.impl;

import by.guretsky.info_system.dao.connection.ConnectionPool;
import by.guretsky.info_system.dao.impl.DaoManagerFactoryImpl;
import by.guretsky.info_system.entity.User;
import by.guretsky.info_system.entity.role.Role;
import by.guretsky.info_system.exception.CustomException;
import by.guretsky.info_system.service.UserService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserServiceTest {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_USER = "movie_service_user";
    private static final String DB_PASSWORD = "password";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/movie_service_db?useSSL=false"
                    + "&allowPublicKeyRetrieval=true";
    private static final int TIMEOUT = 0;
    private static final int START_POOL_SIZE = 15;
    private static final int MAX_POOL_SIZE = 700;

    private UserService service;
    private User expectedUser = new User();

    @BeforeClass
    public void init() throws CustomException {
        ConnectionPool.getInstance().initialize(DB_USER, DB_PASSWORD, DB_URL,
                START_POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, DB_DRIVER);
        service = new ServiceFactoryImpl(new DaoManagerFactoryImpl())
                .createService(UserService.class);
        expectedUser.setRole(Role.ADMIN);
        expectedUser.setLogin("admin");
        expectedUser.setEmail("oracle@gmail.com");
        expectedUser.setSex("м");
        expectedUser.setCountry("Россия");
        expectedUser.setId(1);
    }

    @Test(description = "Positive test for findById method")
    public void testFindById() throws CustomException {
        User actualUser = service.findById(1);
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(description = "Positive test for findByEmail method")
    public void testFindByEmail() throws CustomException {
        User actualUser = service.findByEmail("oracle@gmail.com");
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(description = "Positive test for countUsers method")
    public void testCountUsers() throws CustomException {
        final Integer amount = 4;
        Assert.assertEquals(service.countUsers(), amount);
    }

    @Test(description = "Negative test for findByEmail method",
            expectedExceptions = CustomException.class)
    public void testFindByEmail2() throws CustomException {
        User actualUser = service.findByEmail(null);
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(description = "Negative test for findById method",
            expectedExceptions = CustomException.class)
    public void testFindById2() throws CustomException {
        User actualUser = service.findById(null);
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(description = "Negative test for findByEmail method",
            expectedExceptions = CustomException.class)
    public void testFindByEmail3() throws CustomException {
        User actualUser = service.findByEmail("");
        Assert.assertEquals(actualUser, expectedUser);
    }

    @AfterClass
    public void invalidatePool() {
        ConnectionPool.getInstance().invalidate();
    }
}
