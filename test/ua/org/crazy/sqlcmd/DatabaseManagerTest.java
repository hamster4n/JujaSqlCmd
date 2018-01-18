package ua.org.crazy.sqlcmd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import ua.org.crazy.sqlcmd.util.DatabaseManager;

import java.sql.SQLException;
import java.util.Arrays;

public class DatabaseManagerTest {
    private DatabaseManager databaseManager;

    @Before
    public void setup(){
        databaseManager = new DatabaseManager();
    }

    @Test
    public void testGetAllTableNames() throws SQLException {
        String expectedTableNames = "[test1, users]";

        String[] tableNames = databaseManager.getTableNames();
        String actualTableNames = Arrays.toString(tableNames);

        assertEquals(expectedTableNames, actualTableNames);
    }
}
