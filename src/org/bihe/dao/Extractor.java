package org.bihe.dao;

import java.sql.SQLException;

/**
 * This is functional interface that has extract method for extract object from database
 *
 * @param <T>
 */
public interface Extractor<T> {

	public T extract(java.sql.ResultSet rs) throws SQLException;
}
