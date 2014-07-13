package takuma.tutorial.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import takuma.tutorial.database.DatabaseUtil;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseResults;
import com.j256.ormlite.table.DatabaseTableConfig;

public class BaseDAO<T, ID> extends BaseDaoImpl<T, ID> implements BaseDAOInterface<T, ID> {
	
    private static final String TAG = BaseDAO.class.getSimpleName();

    public BaseDAO(Class<T> dataClass) throws SQLException {
        super(dataClass);
    }

    public BaseDAO(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public BaseDAO(ConnectionSource connectionSource, DatabaseTableConfig<T> tableConfig)
            throws SQLException {
        super(connectionSource, tableConfig);
    }

    /**
     * Initialize the various DAO configurations after the various setters have been called.
     */
    public void initializeOrFail() {
        try {
            initialize();
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
    }

    public T queryForIdOrFail(ID id) {
        T result = null;
        try {
            result = queryForId(id);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public T queryForFirstOrFail(PreparedQuery<T> preparedQuery) {
        T result = null;
        try {
            result = queryForFirst(preparedQuery);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public List<T> queryForAllOrFail() {
        List<T> result = null;
        try {
            result = queryForAll();
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public List<T> queryForEqOrFail(String fieldName, Object value) {
        List<T> result = null;
        try {
            result = queryForEq(fieldName, value);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public List<T> queryOrFail(PreparedQuery<T> preparedQuery) {
        List<T> result = null;
        try {
            result = query(preparedQuery);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public List<T> queryForMatchingOrFail(T matchObj) {
        List<T> result = null;
        try {
            result = queryForMatching(matchObj);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public List<T> queryForMatchingArgsOrFail(T matchObj) {
        List<T> result = null;
        try {
            result = queryForMatchingArgs(matchObj);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public List<T> queryForFieldValuesOrFail(Map<String, Object> fieldValues) {
        List<T> result = null;
        try {
            result = queryForFieldValues(fieldValues);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public List<T> queryForFieldValuesArgsOrFail(Map<String, Object> fieldValues) {
        List<T> result = null;
        try {
            result = queryForFieldValuesArgs(fieldValues);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public T queryForSameIdOrFail(T data) {
        T result = null;
        try {
            result = queryForSameId(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int createOrFail(T data) {
        int result = -1;
        try {
            result = create(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public T createIfNotExistsOrFail(T data) {
        T result = null;
        try {
            result = createIfNotExists(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public CreateOrUpdateStatus createOrUpdateOrFail(T data) {
        CreateOrUpdateStatus result = null;
        try {
            result = createOrUpdate(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int updateOrFail(T data) {
        int result = -1;
        try {
            result = update(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int updateIdOrFail(T data, ID newId) {
        int result = -1;
        try {
            result = updateId(data, newId);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int updateOrFail(PreparedUpdate<T> preparedUpdate) {
        int result = -1;
        try {
            result = update(preparedUpdate);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int refreshOrFail(T data) {
        int result = -1;
        try {
            result = refresh(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int deleteOrFail(T data) {
        int result = -1;
        try {
            result = delete(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int deleteByIdOrFail(ID id) {
        int result = -1;
        try {
            result = deleteById(id);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int deleteOrFail(Collection<T> datas) {
        int result = -1;
        try {
            result = delete(datas);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int deleteIdsOrFail(Collection<ID> ids) {
        int result = -1;
        try {
            result = deleteIds(ids);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int deleteOrFail(PreparedDelete<T> preparedDelete) {
        int result = -1;
        try {
            result = delete(preparedDelete);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public void closeLastIteratorOrFail() {
        try {
            closeLastIterator();
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
    }

    public CloseableIterator<T> iteratorOrFail(PreparedQuery<T> preparedQuery) {
        CloseableIterator<T> result = null;
        try {
            result = iterator(preparedQuery);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public CloseableIterator<T> iteratorOrFail(PreparedQuery<T> preparedQuery, int resultFlags) {
        CloseableIterator<T> result = null;
        try {
            result = iterator(preparedQuery, resultFlags);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public GenericRawResults<String[]> queryRawOrFail(String query, String... arguments) {
        GenericRawResults<String[]> result = null;
        try {
            result = queryRaw(query, arguments);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public <GR> GenericRawResults<GR> queryRawOrFail(String query, RawRowMapper<GR> mapper,
            String... arguments) {
        GenericRawResults<GR> result = null;
        try {
            result = queryRaw(query, mapper, arguments);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public GenericRawResults<Object[]> queryRawOrFail(String query, DataType[] columnTypes,
            String... arguments) {
        GenericRawResults<Object[]> result = null;
        try {
            result = queryRaw(query, columnTypes, arguments);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int executeRawOrFail(String statement, String... arguments) {
        int result = -1;
        try {
            result = executeRaw(statement, arguments);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public int updateRawOrFail(String statement, String... arguments) {
        int result = -1;
        try {
            result = updateRaw(statement, arguments);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public boolean objectsEqualOrFail(T data1, T data2) {
        boolean result = false;
        try {
            result = objectsEqual(data1, data2);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public ID extractIdOrFail(T data) {
        ID id = null;
        try {
            id = extractId(data);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return id;
    }

    public boolean isTableExistsOrFail() {
        boolean result = false;
        try {
            result = isTableExists();
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public long countOfOrFail() {
        long result = -1;
        try {
            result = countOf();
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public long countOfOrFail(PreparedQuery<T> preparedQuery) {
        long result = -1;
        try {
            result = countOf(preparedQuery);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public <FT> ForeignCollection<FT> getEmptyForeignCollectionOrFail(String fieldName) {
        ForeignCollection<FT> result = null;
        try {
            result = getEmptyForeignCollection(fieldName);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public void setObjectCacheOrFail(boolean enabled) {
        try {
            setObjectCache(enabled);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
    }

    public void setObjectCacheOrFail(ObjectCache objectCache) {
        try {
            setObjectCache(objectCache);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
    }

    public T mapSelectStarRowOrFail(DatabaseResults results) {
        T result = null;
        try {
            result = mapSelectStarRow(results);
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }

    public GenericRowMapper<T> getSelectStarRowMapperOrFail() {
        GenericRowMapper<T> result = null;
        try {
            result = getSelectStarRowMapper();
        } catch (SQLException e) {
            DatabaseUtil.throwAndroidSQLException(TAG, e);
        }
        return result;
    }
}
