package design.mode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao implements IUserDao {
    private static final Logger logger = LogManager.getLogger(UserDao.class);
    @Override
    public void save(Object pojo) {
        logger.error("Save object success.");
    }
}
