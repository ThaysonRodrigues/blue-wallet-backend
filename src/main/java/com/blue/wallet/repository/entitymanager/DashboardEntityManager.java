package com.blue.wallet.repository.entitymanager;

import com.blue.wallet.domain.vo.CategoriaDashboardVO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardEntityManager {

    @PersistenceContext
    private EntityManager entityManager;

    public List<CategoriaDashboardVO> getCalculoReceitaPorCategoria(Integer idUsuario, String data) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT new com.blue.wallet.domain.vo.CategoriaDashboardVO(SUM(lr.valor), cr.descricao) ");
        sb.append("FROM LancamentoReceitaORM lr ");
        sb.append("INNER JOIN lr.categoriaReceita cr ");
        sb.append("WHERE lr.usuario.id = :idUsuario and to_char(lr.dataLancamento, 'YYYY-MM') = :data ");
        sb.append("group by cr.descricao ");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("data", data);

        return query.getResultList();
    }

    public List<CategoriaDashboardVO> getCalculoDespesaPorCategoria(Integer idUsuario, String data) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT new com.blue.wallet.domain.vo.CategoriaDashboardVO(SUM(ld.valor), cd.descricao) ");
        sb.append("FROM LancamentoDespesaORM ld ");
        sb.append("INNER JOIN ld.categoriaDespesa cd ");
        sb.append("WHERE ld.usuario.id = :idUsuario and to_char(ld.dataPagamento, 'YYYY-MM') = :data ");
        sb.append("group by cd.descricao ");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("data", data);

        return query.getResultList();
    }

    public BigDecimal getSaldoAtual(Integer idUsuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SUM(lr.valor) - (SELECT SUM(ld.valor) from LancamentoDespesaORM ld where ld.usuario.id = :idUsuario) ");
        sb.append("FROM LancamentoReceitaORM lr ");
        sb.append("WHERE lr.usuario.id = :idUsuario ");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("idUsuario", idUsuario);
        return (BigDecimal) query.getSingleResult();
    }
}