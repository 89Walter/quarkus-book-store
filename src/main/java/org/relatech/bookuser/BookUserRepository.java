package org.relatech.bookuser;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class BookUserRepository implements PanacheRepository<BookUser> {

}
