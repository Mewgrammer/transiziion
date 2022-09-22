package github.mewgrammer.transizion.statemachine.context

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import java.util.*
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "executions")
@Entity
class Execution {
    @Id
    val id = UUID.randomUUID()

    @Column
    @Basic
    var name: String? = null

    @CreatedDate
    lateinit var createdAt: Instant
}