package com.softwarecorporativo.monitoriaifpe.modelo.turma;

import com.softwarecorporativo.monitoriaifpe.modelo.curso.Curso;
import com.softwarecorporativo.monitoriaifpe.modelo.negocio.EntidadeNegocio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_componente_curricular")
@AttributeOverrides({
    @AttributeOverride(name = "chavePrimaria", column = @Column(name = "id_componente_curricular"))})
@Access(AccessType.FIELD)
public class ComponenteCurricular extends EntidadeNegocio {

    private static final long serialVersionUID = -3079766681161299776L;

    @NotBlank
    @Column(name = "txt_codigo_componente", nullable = false, unique = true)
    private String codigoComponenteCurricular;

    @NotBlank
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[A-Z]{1}\\D+$", message = "{com.softwarecorporativo.monitoriaifpe.componenteCurricular.descricao}")
    @Column(name = "txt_descricao", nullable = false)
    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "componenteCurricular", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Turma> turmas;

    public ComponenteCurricular(String codigoComponenteCurricular, String descricao) {
        this.codigoComponenteCurricular = codigoComponenteCurricular;
        this.descricao = descricao;
    }

    public ComponenteCurricular() {
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public Curso getCurso() {

        return curso;
    }

    public void setCurso(Curso curso) {

        this.curso = curso;
    }

    public String getCodigoComponenteCurricular() {
        return codigoComponenteCurricular;
    }

    public void setCodigoComponenteCurricular(String codigoComponenteCurricular) {
        this.codigoComponenteCurricular = codigoComponenteCurricular;
    }

    public void addTurma(Turma turma) {
        if (this.turmas == null) {
            this.turmas = new ArrayList<>();
        }
        turma.setComponenteCurricular(this);
        this.turmas.add(turma);
    }
}