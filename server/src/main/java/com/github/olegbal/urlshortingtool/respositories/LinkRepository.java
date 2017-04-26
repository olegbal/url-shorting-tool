package com.github.olegbal.urlshortingtool.respositories;

import com.github.olegbal.urlshortingtool.domain.entity.Link;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link,Long> {

}
