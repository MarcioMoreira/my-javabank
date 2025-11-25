package io.codeforall.bootcamp.javabank.persistence.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.GenericXmlApplicationContext;


public class JpaIntegrationTestHelper {

    protected EntityManagerFactory emf;
    protected EntityManager em;
    private GenericXmlApplicationContext ctx;

    @Before
    public void init() {

        ctx = new GenericXmlApplicationContext();
        ctx.getEnvironment().setActiveProfiles("test");
        ctx.load("file:src/main/webapp/WEB-INF/spring/spring-config.xml");
        ctx.refresh();

        emf = ctx.getBean(EntityManagerFactory.class);
        em = emf.createEntityManager();

    }

    @After
    public void tearDown() {

        if (em != null) {
            em.clear();
            em.close();
        }

        if (emf != null) {
            emf.close();
        }

        ctx.close();
    }
}
