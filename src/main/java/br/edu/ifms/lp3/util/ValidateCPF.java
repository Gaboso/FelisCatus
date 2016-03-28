package br.edu.ifms.lp3.util;

/**
 * Classe pega no DevMedia e otimizada por Gabriel Santiago de caravalho
 * <p>
 * Link Original:
 * http://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
 *
 * @author Omero Francisco Bertol Modificado por: Gabriel Santiago de Carvalho
 */
public class ValidateCPF {

    public boolean isCPF(String cpf) {
        //TODO corrigir verificacoes
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")) {
            return false;
        } else {

            char tenDigit, elevenDigit;
            int sum, i, r, weight;

            ValidateCPF validateCPF = new ValidateCPF();

            // Cálculo do 1° dígito verificador
            sum = 0;
            weight = 10;
            for (i = 0; i < 9; i++) {
                sum = validateCPF.runCalculation(sum, weight, i, cpf);
                weight--;
            }

            r = 11 - (sum % 11);
            tenDigit = validateCPF.verifyR(r);

            // Cálculo do 2° dígito verificador
            sum = 0;
            weight = 11;

            for (i = 0; i < 10; i++) {
                sum = validateCPF.runCalculation(sum, weight, i, cpf);
                weight--;
            }

            r = 11 - (sum % 11);
            elevenDigit = validateCPF.verifyR(r);

            // Se os dígitos calculados são iguais os dígitos pegos.
            return (tenDigit == cpf.charAt(9)) && (elevenDigit == cpf.charAt(10));
        }
    }

    /**
     * Método para executar os cálculos do somatório
     *
     * @param sum   - Quantidade que o somatório já esta
     * @param weight - Peso da posição em que esta verificando
     * @param index - Índice qual esta verificando
     * @param cpf - CPF do cliente
     * @return sum atualizado
     */
    private int runCalculation(int sum, int weight, int index, String cpf) {
        int number = cpf.charAt(index) - 48;
        sum += number * weight;
        return sum;
    }

    /**
     * Método para verificar os últimos dígitos do CPF
     *
     * @param r digito
     * @return o valor equivalente as ultima posições no CPF
     */
    private char verifyR(int r) {
        return (r == 10) || (r == 11) ? '0' : (char) (r + 48);
    }

}