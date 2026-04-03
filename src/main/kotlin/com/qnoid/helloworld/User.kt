package com.qnoid.helloworld

import java.util.*
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull


@Entity
@Table(name = "\"User\"")
@NamedQuery(name = "user.list", query = "SELECT u FROM User u")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,

    @Column(unique=true)
    var identifier: UUID = @NotNull UUID.randomUUID()
)