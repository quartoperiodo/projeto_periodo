/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwarecorporativo.monitoriaifpe.modelo.atividade.validation;

import com.softwarecorporativo.monitoriaifpe.modelo.atividade.Atividade;
import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author EdmilsonS
 */
public class ValidadorAtividade implements ConstraintValidator<ValidaAtividade, Atividade> {

    @Override
    public void initialize(ValidaAtividade validaAtividade) {

    }

    @Override
    public boolean isValid(Atividade atividade, ConstraintValidatorContext cvc) {
        if (atividade != null && atividade.getHorarioEntrada() != null && atividade.getHorarioSaida() != null) {

            Calendar dataHorarioEntrada = Calendar.getInstance();
            Calendar dataHorarioSaida = Calendar.getInstance();
            dataHorarioSaida.setTime(atividade.getHorarioSaida());
            dataHorarioEntrada.setTime(atividade.getHorarioEntrada());

            Calendar dataAtual = Calendar.getInstance();
            dataAtual.set(Calendar.HOUR_OF_DAY, dataHorarioEntrada.get(Calendar.HOUR_OF_DAY));
            dataAtual.set(Calendar.MINUTE, dataHorarioEntrada.get(Calendar.MINUTE));
            dataAtual.set(Calendar.SECOND, dataHorarioEntrada.get(Calendar.SECOND));
            Date horarioEntrada = dataAtual.getTime();

            dataAtual.set(Calendar.HOUR_OF_DAY, dataHorarioSaida.get(Calendar.HOUR_OF_DAY));
            dataAtual.set(Calendar.MINUTE, dataHorarioSaida.get(Calendar.MINUTE));
            dataAtual.set(Calendar.SECOND, dataHorarioSaida.get(Calendar.SECOND));
            Date horarioSaida = dataAtual.getTime();

            return horarioEntrada.before(horarioSaida);
        }
        return Boolean.FALSE;
    }

}
