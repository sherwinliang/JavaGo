package design.mode;

public class UserDaoProxy {
    private IUserDao userDao;

    public void save(Object o){
        userDao = new UserDao();
        userDao.save(o);
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
}
