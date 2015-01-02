package takuma.tutorial.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.DatabaseResults;

public interface BaseDAOInterface<T, ID> extends Dao<T, ID> {
    public void initializeOrFail();
    
    public T queryForIdOrFail(ID id);

    public T queryForFirstOrFail(PreparedQuery<T> preparedQuery);

    public List<T> queryForAllOrFail();

    public List<T> queryForEqOrFail(String fieldName, Object value);

    public List<T> queryOrFail(PreparedQuery<T> preparedQuery);

    public List<T> queryForMatchingOrFail(T matchObj);

    public List<T> queryForMatchingArgsOrFail(T matchObj);

    public List<T> queryForFieldValuesOrFail(Map<String, Object> fieldValues);

    public List<T> queryForFieldValuesArgsOrFail(Map<String, Object> fieldValues);

    public T queryForSameIdOrFail(T data);

    public int createOrFail(T data);

    public T createIfNotExistsOrFail(T data);

    public CreateOrUpdateStatus createOrUpdateOrFail(T data);

    public int updateOrFail(T data);

    public int updateIdOrFail(T data, ID newId);

    public int updateOrFail(PreparedUpdate<T> preparedUpdate);

    public int refreshOrFail(T data);

    public int deleteOrFail(T data);

    public int deleteByIdOrFail(ID id);

    public int deleteOrFail(Collection<T> datas);

    public int deleteIdsOrFail(Collection<ID> ids);

    public int deleteOrFail(PreparedDelete<T> preparedDelete);

    public void closeLastIteratorOrFail();

    public CloseableIterator<T> iteratorOrFail(PreparedQuery<T> preparedQuery); 

    public CloseableIterator<T> iteratorOrFail(PreparedQuery<T> preparedQuery, int resultFlags);


    public GenericRawResults<String[]> queryRawOrFail(String query, String... arguments);

    public <GR> GenericRawResults<GR> queryRawOrFail(String query, RawRowMapper<GR> mapper,
            String... arguments);

    public GenericRawResults<Object[]> queryRawOrFail(String query, DataType[] columnTypes,
            String... arguments);

    public int executeRawOrFail(String statement, String... arguments);
    
    public int updateRawOrFail(String statement, String... arguments);

    public boolean objectsEqualOrFail(T data1, T data2);

    public ID extractIdOrFail(T data);

    public boolean isTableExistsOrFail();

    public long countOfOrFail();

    public long countOfOrFail(PreparedQuery<T> preparedQuery);

    public <FT> ForeignCollection<FT> getEmptyForeignCollectionOrFail(String fieldName);

    public void setObjectCacheOrFail(boolean enabled);

    public void setObjectCacheOrFail(ObjectCache objectCache);

    public T mapSelectStarRowOrFail(DatabaseResults results);

    public GenericRowMapper<T> getSelectStarRowMapperOrFail();

}
