package com.example.demo.application.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.search.jpa.FullTextQuery;

import com.example.demo.application.bean.Hero;
import com.example.demo.application.repository.HeroRepository;

@Service
public class HeroService {

	@Autowired
	private final HeroRepository heroRepository = null;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Hero> getHeroes() {
		return (List<Hero>) heroRepository.findAll();
	}

	public void addHero(Hero hero) {
		heroRepository.save(hero);
	}

	public void removeHero(Long id) {
		heroRepository.deleteById(id);
	}

	public List<Hero> searchHeroes(String name) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
														 .buildQueryBuilder()
														 .forEntity(Hero.class)
														 .get();
		
		List<Hero> results = this.fullTextWithLike(name, queryBuilder, fullTextEntityManager);

		return results;

	}

	private List<Hero> fullTextWithLike(String text, QueryBuilder queryBuilder,
			FullTextEntityManager fullTextEntityManager) {

		Query query = queryBuilder.keyword().wildcard().onFields("name").matching("*" + text + "*").createQuery();

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Hero.class);
		return jpaQuery.setMaxResults(10).getResultList();
	}
}
