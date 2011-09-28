package org.guardian;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import org.guardian.entries.DataEntry;
import org.guardian.params.QueryParams;

public interface DatabaseBridge extends Closeable
{
	/**
	 * Returns all log matching specified parameters. Database specific implementing of getLog()
	 **/
	List<DataEntry> getEntries(QueryParams params);

	/**
	 * Performs a single insert.
	 **/
	void addEntry(DataEntry entry);

	/**
	 * Deletes all entries from database matching specified parameters.
	 **/
	void deleteEntries(QueryParams params);

	/**
	 * Deletes entries from database.
	 * 
	 * @param entries
	 * DataEntries to be deleted. Id must be set.
	 **/
	void deleteEntries(List<DataEntry> entries);

	/**
	 * Checks whether the DBMS runs/works.
	 **/
	boolean test() throws Exception;

	/**
	 * Creates tables if necessary
	 **/
	boolean checkTables() throws SQLException;
}
