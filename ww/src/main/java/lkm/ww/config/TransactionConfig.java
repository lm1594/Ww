package lkm.ww.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 트랜잭션 AOP 설정
 * @author lkm
 * @since 2020.04.02
 */
@Configuration
public class TransactionConfig {
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
	
	@Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* *..*Service.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
    
    @Bean
    public TransactionInterceptor txAdvice() {
        TransactionInterceptor txAdvice = new TransactionInterceptor();
        txAdvice.setTransactionManager(transactionManager());
        txAdvice.setTransactionAttributeSources(this.getTransactionAttributeSources());
        return txAdvice;
    }

    private TransactionAttributeSource[] getTransactionAttributeSources() {
        List<TransactionAttributeSource> transactionAttributeSourceList = new ArrayList<>();
        transactionAttributeSourceList.addAll(this.getMatchAlwaysTransactionAttributeSourceList());

        TransactionAttributeSource[] transactionAttributeSources = new TransactionAttributeSource[transactionAttributeSourceList.size()];
        transactionAttributeSourceList.toArray(transactionAttributeSources);
        return transactionAttributeSources;
    }
    
    private List<MatchAlwaysTransactionAttributeSource> getMatchAlwaysTransactionAttributeSourceList() {

        List<MatchAlwaysTransactionAttributeSource> result = new ArrayList<>();
        result.add(getRollbackRulesMatchAlwaysTransactionAttributeSource("insert*",	TransactionDefinition.PROPAGATION_REQUIRED));
        result.add(getRollbackRulesMatchAlwaysTransactionAttributeSource("update*",	TransactionDefinition.PROPAGATION_REQUIRED));
        result.add(getRollbackRulesMatchAlwaysTransactionAttributeSource("delete*",	TransactionDefinition.PROPAGATION_REQUIRED));
        result.add(getRollbackRulesMatchAlwaysTransactionAttributeSource("batch*", 	TransactionDefinition.PROPAGATION_REQUIRED));
        result.add(getRollbackRulesMatchAlwaysTransactionAttributeSource("merge*",	TransactionDefinition.PROPAGATION_REQUIRED));
        
        result.add(getReadOnlyMatchAlwaysTransactionAttributeSource("select*"));
        result.add(getReadOnlyMatchAlwaysTransactionAttributeSource("get*"));
        return result;
    }

    private MatchAlwaysTransactionAttributeSource getRollbackRulesMatchAlwaysTransactionAttributeSource(String methodName, int propagation) {
        MatchAlwaysTransactionAttributeSource result = new MatchAlwaysTransactionAttributeSource();

        RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute();
        ruleBasedTransactionAttribute.setName(methodName);
        ruleBasedTransactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        ruleBasedTransactionAttribute.setPropagationBehavior(propagation);

        result.setTransactionAttribute(ruleBasedTransactionAttribute);
        return result;
    }

    private MatchAlwaysTransactionAttributeSource getReadOnlyMatchAlwaysTransactionAttributeSource(String methodName) {
        MatchAlwaysTransactionAttributeSource result = new MatchAlwaysTransactionAttributeSource();

        RuleBasedTransactionAttribute ruleBasedTransactionAttribute = new RuleBasedTransactionAttribute();
        ruleBasedTransactionAttribute.setName(methodName);
        ruleBasedTransactionAttribute.setReadOnly(true);

        result.setTransactionAttribute(ruleBasedTransactionAttribute);
        return result;
    }
}
