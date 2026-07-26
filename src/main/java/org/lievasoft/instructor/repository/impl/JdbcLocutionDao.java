package org.lievasoft.instructor.repository.impl;

import org.hibernate.sql.results.spi.NoRowException;
import org.lievasoft.instructor.dto.locution.DetailsLocutionResponse;
import org.lievasoft.instructor.repository.LocutionDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcLocutionDao implements LocutionDao {

	private final JdbcClient jdbcClient;

	public JdbcLocutionDao(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	@Override
	public DetailsLocutionResponse getDetailsLocutionResponseById(Long locutionId) {
		var sql = """
				SELECT l.id AS locution_id, l.sentence, t.text AS translation
				FROM locutions l
				INNER JOIN translations t
					ON l.id = t.locution_id
				WHERE l.id = :id
				""";

		RowMapper<DetailsLocutionResponse> rowMapper = (resultSet, _) -> {
			if (!resultSet.next())
				throw new NoRowException("Locution with ID %s not has been found".formatted(locutionId));
			else {
				var obtainedLocutionId = resultSet.getLong("locution_id");
				var sentence = resultSet.getString("sentence");
				List<String> translations = new ArrayList<>();
				do {
					var translation = resultSet.getString("translation");
					if (translation != null) translations.add(translation);
				} while (resultSet.next());
				return new DetailsLocutionResponse(obtainedLocutionId, sentence, translations);
			}
		};

		return jdbcClient.sql(sql)
				.param("id", locutionId)
				.query(rowMapper)
				.single();
	}
}
