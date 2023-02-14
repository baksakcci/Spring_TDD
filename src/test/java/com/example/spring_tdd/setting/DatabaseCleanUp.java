package com.example.spring_tdd.setting;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*

1. JPA entityManager에서 Entity들을 다 가져옴
2. stream을 통해 각각 @Entity, @Table 어노테이션 가지고 있는지 확인 후 각 Entity의 Table 이름들 가져옴
3. 이번에는 @Entity만 가지고 있고 @Table 어노테이션은 가지고 있지 않은 것을 가지고 옴
 */
@Component
public class DatabaseCleanUp implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        tableNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e -> e.getJavaType().getAnnotation(Table.class).name())
                .collect(Collectors.toList());
        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && !hasTableAnnotation(e))
                // productItem -> product_item 이런식으로 이름 바꾸는 것, CamelCase이용
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .collect(Collectors.toList());
        tableNames.addAll(entityNames);
    }

    private boolean isEntity(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Entity.class);
    }

    private boolean hasTableAnnotation(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    @Transactional
    // H2 기준
    public void execute() {
        entityManager.flush();
        // table에서 id를 포함한 모든 row 데이터를 삭제하기 위해 참조무결성 일시적 해제
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        for(final String tableName : tableNames) {
            // table 모든 row 데이터 삭제
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            // 테이블 수정 -> ID를 1로 다시 시작하게 함
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        }
        // 다시 참조무결성 설정
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
