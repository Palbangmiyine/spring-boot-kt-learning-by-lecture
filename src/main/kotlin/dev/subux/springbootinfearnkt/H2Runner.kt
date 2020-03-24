package dev.subux.springbootinfearnkt

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.Statement
import javax.sql.DataSource

@Component
class H2Runner(
    private val dataSource: DataSource,
    private val  jdbcTemplate: JdbcTemplate
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val conn: Connection = dataSource.getConnection()
        val stmt: Statement = conn.createStatement()
        val sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (ID))"
        stmt.executeUpdate(sql)
        stmt.close()

        println(conn.metaData.userName)
        println(conn.metaData.url)

        jdbcTemplate.execute("INSERT INTO USER VALUES (1, 'subin')")
    }
}