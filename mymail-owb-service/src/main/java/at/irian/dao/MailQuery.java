package at.irian.dao;

import at.irian.domain.Mail;
import at.irian.domain.MailPriority;
import at.irian.domain.Mail_;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MailQuery {

    private EntityManager em;
    private CriteriaBuilder builder;
    private CriteriaQuery<Mail> query;
    private Root<Mail> root;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private AtomicInteger parameterIndex = new AtomicInteger();
    private Integer firstResult;
    private Integer maxResults;

    public MailQuery(EntityManager em) {
        this.em = em;
        builder = em.getCriteriaBuilder();
        query = builder.createQuery(Mail.class);
        root = query.from(Mail.class);
        query.select(root);
        query.where(builder.conjunction());
    }

    public MailQuery withFromEquals(String from) {
        if (from != null && !from.isEmpty()) {
            addEquals(Mail_.from, String.class, from);
        }
        return this;
    }

    public MailQuery withFromLike(String from) {
        if (from != null && !from.isEmpty()) {
            addLike(Mail_.from, from);
        }
        return this;
    }

    public MailQuery withSubjectEquals(String subject) {
        if (subject != null && !subject.isEmpty()) {
            addEquals(Mail_.subject, String.class, subject);
        }
        return this;
    }

    public MailQuery withSubjectLike(String subject) {
        if (subject != null && !subject.isEmpty()) {
            addLike(Mail_.subject, subject);
        }
        return this;
    }

    public MailQuery withPriorityIn(List<MailPriority> priorities) {
        if (priorities != null && !priorities.isEmpty()) {
            addIn(Mail_.priority, priorities);
        }
        return this;
    }

    public MailQuery setFirstResult(int firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    public MailQuery setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public List<Mail> getResultList() {
        query.orderBy(builder.desc(root.get(Mail_.date)));
        TypedQuery<Mail> typedQuery = em.createQuery(query);
        if (firstResult != null) {
            typedQuery.setFirstResult(firstResult);
        }
        if (maxResults != null) {
            typedQuery.setMaxResults(maxResults);
        }
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return typedQuery.getResultList();
    }

    public int getResultSize() {
        CriteriaQuery<Long> sizeQuery = builder.createQuery(Long.class);
        sizeQuery.from(Mail.class);
        sizeQuery.select(builder.count(root));
        sizeQuery.where(query.getRestriction());
        TypedQuery<Long> typedQuery = em.createQuery(sizeQuery);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return typedQuery.getSingleResult().intValue();
    }

    private <T> void addEquals(SingularAttribute<Mail, T> attribute, Class<T> clazz, T value) {
        ParameterExpression<T> parameter = createParameter(clazz, value);
        Predicate condition = builder.equal(root.get(attribute), parameter);
        query.where(builder.and(query.getRestriction(), condition));
    }

    private void addLike(SingularAttribute<Mail, String> attribute, String value) {
        ParameterExpression<String> parameter = createParameter(String.class, value);
        Predicate condition = builder.like(root.get(attribute), parameter);
        query.where(builder.and(query.getRestriction(), condition));
    }

    private <T> void addIn(SingularAttribute<Mail, T> attribute, List<T> values) {
        Predicate condition = root.get(attribute).in(values);
        query.where(builder.and(query.getRestriction(), condition));
    }

    private <T> ParameterExpression<T> createParameter(Class<T> paramClass, T paramValue) {
        String paramName = "p" + parameterIndex.getAndIncrement();
        parameters.put(paramName, paramValue);
        return builder.parameter(paramClass, paramName);
    }

}
