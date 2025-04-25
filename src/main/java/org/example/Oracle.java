package org.example;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.oracle.CreateOption;
import dev.langchain4j.store.embedding.oracle.OracleEmbeddingStore;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import java.sql.SQLException;

/**
 * Inspired from <a href="https://medium.com/@timju/chromadb-in-java-langchain4j-41ed910cd3e7">ChromaDB in Java (langchain4j 🦜)</a>.
 *
 * @author Loïc Lefèvre
 */
public class Oracle {
	/**
	 * Data source for Oracle database 23ai.
	 */
	public static final PoolDataSource dataSource = PoolDataSourceFactory.getPoolDataSource();

	static {
		try {
			// Data source configuration
			dataSource.setConnectionFactoryClassName("oracle.jdbc.datasource.impl.OracleDataSource");

			// Use environment variables for CS (Connection String) and credential
			// format: jdbc:oracle:thin:@cs
			System.out.printf("Connecting to Database %s\n", System.getenv("cs"));
			dataSource.setURL("jdbc:oracle:thin:@"+System.getenv("cs"));
			dataSource.setUser(System.getenv("dbUser"));
			dataSource.setPassword(System.getenv("myPwd"));
		}
		catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}

	public static final EmbeddingStore<TextSegment> embeddingStore =
			OracleEmbeddingStore.builder()
					.dataSource(dataSource)
					.embeddingTable(
							"my_embeddings",
							CreateOption.CREATE_IF_NOT_EXISTS)
					.build();

	public static final EmbeddingModel embeddingModel = new AllMiniLmL6V2QuantizedEmbeddingModel();
}
